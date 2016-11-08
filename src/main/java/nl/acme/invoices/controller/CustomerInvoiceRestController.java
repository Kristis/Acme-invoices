package nl.acme.invoices.controller;

import nl.acme.invoices.domain.invoice.CustomerInvoice;
import nl.acme.invoices.domain.invoice.InvoiceType;
import nl.acme.invoices.repository.InvoicesRepository;
import nl.acme.invoices.services.CustomerInvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Invoice rest controller.
 * This controller return basic data about customer.
 * Also, generate random invoices for customers;
 * <p>
 * Created by kristisvaskys on 03/11/2016.
 */
@RestController
@RequestMapping("/sysapi/v1.0/")
public class CustomerInvoiceRestController {

    private static final String SHOP_FILTER = "shop";

    @Autowired
    private InvoicesRepository invoicesRepository;

    @Autowired
    private CustomerInvoicesService customerInvoicesService;

    /**
     * Return all invoices for all customers.
     * <p>
     * TODO: remove for PROD usage, only for testing
     *
     * @return list of CustomerInvoice
     */
    @RequestMapping(value = "/invoices/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Iterable<CustomerInvoice> list() {
        return invoicesRepository.findAll();
    }

    /**
     * Generate random invoices for customers
     *
     * @return list of CustomerInvoice
     */
    @RequestMapping(value = "/invoices/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Iterable<CustomerInvoice> generateInvoices() {
        return customerInvoicesService.generateInvoices();
    }

    /**
     * Search invoices by customerID
     *
     * @param customerId
     * @return list of CustomerInvoice
     */
    @RequestMapping(value = "/invoices/", params = {"customerId"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Iterable<CustomerInvoice> findByCustomerId(@RequestParam("customerId") String customerId) {
        Long id = Long.valueOf(customerId);
        if (id != null) {
            return invoicesRepository.findByCustomerId(id);
        } else {
            return null;
        }
    }

    /**
     * Search invoices by CustomerId and Customer addressId
     *
     * @param customerId
     * @param addressId
     * @return list of CustomerInvoice
     */
    @RequestMapping(value = "/invoices/", params = {"customerId", "addressId"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Iterable<CustomerInvoice> findByCustomerIdAddressId(@RequestParam("customerId") String customerId, @RequestParam("addressId") String addressId) {
        Long id = Long.valueOf(customerId);
        return invoicesRepository.findByCustomerIdAddressId(id, addressId);
    }

    /**
     * Search invoices by CustomerId and invoice month.
     * Note: Searching in current year
     *
     * @param customerId
     * @param month
     * @return list of CustomerInvoice
     */
    @RequestMapping(value = "/invoices/", params = {"customerId", "month"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Iterable<CustomerInvoice> findByCustomerIdMonth(@RequestParam("customerId") String customerId, @RequestParam("month") String month) {
        Long id = Long.valueOf(customerId);
        Integer monthInt = Integer.valueOf(month);
        LocalDate localDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return invoicesRepository.findByCustomerIdMonth(id, localDate.getYear(), monthInt);
    }

    /**
     * Search by invoices by CustomerId and invoice month and invoices type
     *
     * @param customerId
     * @param filter
     * @param month
     * @return list of CustomerInvoice
     */
    @RequestMapping(value = "/invoices/", params = {"customerId", "filter", "month"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Iterable<CustomerInvoice> findByCustomerIdMonthFilter(@RequestParam("customerId") String customerId, @RequestParam("filter") String filter, @RequestParam("month") String month) {
        Long id = Long.valueOf(customerId);
        Integer monthInt = Integer.valueOf(month);
        LocalDate localDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (!StringUtils.isEmpty(filter) && filter.equals(SHOP_FILTER)) {
            return invoicesRepository.findByCustomerIdFilterMonth(id, localDate.getYear(), monthInt, InvoiceType.SHOP_PURCHASE);
        } else {
            return null;
        }

    }

    public void setCustomerInvoicesServiceImpl(CustomerInvoicesService customerInvoicesService) {
        this.customerInvoicesService = customerInvoicesService;
    }

}
