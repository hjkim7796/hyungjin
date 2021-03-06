package com.hyungjin.mvc.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

import java.net.URL;
 
@Configuration
@EnableCaching
@EnableTransactionManagement
@ComponentScan("com.hyungjin.mvc")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.hyungjin.mvc.repository")
public class WebAppConfig {
	
    @Autowired
    Environment env;
 
    @Bean
    public DataSource hikariDataSource() {
    	HikariConfig config = new HikariConfig("/hikari.properties");
    	HikariDataSource ds = new HikariDataSource(config);
	    return ds;
	}
	
    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }
 
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		//Properties jpaProperties = new Properties();

        //jpaProperties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        //jpaProperties.setProperty("net.sf.ehcache.configurationResourceName","/ehcache.xml");
        
		entityManagerFactoryBean.setDataSource(hikariDataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		//entityManagerFactoryBean.setJpaProperties(jpaProperties);
		return entityManagerFactoryBean;
	}
	
  	@Bean(destroyMethod="shutdown")
    public net.sf.ehcache.CacheManager ehCacheManager() {
  		URL url = getClass().getResource("/ehcache.xml");
  		return net.sf.ehcache.CacheManager.newInstance(url);
    }
  	
    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }
    
  	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
}