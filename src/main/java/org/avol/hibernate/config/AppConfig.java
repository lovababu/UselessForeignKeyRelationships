package org.avol.hibernate.config;

import oracle.jdbc.driver.OracleDriver;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Durga on 8/28/2015.
 */

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:db-config.properties")
@ComponentScan(basePackages = {"org.avol.hibernate.service", "org.avol.hibernate.repository"})
public class AppConfig {

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${hibernate.generate_statistics}")
    private String generateStatistics;

    @Value("${jdbc.conn.url}")
    private String connUrl;

    @Value("${db.username}")
    private String userName;

    @Value("${db.password}")
    private String password;

    /**
     * This static place holder configures bean registration mandatory to load the properties.
     * Alternatively you can make use of Environment class to fetch the values from propertey file.
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setHibernateProperties(getHibernateProperties());
        localSessionFactoryBean.setPackagesToScan("org.avol.hibernate.entities");
        localSessionFactoryBean.afterPropertiesSet();
        return localSessionFactoryBean.getObject();

    }

    public DataSource dataSource() {
        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
        simpleDriverDataSource.setDriverClass(OracleDriver.class);
        simpleDriverDataSource.setUrl(connUrl);
        simpleDriverDataSource.setUsername(userName);
        simpleDriverDataSource.setPassword(password);
        return simpleDriverDataSource;
    }

    public Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.cache.use_second_level_cache", false);
        properties.put("hibernate.generate_statistics", generateStatistics);
        return properties;
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager() throws Exception {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory());
        return transactionManager;
    }
}
