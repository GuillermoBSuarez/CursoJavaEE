package init;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
	@Value("${keycloak.clientId}") // Propiedad en application.yml
	private String clientId;

	/* Método que devuelve una implementación de AbstractAuthenticationToken
	con los datos del usuario extraidos del token. Spring Security usa la información
	para el proceso de autorización. */
	@Override
	public AbstractAuthenticationToken convert(Jwt jwt) {
		Collection<GrantedAuthority> authorities = extractResourceRoles(jwt);
		return new JwtAuthenticationToken(jwt, authorities, jwt.getClaim("preferred_username"));
	}

	// Método que obtiene una colección de roles incluidos en el token.
	private Collection<GrantedAuthority> extractResourceRoles(Jwt jwt) {
		Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
		// getClaim incluye los datos asociados al clientId, incluyendo lista de roles

		Map<String, Object> resource;
		Collection<String> resourceRoles;

		if (resourceAccess == null ||
		   (resource = (Map<String, Object>) resourceAccess.get(clientId)) == null ||
		   (resourceRoles = (Collection<String>) resource.get("roles")) == null) {
			/* Si no hay información sobre ese cliente se devuelve colección vacía
			en vez de null, para evitar NullPointerException. */
			return Set.of();
		}

		// a cada rol indicado se le añade el prefijo "ROLE_" porque así los espera Spring Security
		return resourceRoles.stream()
							.map(role -> new SimpleGrantedAuthority("ROLE_" + role))
							.collect(Collectors.toSet());
	}
}