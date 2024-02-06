package controller.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"controller"})
public class MvcConfig {}

/* Sobra todo el código y métodos porque no hay vistas ni, por tanto,
que dirigir a ellas. */