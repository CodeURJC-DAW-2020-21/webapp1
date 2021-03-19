package es.codeurjc.friends_padel_tour.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		String encodedPassword = encoder.encode("pass");

		auth.inMemoryAuthentication().withUser("admin").password(encodedPassword).roles("ADMIN");
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
		http.authorizeRequests().antMatchers("/userProfile").permitAll();
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
		http.authorizeRequests().anyRequest().authenticated();

		// Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/userProfile");
		http.formLogin().failureUrl("/404");

		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");

        
		// Disable CSRF at the moment
		http.csrf().disable();
	}

}
