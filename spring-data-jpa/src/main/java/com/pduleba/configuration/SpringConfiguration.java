package com.pduleba.configuration;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.pduleba.jpa.model.CarModel;
import com.pduleba.spring.data.SpringMarker;
import com.pduleba.spring.data.dao.SpringDataJpaMarker;

@Configuration
@ComponentScan(basePackageClasses=SpringMarker.class)
@EnableJpaRepositories(basePackageClasses = SpringDataJpaMarker.class)
@PropertySource("classpath:/config/application.properties")
@EnableTransactionManagement
public class SpringConfiguration implements ApplicationPropertiesConfiguration {
	
	@Autowired
	private Environment env;

	@Bean	// Trick (see doc of @PropertySource for more)
	public static PropertySourcesPlaceholderConfigurer properties(Environment environment) {
	    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
	    propertySourcesPlaceholderConfigurer.setEnvironment(environment);
	    return propertySourcesPlaceholderConfigurer;
	}
	
	@Bean DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName(env.getProperty(KEY_DATASOURCE_DRIVER_CLASS));
		dataSource.setUrl(env.getProperty(KEY_DATASOURCE_URL));
		dataSource.setUsername(env.getProperty(KEY_DATASOURCE_USERNAME));
		dataSource.setPassword(env.getProperty(KEY_DATASOURCE_PASSWORD));
		
		return dataSource;
	}
	
	// EntityManagerFactory by dataSource
	@Bean LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) throws IOException {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setPackagesToScan(CarModel.class.getPackage().getName());
        entityManagerFactory.setJpaProperties(getHibernateProperties());
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        
		return entityManagerFactory;
	}

	// SessionFactory by EntityManagerFactory
	@Bean public HibernateJpaSessionFactoryBean getSessionFactory(EntityManagerFactory emf) {
		HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
		factory.setEntityManagerFactory(emf);

		return factory;
	}
	
	// EntityManager by EntityMangerFactory
	@Bean SharedEntityManagerBean entityManager(EntityManagerFactory emf) {
		SharedEntityManagerBean entityManager = new SharedEntityManagerBean();
		entityManager.setEntityManagerFactory(emf);
		
		return entityManager;
	}

	// TransactionManager by EntityManagerFactory 
	@Bean PlatformTransactionManager transactionManager(EntityManagerFactory emf, DataSource dataSource) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(emf);
		jpaTransactionManager.setDataSource(dataSource);
		
		return jpaTransactionManager;
	}
	
	private Properties getHibernateProperties() throws IOException {
		Properties prop = new Properties();
		Resource resource = new ClassPathResource(env.getProperty(KEY_HIBERNATE_PROPERTIES_LOCATION));
		
		if (resource.isReadable()) {
			prop.load(resource.getInputStream());
			// TRICK !!!
	        prop.put("hibernate.current_session_context_class", SpringSessionContext.class.getName()); 
		} else {
			throw new IllegalStateException(MessageFormat.format("{0} not readable", resource.getFilename()));
		}
		
		return prop;
	}
}
