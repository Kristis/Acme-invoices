package nl.acme.invoices.config;

import nl.acme.invoices.domain.customer.Customer;
import nl.acme.invoices.domain.customer.CustomerAddress;
import nl.acme.invoices.domain.invoice.CustomerInvoice;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * Created by kristisvaskys on 04/11/2016.
 */
@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
        config.exposeIdsFor(Customer.class);
        config.exposeIdsFor(CustomerInvoice.class);
        config.exposeIdsFor(CustomerAddress.class);
    }
}
