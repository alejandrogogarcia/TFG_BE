package es.udc.tfg.app.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtGenerator jwtGenerator;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.addFilter(new JwtFilter(authenticationManager(), jwtGenerator))
			.authorizeRequests()
//			.antMatchers(HttpMethod.POST, "/signup").permitAll()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			//.antMatchers(HttpMethod.POST, "/users/loginFromServiceToken").permitAll()
			//.antMatchers(HttpMethod.PUT, "/users/*").hasRole("USER")
			//.antMatchers(HttpMethod.GET, "/users/*").hasRole("ADMIN")
//			.antMatchers(HttpMethod.GET, "/courses/public").permitAll()
			.antMatchers(HttpMethod.PUT, "/users/**").authenticated()
			.antMatchers(HttpMethod.POST, "/users/create").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/allUsers").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/category/create").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT, "/category/*/update").hasRole("ADMIN")
//			.antMatchers(HttpMethod.GET, "/sporttests/sporttest/*").permitAll()
//			.antMatchers(HttpMethod.GET, "/sporttests/sporttest/findByDate").permitAll()
//			.antMatchers(HttpMethod.POST, "/inscriptions/inscript/*/create").hasRole("USER")
			.anyRequest().denyAll();

	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration config = new CorsConfiguration();
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		config.setAllowCredentials(false);
	    config.addAllowedOrigin("*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    
	    source.registerCorsConfiguration("/**", config);
	    
	    return source;
	    
	 }
}
