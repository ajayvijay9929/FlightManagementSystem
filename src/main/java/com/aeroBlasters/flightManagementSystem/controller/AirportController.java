package com.aeroBlasters.flightManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aeroBlasters.flightManagementSystem.bean.Airport;
import com.aeroBlasters.flightManagementSystem.dao.AirportDao;
import com.aeroBlasters.flightManagementSystem.exception.AirportException;

/**
 * Controller class to handle requests related to Airport operations.
 * 
 * This class provides endpoints for viewing, adding, and listing airports.
 */
@RestController
public class AirportController {

	@Autowired
	private AirportDao airportDao;

	/**
	 * Displays the airport entry page.
	 * 
	 * @return ModelAndView object for the airport entry page
	 */
	@GetMapping("/airport")
	public ModelAndView showAirportEntryPage() {
		try {
			Airport airport = new Airport();
			ModelAndView mv = new ModelAndView("airportEntryPage");
			mv.addObject("airport_data", airport);
			return mv;
		} catch (Exception e) {
			throw new AirportException("Error displaying airport entry page: " + e.getMessage());
		}
	}

	/**
	 * Saves the airport details.
	 * 
	 * @param airport Airport object populated from the form data
	 * @return ModelAndView object for redirecting to the index page
	 */
	@PostMapping("/airport")
	public ModelAndView saveAirport(@ModelAttribute("airport_data") Airport airport) {
		try {
			// Convert airport code to uppercase
			System.out.println(airport.getAirportCode());
			String str = airport.getAirportCode().toUpperCase();
			System.out.println(str);
			airport.setAirportCode(str);

			// Convert airport location to uppercase
			String stg = airport.getAirportLocation().toUpperCase();
			airport.setAirportLocation(stg);

			// Validate airport code length
			if (airport.getAirportCode().length() != 3) {
				throw new AirportException("Airport code must be 3 characters long.");
			}

			// Validate airport location length
			if (airport.getAirportLocation().length() < 3) {
				throw new AirportException("Airport location must be at least 3 characters long.");
			}

			// Save the airport details to the database
			airportDao.addAirport(airport);
			return new ModelAndView("redirect:/index");
		} catch (Exception e) {
			throw new AirportException("Error saving airport: " + e.getMessage());
		}
	}

	/**
	 * Displays details of a single airport.
	 * 
	 * @param id ID of the airport to be displayed
	 * @return ModelAndView object for the airport show page
	 */
	@GetMapping("/airport/{id}")
	public ModelAndView showSingleAirportPage(@PathVariable("id") String id) {
		try {
			// Find the airport by ID
			Airport airport = airportDao.findAirportById(id.toUpperCase());
			if (airport == null) {
				throw new AirportException("Airport with ID " + id + " not found.");
			}
			ModelAndView mv = new ModelAndView("airportShowPage");
			mv.addObject("airport", airport);
			return mv;
		} catch (Exception e) {
			throw new AirportException("Error displaying airport: " + e.getMessage());
		}
	}

	@GetMapping("/updateairport/{id}")
	public ModelAndView showUpdateAirportPage(@PathVariable("id") String id) {
		try {
			// Find the airport by ID
			Airport airport = airportDao.findAirportById(id.toUpperCase());
			if (airport == null) {
				throw new AirportException("Airport with ID " + id + " not found.");
			}
			ModelAndView mv = new ModelAndView("airportUpdatePage");
			mv.addObject("airport", airport);
			return mv;
		} catch (Exception e) {
			throw new AirportException("Error displaying airport: " + e.getMessage());
		}
	}

	@PostMapping("/updateairport")
	public ModelAndView updateAirport(@ModelAttribute("airport") Airport airport) {
		try {
			// Convert airport code to uppercase
			String str = airport.getAirportCode().toUpperCase();
			airport.setAirportCode(str);

			// Convert airport location to uppercase
			String stg = airport.getAirportLocation().toUpperCase();
			airport.setAirportLocation(stg);

			// Validate airport code length
			if (airport.getAirportCode().length() != 3) {
				throw new AirportException("Airport code must be 3 characters long.");
			}

			// Validate airport location length
			if (airport.getAirportLocation().length() < 3) {
				throw new AirportException("Airport location must be at least 3 characters long.");
			}

			// Save the airport details to the database
			airportDao.updateAirport(airport);
			return new ModelAndView("redirect:/index");
		} catch (Exception e) {
			throw new AirportException("Error updating airport: " + e.getMessage());
		}
	}

	/**
	 * Displays a report of all airports.
	 * 
	 * @return ModelAndView object for the airport report page
	 */
	@GetMapping("/airports")
	public ModelAndView showAirportReportPage() {
		try {
			// Retrieve all airports from the database
			List<Airport> airportList = airportDao.findAllAirports();
			ModelAndView mv = new ModelAndView("airportReportPage");
			mv.addObject("airportList", airportList);
			return mv;
		} catch (Exception e) {
			throw new AirportException("Error displaying airport report page: " + e.getMessage());
		}
	}

	/**
	 * Displays a report of all airports for customers
	 * 
	 * @return ModelAndView object for the airport report page
	 */
	@GetMapping("/allairports")
	public ModelAndView showAirportReportPageForCustomer() {
		try {
			// Retrieve all airports from the database
			List<Airport> airportList = airportDao.findAllAirports();
			ModelAndView mv = new ModelAndView("customerAirportReportPage");
			mv.addObject("airportList", airportList);
			return mv;
		} catch (Exception e) {
			throw new AirportException("Error displaying airport report page: " + e.getMessage());
		}
	}

	@GetMapping("/deleteairport/{id}")
	public ModelAndView showDeleteAirportPage(@PathVariable("id") String id) {
		try {
			// Find the airport by ID
			Airport airport = airportDao.findAirportById(id.toUpperCase());
			if (airport == null) {
				throw new AirportException("Airport with ID " + id + " not found.");
			}
			ModelAndView mv = new ModelAndView("airportDeletePage");
			mv.addObject("airport", airport);
			return mv;
		} catch (Exception e) {
			throw new AirportException("Error displaying airport: " + e.getMessage());
		}
	}

	// Delete the airport
	@PostMapping("/deleteairport")
	public ModelAndView deleteAirport(@RequestParam("airportCode") String airportCode) {
		try {
			Airport airport = airportDao.findAirportById(airportCode.toUpperCase());
			if (airport == null) {
				throw new AirportException("Airport not found.");
			}
			airportDao.deleteAirport(airport);
			return new ModelAndView("redirect:/index");
		} catch (Exception e) {
			throw new AirportException("Error deleting airport: " + e.getMessage());
		}
	}

	/**
	 * Handles AirportException exceptions.
	 * 
	 * @param exception the exception to be handled
	 * @return ModelAndView object for the error page
	 */
	@ExceptionHandler(value = AirportException.class)
	public ModelAndView handlingRouteException(AirportException exception) {
		String message = "Airport Exception: " + exception.getMessage();
		ModelAndView mv = new ModelAndView("airportErrorPage");
		mv.addObject("errorMessage", message);
		return mv;
	}
}
