package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class Application {
	
	/* Esta es la única clase de configuración en Spring Boot, que unifica todas las que había en Spring.
	Al arrancar daba un error porque no encontraba el objeto RestClient, así que hay que crear aquí ese método,
	que antes estaba en service.config */

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public RestClient getClient() {
		return RestClient.create();
	}
}