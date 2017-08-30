package com.seven.virtual_currency_website.data;

import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.seven.virtual_currency_website")
@EnableTransactionManagement
@EnableJpaRepositories("com.seven.virtual_currency_website.dao")
@PropertySource(value = { "classpath:/application.properties" })
public class App {

	private DataSource preferentialDataSource;

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "connection.driver_class";
	private static final String PROPERTY_NAME_DATABASE_URL = "connection.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "connection.username";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "connection.password";
	private static final String PROPERTY_NAME_DATABASE_DDL = "hbm2ddl.auto";

	private static final String ENTITY_PACKAGE = "com.seven.virtual_currency_website.domain";

	@Autowired
	private Environment environment;

	private EntityManager entityManager;

	protected EntityManagerFactory entityManagerFactory;

	private PlatformTransactionManager annotationDrivenTransactionManager;

	@Bean(name = "entityManagerFactory")
	public synchronized EntityManagerFactory entityManagerFactory() {
		if (this.entityManagerFactory == null) {
			LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
			HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
			hibernateJpaVendorAdapter.setGenerateDdl(true);
			hibernateJpaVendorAdapter.setShowSql(true);
			factoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
			factoryBean.setDataSource(this.getPreferentialDataSource());
			final Properties jpaProperties = new Properties();
			jpaProperties.put(PROPERTY_NAME_DATABASE_DDL,
					this.environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DDL));
			jpaProperties.put("hibernate.physical_naming_strategy", PhysicalNamingStrategyImpl.class);
			factoryBean.setJpaProperties(jpaProperties);
			factoryBean.setPackagesToScan(ENTITY_PACKAGE);
			factoryBean.afterPropertiesSet();
			this.entityManagerFactory = factoryBean.getObject();
		}
		return this.entityManagerFactory;
	}

	// 事务管理
	@Bean(name = "transactionManager")
	public synchronized PlatformTransactionManager annotationDrivenTransactionManager() {
		if (this.annotationDrivenTransactionManager == null) {
//			final JpaTransactionManager jpaTxManager = new JpaTransactionManager();
//			jpaTxManager.setEntityManagerFactory(this.entityManagerFactory.getNativeEntityManagerFactory());
//			jpaTxManager.setJpaDialect(new EclipseLinkJpaDialect());
			this.annotationDrivenTransactionManager = new JpaTransactionManager(entityManagerFactory());
		}
		return this.annotationDrivenTransactionManager;
	}

	@Bean
	public synchronized DataSource getPreferentialDataSource() {
		if (this.preferentialDataSource == null) {
			final DriverManagerDataSource dataSourceConfig = new DriverManagerDataSource();
			dataSourceConfig.setDriverClassName(this.environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
			dataSourceConfig.setUrl(this.environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
			dataSourceConfig.setUsername(this.environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
			dataSourceConfig.setPassword(this.environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
			this.preferentialDataSource = dataSourceConfig;
		}
		return this.preferentialDataSource;
	}

	@Bean
	@Primary
	public synchronized EntityManager entityManager() {
		if (this.entityManager == null) {
//			this.entityManager = SharedEntityManagerCreator
//					.createSharedEntityManager(this.entityManagerFactory.getNativeEntityManagerFactory());
			this.entityManager = entityManagerFactory().createEntityManager();
		}
		return this.entityManager;
	}

//	@Bean
//	public JdbcTemplate jdbcTemplate() throws SQLException {
//		return new JdbcTemplate(this.getPreferentialDataSource());
//	}
//
//	@Bean
//	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() throws SQLException {
//		return new NamedParameterJdbcTemplate(this.getPreferentialDataSource());
//	}

}
