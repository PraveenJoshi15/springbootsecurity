package com.secure.app.securitylearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//System.out.println("inside auth");
		auth.inMemoryAuthentication()
		.withUser("zensar").password(passwordEncrypter().encode("zensar123")).roles("USER")
		.and()
		.withUser("admin").password(passwordEncrypter().encode("admin123")).roles("USER","ADMIN");
			
	}
					
	
	
	
	  @Override 
	  protected void configure(HttpSecurity http) throws Exception {
	  
	  //System.out.println("inside http");
	  
		
		  http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN")
		  .antMatchers("/contact").hasAnyRole("USER", "ADMIN")
		  .antMatchers("/").authenticated() .and() .httpBasic();
		 
		  
		/*
		 * http .authorizeRequests() .anyRequest() .authenticated() .and() .httpBasic();
		 */
	  
	  }
	 
	 
	 
	
	@Bean
	PasswordEncoder passwordEncrypter() {
		return new BCryptPasswordEncoder();
	}
}
