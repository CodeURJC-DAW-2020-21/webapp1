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

import es.codeurjc.friends_padel_tour.Service.UserService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userDetailsService;

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
		
		http.antMatcher("/new/**");

		// Public pages that we all users can access
		http.authorizeRequests().antMatchers("/new/**").permitAll();
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/404").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		
        http.authorizeRequests().antMatchers("/AboutUs").permitAll();
        http.authorizeRequests().antMatchers("/friendlyMatch").permitAll();
        http.authorizeRequests().antMatchers("/previousSignUp").permitAll();
		
		http.authorizeRequests().antMatchers("/download-pdf").permitAll(); //Generate the PDF 
		http.authorizeRequests().antMatchers("/tournamentInfo/{\\d+}}").permitAll();
        http.authorizeRequests().antMatchers("/team/1").permitAll();
        http.authorizeRequests().antMatchers("/team/2").permitAll();
        http.authorizeRequests().antMatchers("/team/3").permitAll();
        http.authorizeRequests().antMatchers("/team/4").permitAll();
        http.authorizeRequests().antMatchers("/team/5").permitAll();
        http.authorizeRequests().antMatchers("/team/6").permitAll();
        http.authorizeRequests().antMatchers("/tournaments").permitAll();
		
		http.authorizeRequests().antMatchers("/signUpPlayer").permitAll();
		http.authorizeRequests().antMatchers("/userSignUp").permitAll();
		http.authorizeRequests().antMatchers("/successDelete").permitAll();
		http.authorizeRequests().antMatchers("/succesEdit").permitAll();
		http.authorizeRequests().antMatchers("/bussinessSignUp").permitAll();
		http.authorizeRequests().antMatchers("/bussinessSignUpForm").permitAll();
		http.authorizeRequests().antMatchers("/update/{\\d+}/image").permitAll();			
        http.authorizeRequests().antMatchers("/css-min/**", "/css/main.css", "/css/**", "/scss/**","/js/**","/icomoon/**", "/images/**", "/fonts/**", "/dev-assets/**", "/vendor/**", "/html/**").permitAll();
		

		// Private pages (all other pages)
		http.authorizeRequests().antMatchers("/makeDoubleWhith/{\\s}").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/joinTournament/{\\d+}").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/create/tournament").hasAnyRole("BUSSINESS");
		http.authorizeRequests().antMatchers("/userProfile").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/delete/{\\d+}").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/bussinessProfile").hasAnyRole("BUSSINESS");
		http.authorizeRequests().antMatchers("/tournamentManagement").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/tournamentRequest").hasAnyRole("BUSSINESS");
		http.authorizeRequests().antMatchers("/acceptTournament/{\\d+}").hasAnyRole("ADMIN");

		
		

		// Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/loginError");

		// Logout button
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");

	}

}
