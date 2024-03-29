package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ProyectoAgenciaVuelosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoAgenciaVuelosApplication.class, args);
	}

}
