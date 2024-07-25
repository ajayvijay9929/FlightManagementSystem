package com.aeroBlasters.flightManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.aeroBlasters.flightManagementSystem.service.FlightUserService;

/**
 * Security configuration class to set up web security and authentication.
 * <p>
 * This class configures web security settings such as URL access
 * permissions, login and logout behavior, and password encoding.
 * </p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private FlightUserService service; // Service to load user details from the database

	@Autowired
	private EncoderConfig config; // Configuration for password encoding

	/**
	 * Configures authentication with a custom user details service
	 * and password encoder.
	 * <p>
	 * This method sets up the AuthenticationManagerBuilder to use
	 * FlightUserService for user details and BCryptPasswordEncoder
	 * for password encoding.
	 * </p>
	 * 
	 * @param authority the AuthenticationManagerBuilder to configure.
	 * @throws Exception if an error occurs while configuring.
	 */
	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder authority) throws Exception {
		authority.userDetailsService(service).passwordEncoder(config.passwordEncoder());
	}

	/**
	 * Configures HTTP security settings.
	 * <p>
	 * This method sets up URL access permissions, login and logout
	 * configurations, and disables CSRF protection.
	 * </p>
	 * 
	 * @param http the HttpSecurity object to configure.
	 * @throws Exception if an error occurs while configuring.
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				.antMatchers("/register", "/static/**", "/images/**").permitAll() // Allow access to registration and
																					// static resources
				.anyRequest().authenticated() // Require authentication for all other requests
				.and().formLogin()
				.loginPage("/loginpage") // Custom login page
				.failureUrl("/loginErrorPage") // Redirect on login failure
				.loginProcessingUrl("/login") // URL to process login
				.permitAll() // Allow access to login page and processing URL
				.and().logout()
				.logoutSuccessUrl("/index") // Redirect after logout
				.and().csrf().disable(); // Disable CSRF protection (consider enabling in production)
	}
}
