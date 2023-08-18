package com.aashdit.digiverifier.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.aashdit.digiverifier.login.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@EnableScheduling
@ComponentScan(basePackages = { "com.aashdit.*" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SessionRegistry SessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	 @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		
		 http.headers()
         .contentTypeOptions()
         .and()
         .xssProtection()
         .and()
         .cacheControl()
         .and()
         .httpStrictTransportSecurity()
         .and()
         .frameOptions()
         .and()
         .contentSecurityPolicy("script-src 'self' 'unsafe-eval' 'unsafe-inline'")
//         .and()
//         .referrerPolicy(ReferrerPolicy.ORIGIN_WHEN_CROSS_ORIGIN)
         ;
		
		
		 http.csrf().disable().authorizeRequests()
         .antMatchers(HttpMethod.TRACE, "/**").denyAll()
         .antMatchers(HttpMethod.PATCH, "/**").denyAll()
         .antMatchers(HttpMethod.DELETE, "/**").denyAll().antMatchers(HttpMethod.HEAD, "/**").denyAll()	
         .antMatchers("/swagger**").permitAll()
         .antMatchers("/webjars/**").permitAll()
         .antMatchers("/configuration/ui").permitAll()
         .antMatchers("/swagger-resources/**").permitAll()
         .antMatchers("/v2/api-docs").permitAll()
         .antMatchers("/api/**").permitAll()
         .anyRequest().authenticated();
		 
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	
}
