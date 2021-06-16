package org.springMVChibernateCRUD.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springMVChibernateCRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "org.springMVChibernateCRUD")
public class AppConfigDatabase {

    // inject Environment object to get settings from classpath properties file
    @Autowired
    private Environment env;
//    @Autowired
    public AppConfigDatabase(Environment env) {
        this.env = env;
    }

    public AppConfigDatabase() {
    }

    // PROPERTIES for Hibernate config from classpath properties file - SAME for ANY IMPLEMENT

    private  Properties getHibernateProps() {
        Properties props = new Properties();
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        return props;
    }

    // DATASOURCE - SAME FOR ANY IMPLEMENTATION
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    //___________________________HIBERNATE IMPLEMENTATION_____________________________________________________________//


    // Hibernate LOCALSESSIONFACTORYBEAN - HIBERNATE data CONTEXT - but better use JPA
    // inject database and create session with spring orm hibernate 5
    @Bean
    @Qualifier(value = "getSessionFactory")
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean.setHibernateProperties(getHibernateProps());
        factoryBean.setAnnotatedClasses(User.class);
        return factoryBean;
    }

    // TRANSACTION MANAGER Hibernate DataSource access within a transaction i.e. plain JDBC.

    //TODO how to keep 2 TRANSACTION MANAGERS???


//    @Bean(value = "HibernateTransactionManager")
//    public HibernateTransactionManager getTransactionManager() {
//        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
//        hibernateTransactionManager.setSessionFactory(getSessionFactory().getObject());
//        return hibernateTransactionManager;
//    }

    //_______________________ JPA EntityManager IMPLEMENTATION_________________________________________________________//

    //1 - create Hibernate for JPA Adapter для обслуживания менеджера транзакций
    @Bean
    public JpaVendorAdapter getHibernateAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    // 2 - replace Hibernate "native" SessionFactory with JPA EntityManagerFactory

    @Bean(name = "getEMF")
    public LocalContainerEntityManagerFactoryBean getEMF() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(getDataSource());

        emf.setPackagesToScan(new String[]{"org.springMVChibernateCRUD"});
//        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//        emf.setPersistenceUnitName();
        emf.setJpaVendorAdapter(getHibernateAdapter());
        emf.setJpaProperties(getHibernateProps());
        return emf;
    }

    // 3 - JPA transaction manager
    @Bean(name = "JpaTransactionManager")
    public JpaTransactionManager getJpaTransactionManger(@Qualifier("getEMF") EntityManagerFactory emf) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(emf);
        return jpaTransactionManager;
    }
}
