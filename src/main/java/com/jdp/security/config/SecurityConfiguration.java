package com.jdp.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.jdp.security.repository.UserRepository;

@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses=UserRepository.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService; //service for DB Authentication
//Authentication configuration for DB Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("SecurityConfiguration. configure. AuthenticationManagerBuilder: " + auth);
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new PasswordEncoder() {
			@Override
			public String encode(CharSequence charSequence) {
				System.out.println("SecurityConfiguration::PasswordEncoder::encode. charSequence: " + charSequence);
				return charSequence.toString();
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				System.out.println("SecurityConfiguration::PasswordEncoder::encode. matches: " + true);				
				return true;
			}
		});
		System.out.println("SecurityConfiguration. configure. auth: " + auth);
	}	
//Security Configuration for DB Security
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		System.out.println("SecurityConfiguration. configure. httpSecurity: " + httpSecurity);
		httpSecurity.authorizeRequests()
				.antMatchers("**/secured/**").authenticated()
				.anyRequest().permitAll()
				.and()
//				.formLogin().loginPage("/loginPage").permitAll(); //If there is custom login page, give its url 
				.formLogin().permitAll();  //use Spring Security supplied default login page
		httpSecurity.csrf().disable();
	}
	/* 
//  configuration for inMemoryAuthentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("SecurityConfiguration. configure. auth: " + auth);
		auth.inMemoryAuthentication()
		.passwordEncoder(NoOpPasswordEncoder.getInstance())
		.withUser("user").password("pswd").roles("USER")
		.and()
		.withUser("admin").password("pswd").roles("ADMIN");
	}
*/	
/*
 //Security Configuration for inMemoryAuthentication without filter
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		System.out.println("SecurityConfiguration. configure. httpSecurity: " + httpSecurity);
		httpSecurity.authorizeRequests()
				.antMatchers("/hello").hasRole("USER") //this doesn't work. allows everyone to go through
				//next line needs ** in front of /rest
//			.antMatchers("/rest/") //authenticate requests matching this pattern 
				.anyRequest()
//				.permitAll()  //allow every request to go through. Security is in place but it's disabled
				.fullyAuthenticated()
				.and()
				.httpBasic();
		httpSecurity.csrf().disable();
	}
*/
/*
	//Security configuration for inMemoryAuthentication with custom filter
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		System.out.println("SecurityConfiguration. configure. httpSecurity: " + httpSecurity);
		httpSecurity
				.authorizeRequests()
				.anyRequest()
				.permitAll() 
				.and()
//				.addFilterBefore(customFilter(), BasicAuthenticationFilter.class)
				.httpBasic();
		httpSecurity.csrf().disable();
	}

	@Bean
	public CustomFilter customFilter() {
		System.out.println("SecurityConfiguration. customFilter. return new CustomFilter()");
		return new CustomFilter();
	}
 	*/
}
