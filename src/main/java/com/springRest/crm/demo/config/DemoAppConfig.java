package com.springRest.crm.demo.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.springRest.crm.demo")
@PropertySource("classpath:persistence-msql.properties")
@EnableTransactionManagement
public class DemoAppConfig {
	
	// set up a variable to hold data read from mysql properties
	@Autowired
	private Environment env;

	// set up a logger for diagnostics

	private Logger logger = Logger.getLogger(getClass().getName());

	//  Set up myDataSourceBean
		@Bean
		public DataSource myDataSource() {

			// create the connection pool
			ComboPooledDataSource DataSource = new ComboPooledDataSource();

			// set the JDBC driver
			try {
				DataSource.setDriverClass(env.getProperty("jdbc.driver"));
			} catch (PropertyVetoException e) {

				e.printStackTrace();
			}

			// for sanity let's check the log url and user
			logger.info(">>>>>>>> jdbc.url " + env.getProperty("jdbc.url"));
			logger.info(">>>>>>>> jdbc.user " + env.getProperty("jdbc.user"));

			// set database connection props
			DataSource.setJdbcUrl(env.getProperty("jdbc.url"));
			DataSource.setUser(env.getProperty("jdbc.user"));
			DataSource.setPassword(env.getProperty("jdbc.password"));

			// set connection pool props
			DataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
			DataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
			DataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
			DataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

			return DataSource;

		}
		
		
		
		public Properties getHibernateProperties() {
			
			Properties pros = new Properties();
			
			pros.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
			pros.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));		
			
			return pros ;
		}
		// need a helper method
		// read environment property and convert to int

		private int getIntProperty(String propName) {

			String propVal = env.getProperty(propName);

			// now convert to int
			int intPropVal = Integer.parseInt(propVal);

			return intPropVal;
		}
		
		// we set up a LocalSessionFactoryBean for use with Hibernate.
		
		@Bean
		public LocalSessionFactoryBean sessionFactory() {
			
			LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
			
			// set the properties for the sessionFactory
			sessionFactory.setDataSource(myDataSource());
			sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
			sessionFactory.setHibernateProperties(getHibernateProperties());
			
			return sessionFactory ;
		}
		
		// set up the HibernateTransactionManager
		@Bean
		@Autowired
		public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
		{
			
			//seet up transaction manager based on sessionFactory
			HibernateTransactionManager txManager = new HibernateTransactionManager();
			txManager.setSessionFactory(sessionFactory);
			return txManager;
		}
		
		
}
