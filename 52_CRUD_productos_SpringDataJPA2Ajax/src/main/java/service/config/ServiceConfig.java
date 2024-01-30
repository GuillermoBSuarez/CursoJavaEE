package service.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// Nueva etiqueta en Spring Data JPA a la que hay que darle tres referencias:
@EnableJpaRepositories(basePackages = "DAO.interfaces",
					   entityManagerFactoryRef = "factory",
					   transactionManagerRef = "txManager")			
@EnableTransactionManagement
@Configuration

// Desaparece el paquete dao.implementations
@ComponentScan(basePackages = { "service.implementations" })
public class ServiceConfig {

	@Bean
	public DataSource crearDatasource() {
		DriverManagerDataSource data = new DriverManagerDataSource();
		data.setDriverClassName("com.mysql.cj.jdbc.Driver");
		data.setUrl("jdbc:mysql://localhost:3306/tienda");
		data.setUsername("root");
		data.setPassword("root");
		return data;
	}

	@Bean
	public HibernateJpaVendorAdapter adapter() {
		HibernateJpaVendorAdapter adp = new HibernateJpaVendorAdapter();
		adp.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return adp;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean factory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setPersistenceUnitName("tiendaPU");
		factory.setDataSource(crearDatasource());
		factory.setPackagesToScan("model");
		factory.setJpaVendorAdapter(adapter());
		return factory;
	}

	@Bean
	public JpaTransactionManager txManager() {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(factory().getObject());
		return manager;
	}
}