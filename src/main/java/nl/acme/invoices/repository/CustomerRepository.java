package nl.acme.invoices.repository;

import nl.acme.invoices.domain.customer.Customer;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

/**
 * Created by kristisvaskys on 04/11/2016.
 */
@RepositoryRestController
public interface CustomerRepository extends GraphRepository<Customer> {

    @Query("MATCH (c:Customer)\n" +
            "MATCH (ci:CustomerInvoices)\n" +
            "where (c) <-[:BILL_FOR]-(ci) and ci is null\n" +
            "RETURN c")
    List<Customer> findAllCustomersWithoutInvoices();
}
