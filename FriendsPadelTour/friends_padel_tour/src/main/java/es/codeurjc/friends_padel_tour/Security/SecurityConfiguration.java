package es.codeurjc.friends_padel_tour.Security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import es.codeurjc.friends_padel_tour.Service.RepositoryUserDetailsService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	RepositoryUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/404").permitAll();
		//http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/Index").permitAll();
		
        http.authorizeRequests().antMatchers("/AboutUs").permitAll();
        http.authorizeRequests().antMatchers("/friendlyMatch").permitAll();
        http.authorizeRequests().antMatchers("/previousSignUp").permitAll();
		//Generar PDF
		http.authorizeRequests().antMatchers("/download-pdf").permitAll();
        http.authorizeRequests().antMatchers("/team/1").permitAll();
        http.authorizeRequests().antMatchers("/team/2").permitAll();
        http.authorizeRequests().antMatchers("/team/3").permitAll();
        http.authorizeRequests().antMatchers("/team/4").permitAll();
        http.authorizeRequests().antMatchers("/team/5").permitAll();
        http.authorizeRequests().antMatchers("/team/6").permitAll();
        http.authorizeRequests().antMatchers("/tournaments").permitAll();
		http.authorizeRequests().antMatchers("/userSignUp").permitAll();
		http.authorizeRequests().antMatchers("/bussinessSignUp").permitAll();		
        http.authorizeRequests().antMatchers("/css-min/**", "/css/main.css", "/css/**", "/js/**", "/images/**", "/fonts/**", "/dev-assets/**", "/vendor/**", "/html/**").permitAll();
		

		// Private pages (all other pages)
		http.authorizeRequests().antMatchers("/userProfile").hasAnyRole("user");		
		http.authorizeRequests().antMatchers("/bussinessProfile").hasAnyRole("bussiness");
		http.authorizeRequests().antMatchers("/tournamentManagement").hasAnyRole("");
		http.authorizeRequests().antMatchers("/tournamentRequest").hasAnyRole("");

		// Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/404");

		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");

		http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
		http.headers().frameOptions().sameOrigin();
	}

}
