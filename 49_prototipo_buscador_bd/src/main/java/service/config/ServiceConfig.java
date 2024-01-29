package service.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement // habilita la transaccionalidad con anotaciones
@Configuration
@ComponentScan(basePackages = { "service.implementations" })
public class ServiceConfig {

	// Objeto DataSource con datos de conexión a la BD
	@Bean
	public DataSource crearDatasource() {
		DriverManagerDataSource data = new DriverManagerDataSource();
		data.setDriverClassName("com.mysql.cj.jdbc.Driver");
		data.setUrl("jdbc:mysql://localhost:3306/buscador");
		data.setUsername("root");
		data.setPassword("root");
		return data;
	}

	// Adaptador de Hibernate
	@Bean
	public HibernateJpaVendorAdapter adapter() {
		HibernateJpaVendorAdapter adp = new HibernateJpaVendorAdapter();
		adp.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return adp;
	}

	// FactorÍa EntityManager para acceder a capa de persistencia con JPA
	@Bean
	public LocalContainerEntityManagerFactoryBean factory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setPersistenceUnitName("buscadorPU");
		factory.setDataSource(crearDatasource()); // el primer objeto
		factory.setPackagesToScan("model"); // paquete donde estarán las entidades
		factory.setJpaVendorAdapter(adapter()); // el adaptador creado antes
		return factory;
	}

	// TransactionManager, encargado de la transaccionalidad
	@Bean
	public JpaTransactionManager txManager() {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(factory().getObject());
		return manager;
	}
}