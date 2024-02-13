package init;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager users() {
		List<UserDetails> usuarios = List.of(
				User.withUsername("user1")
					.password("{noop}user1")		/* {noop} es para indicar que la contraseña NO está encriptada,
													porque por defecto entiende que sí y al no haber un mapeador de
													encriptar/desencriptar da error. */
					.roles("USERS")					// En mayúsculas por convención
					.build(),
				User.withUsername("user2")
					.password("{noop}user2")
					.roles("OPERATORS")
					.build(),
				User.withUsername("admin")
					.password("{noop}admin")
					.roles("USERS", "ADMINS")
					.build());
		return new InMemoryUserDetailsManager(usuarios); 
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf( c -> c.disable() )		/* Token que se habilita por defecto
		para envitar inyecciones de código (?) pero que deshabilitamos porque debe
		estar deshabilitado en microservicios. Cuestión de Fe, amén. */
			.authorizeHttpRequests(
					aut -> aut.requestMatchers(HttpMethod.GET, "/curso").authenticated()
											  // permite llamadas GET de curso de cualquier usuario autenticado
							  .requestMatchers(HttpMethod.GET, "/cursos/*/*").authenticated()
							  				  // permite llamadas GET de curso por precio de cualquier usuario autenticado
							  .requestMatchers(HttpMethod.POST, "/agregar").hasRole("ADMINS")
							  				  // permite llamadas POST de agregar curso sólo de los administradores
							  .requestMatchers(HttpMethod.DELETE, "/eliminarCurso").hasAnyRole("ADMINS", "OPERATORS")
							  				  // permite llamadas DELETE de eliminar curso sólo de los administradores y operadores
							  .requestMatchers(HttpMethod.PUT, "/actualizarPrecio").hasRole("OPERATORS")
							  				  // permite llamadas PUT de actualizar curso sólo de los operadores
							  .anyRequest().permitAll()		// Acceso libre al resto de llamadas
			 )
			.httpBasic(Customizer.withDefaults());		// Identificación por defecto
		return http.build();
	}
}