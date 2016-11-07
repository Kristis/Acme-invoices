package nl.acme.invoices.config;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Application configuration.
 * Connection to Neo4j database.
 * <p>
 * Created by kristisvaskys on 28/10/2016.
 */
@SpringBootApplication
@EnableTransactionManagement
@EntityScan(basePackages = "nl.acme.invoices")
@Configuration
@EnableNeo4jRepositories("nl.acme.invoices.repository")
public class ApplicationConfig extends Neo4jConfiguration {

    private static final String DB_URI_KEY = "spring.data.neo4j.URI";

    private static final String DB_USER_PASS_KEY = "spring.data.neo4j.password";

    private static final String DB_USER_NAME_KEY = "spring.data.neo4j.username";

    private static final String DRIVER_NAME = "org.neo4j.ogm.drivers.http.driver.HttpDriver";

    @Autowired
    private Environment env;

    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory(getConfiguration(), "nl.acme.invoices.domain");
    }

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration()
                .setDriverClassName(DRIVER_NAME)
                .setURI(env.getProperty(DB_URI_KEY))
                .setCredentials(env.getProperty(DB_USER_NAME_KEY), env.getProperty(DB_USER_PASS_KEY));

        return config;
    }

    @Override
    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session getSession() throws Exception {
        return super.getSession();
    }
}
