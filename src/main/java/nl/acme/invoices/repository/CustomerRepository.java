package nl.acme.invoices.repository;

import nl.acme.invoices.domain.customer.Customer;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

/**
 * Repository for searching Customers by different parameters
 * <p>
 * Created by kristisvaskys on 04/11/2016.
 */
@RepositoryRestController
public interface CustomerRepository extends GraphRepository<Customer> {

    /**
     * Find all customers without invoices
     *
     * @return list of Customers
     */
    @Query("MATCH (c:Customer)\n" +
            "WHERE not ((:CustomerInvoices)-[:BILL_FOR]->(c)) \n" +
            "RETURN c;")
    List<Customer> findAllCustomersWithoutInvoices();
}
