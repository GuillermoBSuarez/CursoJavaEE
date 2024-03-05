package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ProyectoAgenciaClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoAgenciaClientesApplication.class, args);
	}

}
