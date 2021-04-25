package es.codeurjc.friends_padel_tour.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.codeurjc.friends_padel_tour.Security.jwt.JwtRequestFilter;

@Configuration
@Order(1)
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	//Expose AuthenticationManager as a Bean to be used in other services
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/api/**");
		
		// URLs that need authentication to access to it

		
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/matches/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/matches/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/matches/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/matches/**").permitAll();

		
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/tournaments/acceptedTournament/{id}").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/tournaments/acceptedTournament/winner/{id}").hasRole("BUSSINESS");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/tournaments/**").hasRole("BUSSINESS");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/tournaments/{id}").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/tournaments/acceptedTournaments").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/tournaments/nonAcceptedTournaments").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/tournaments/acceptedTournament/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/tournaments/**").hasRole("BUSSINESS");

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/bussiness/").hasRole("BUSSINESS");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/player/").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/player/{id}/image").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/bussiness/{id}/image").hasRole("BUSSINESS");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/DoubleWith/").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/player/{id}").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/bussiness/{id}").hasRole("BUSSINESS");


		// Other URLs can be accessed without authentication
		http.authorizeRequests().anyRequest().permitAll();

		// Disable CSRF protection (it is difficult to implement in REST APIs)
		http.csrf().disable();

		// Disable Http Basic Authentication
		http.httpBasic().disable();
		
		// Disable Form login Authentication
		http.formLogin().disable();

		// Avoid creating session 
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// Add JWT Token filter
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}
}

