package org.example.config;

import lombok.Getter;
import org.example.entity.Client;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Properties;

public class HibernateUtil {

    private static final HibernateUtil INSTANCE;

    @Getter
    private final SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }

    private HibernateUtil() {
        Properties properties = new Properties();
        properties.put("hibernate.connection.provider_class", "org.hibernate.hikaricp.internal.HikariCPConnectionProvider");
        properties.put("hibernate.hikari.dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        properties.put("hibernate.hikari.dataSource.url", System.getenv("GOIT_DB_URL"));
        properties.put("hibernate.hikari.dataSource.user", System.getenv("GOIT_DB_USER"));
        properties.put("hibernate.hikari.dataSource.password", System.getenv("GOIT_DB_PASS"));
        properties.put("hibernate.hikari.maximumPoolSize", "10");
        properties.put("hibernate.hikari.minimumIdle", "2");
        properties.put("hibernate.hikari.idleTimeout", "30000");
        properties.put("hibernate.hikari.connectionTimeout", "20000");
        properties.put("hibernate.hikari.poolName", "MyHikariCPPool");
        properties.put("hibernate.hikari.maxLifetime", "1800000");

        sessionFactory = new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(Client.class)
                .buildSessionFactory();
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }
}