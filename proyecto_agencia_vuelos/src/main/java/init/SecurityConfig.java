package init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	JwtAuthConverter jwtAuthConverter;
	
	@Bean
	public JdbcUserDetailsManager users() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/springsecurity");
		ds.setUsername("root");
		ds.setPassword("root");
		JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager(ds);
		jdbc.setUsersByUsernameQuery("select user, pwd, enabled from users where user=?");
		jdbc.setAuthoritiesByUsernameQuery("select rol, user from roles where user=?");
		return jdbc; 
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf( c -> c.disable() )
			.authorizeHttpRequests(
					aut -> aut.requestMatchers(HttpMethod.PUT, "/vueloupdate").authenticated()
							  .anyRequest().permitAll()
			 )
			.oauth2ResourceServer(
					oauth2ResourceServer -> oauth2ResourceServer.jwt(
							jwt->jwt.jwtAuthenticationConverter(jwtAuthConverter)
					)
			 )
			.sessionManagement(
					sessionManagement -> sessionManagement.sessionCreationPolicy(
							SessionCreationPolicy.STATELESS
					)
			);			
		return http.build();
	}
}