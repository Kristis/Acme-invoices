package nl.acme.invoices.repository;

import nl.acme.invoices.domain.invoice.CustomerInvoice;
import nl.acme.invoices.domain.invoice.InvoiceType;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

/**
 * Search invoices by different parameters
 * <p>
 * Created by kristisvaskys on 03/11/2016.
 */
@RepositoryRestController
public interface InvoicesRepository extends GraphRepository<CustomerInvoice> {

    /**
     * Search by CustomerID
     *
     * @param customerId
     * @return list of CustomerInvoices
     */
    @Query("MATCH p=(ci:CustomerInvoices)-[r:BILL_FOR]->(c:Customer)\n" +
            "where c.customerID = {0}\n" +
            "RETURN p")
    List<CustomerInvoice> findByCustomerId(Long customerId);

    /**
     * Search by CustomerID and AddressID
     *
     * @param customerId
     * @param addressId
     * @return list of CustomerInvoices
     */
    @Query("MATCH p=(ci:CustomerInvoices)-[r:BILL_FOR]->(c:Customer)\n" +
            "where c.customerID = {0} and c.addressID = {1}\n" +
            "RETURN p")
    List<CustomerInvoice> findByCustomerIdAddressId(Long customerId, String addressId);

    /**
     * Search by CustomerId and AddressId and Month
     *
     * @param customerID
     * @param addressId
     * @param year       - current system year
     * @param month
     * @return list of CustomerInvoices
     */
    @Query("Match (ci:CustomerInvoices)-[rel:BILL_FOR]->(c:Customer)\n" +
            "where c.customerID = {0} and c.addressId = {1} and ci.year = {2}  and ci.month= {3}\n" +
            "return ci;")
    List<CustomerInvoice> findByCustomerIdAddressIdMonth(Long customerID, String addressId, Integer year, Integer month);

    /**
     * Search by customerId and month
     *
     * @param customerID
     * @param year       - current system year
     * @param month
     * @return list of CustomerInvoices
     */
    @Query("Match (ci:CustomerInvoices)-[rel:BILL_FOR]->(c:Customer)\n" +
            "where c.customerID = {0} and ci.year = {1}  and ci.month= {2}\n" +
            "return ci;")
    List<CustomerInvoice> findByCustomerIdMonth(Long customerID, Integer year, Integer month);

    /**
     * Search by customerId and month and invoice type
     *
     * @param customerID
     * @param year
     * @param month
     * @param type
     * @return list of CustomerInvoices
     */
    @Query("Match (ci:CustomerInvoices)-[rel:BILL_FOR]->(c:Customer)\n" +
            "where c.customerID = {0} and ci.year = {1}  and ci.month= {2} and ci.invoiceType = {3}\n" +
            "return ci;")
    List<CustomerInvoice> findByCustomerIdFilterMonth(Long customerID, Integer year, Integer month, InvoiceType type);


}
