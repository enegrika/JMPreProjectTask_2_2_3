package org.springMVChibernateCRUD.config;

import org.springMVChibernateCRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "org.springMVChibernateCRUD")
public class AppConfigDatabase {

    // to get properties from file we use Environment object from Spring
    @Autowired
    private Environment env;

    // get properties from classpath file
    private Properties getHibernateProps() {
        Properties props = new Properties();
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        return props;
    }

    // get JDBC DATABASE HERE
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl("db.url");
        dataSource.setUsername("db.username");
        dataSource.setPassword("db.password");
        return dataSource;
    }

    // inject database and create session with spring orm hibernate 5
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(getDataSource());
        sessionFactoryBean.setHibernateProperties(getHibernateProps());
        sessionFactoryBean.setAnnotatedClasses(User.class);
        return sessionFactoryBean;
    }

    // binds hibernate session from factory to th thread,
    // binds a Hibernate Session from the specified factory to the thread,
    // potentially allowing for one thread-bound Session per factory.
    // This transaction manager is appropriate for applications
    // that use a single Hibernate SessionFactory for transactional data access,
    // but it also supports direct DataSource access within a transaction i.e. plain JDBC.
    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }


}
