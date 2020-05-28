package br.com.savetheday.security;

import br.com.savetheday.security.details.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	MyUserDetailsService myUserDetailsService;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin().and()
				.csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.GET,"/api/caso").permitAll()
				.antMatchers(HttpMethod.POST,"/api/ong").permitAll()
				.antMatchers(HttpMethod.POST, "/api/login").permitAll()
				.anyRequest().authenticated()
				.and().addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
				http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, myUserDetailsService));
				http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
