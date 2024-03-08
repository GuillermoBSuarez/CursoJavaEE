package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestClient;

@SpringBootApplication
@EnableJpaRepositories
public class ProyectoAgenciaReservasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoAgenciaReservasApplication.class, args);
	}

	@Bean
	public RestClient getClient() {
		return RestClient.create();
	}
}
