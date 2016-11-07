package nl.acme.invoices;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by kristisvaskys on 28/10/2016.
 */
@Configuration
@EnableNeo4jRepositories("nl.acme.invoices.repository")
@EnableTransactionManagement
@ComponentScan("nl.acme.invoices")
public class ApplicationConfig extends Neo4jConfiguration {
    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory("nl.acme.invoices.domain");
    }

    @Override
    @Bean
    public Session getSession() throws Exception {
        return super.getSession();
    }
}
