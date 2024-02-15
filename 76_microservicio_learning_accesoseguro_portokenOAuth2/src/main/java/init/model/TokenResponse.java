package init.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

/* Clase para guardar en objeto Java el token que recibimos del Validador,
que viene en JSON y del que sólo nos interesa el token. */
public class TokenResponse {
	private String access_token;
}