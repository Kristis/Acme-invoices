package nl.acme.invoices.services;

import nl.acme.invoices.domain.customer.Customer;
import nl.acme.invoices.domain.invoice.CustomerInvoice;
import nl.acme.invoices.repository.CustomerRepository;
import nl.acme.invoices.repository.InvoicesRepository;
import nl.acme.invoices.utils.CustomerInvoiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kristisvaskys on 04/11/2016.
 */
@Service
public class CustomerInvoicesServiceImpl implements CustomerInvoicesService {

    Logger logger = LoggerFactory.getLogger(CustomerInvoicesServiceImpl.class);

    @Autowired
    private InvoicesRepository invoicesRepository;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Generate random invoices for customers
     * <p>
     * TODO: Split in batch. Maybe even in batch jobs.
     *
     * @return list of generated CustomerInvoices
     */
    @Override
    public List<CustomerInvoice> generateInvoices() {
        List<Customer> customers = customerRepository.findAllCustomersWithoutInvoices();
        logger.debug("{} customers was found!", customers.size());

        List<CustomerInvoice> invoices = new ArrayList<>();

        for (Customer c : customers) {
            CustomerInvoice invoice = CustomerInvoiceUtils.createCustomerInvoice(c);
            invoicesRepository.save(invoice);
            invoices.add(invoice);
        }

        logger.debug("{} invoices was created!", invoices.size());
        return invoices;
    }


}
