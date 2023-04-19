package com.xworkz.nayana.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.xworkz.nayana")
public class JewelleryConfiguration {
	public JewelleryConfiguration() {
		System.out.println("Created " + this.getClass().getSimpleName());
	}

	// used to process html form which is enctype
	@Bean
	public MultipartResolver multipartResolver() {
		System.out.println("registering multipartResolver");
		return new StandardServletMultipartResolver();
	}

	@Bean
	public ViewResolver viewResolver() {
		System.out.println("Registering ViewResolver");
		return new InternalResourceViewResolver("/", ".jsp");
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean l() {
		return new LocalContainerEntityManagerFactoryBean();
	}

//	@Bean
//	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
//		System.out.println("Registering LocalContainerEntityManagerFactoryBean");
//		LocalContainerEntityManagerFactoryBean bean=new LocalContainerEntityManagerFactoryBean();
//		bean.setPackagesToScan("com.xworkz.nayana_xworkz.entity");//Persistence.xml
//		bean.setDataSource(dataSource());
//		bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//		return bean;
//	}

//	public DataSource dataSource() {
//		System.out.println("Registering datasource...");
//		DriverManagerDataSource dataSource=new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://localhost:3306/bingo");
//		dataSource.setPassword("Xworkzodc@123");
//		dataSource.setUsername("root");
//		return dataSource;
//	}
}
