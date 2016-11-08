package nl.acme.invoices.controllers;

import nl.acme.invoices.controller.CustomerInvoiceRestController;
import nl.acme.invoices.domain.customer.Customer;
import nl.acme.invoices.domain.invoice.CustomerInvoice;
import nl.acme.invoices.domain.invoice.InvoiceType;
import nl.acme.invoices.domain.utils.Month;
import nl.acme.invoices.domain.utils.Year;
import nl.acme.invoices.repository.InvoicesRepository;
import nl.acme.invoices.services.CustomerInvoicesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by kristisvaskys on 08/11/2016.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CustomerInvoiceRestController.class)
public class CustomerInvoicesControllerTests {

    private static final String FIND_BY_CUST_ID = "[{\"invoiceNumber\":343,\"customer\":{\"customerId\":1,\"firstName\":\"Test\",\"middleName\":null,\"lastName\":null,\"email\":null,\"addressId\":null,\"customerAddress\":null,\"id\":null},\"invoiceId\":null,\"invoicesType\":null,\"invoiceStatus\":null,\"invoiceTypeLocalized\":null,\"invoiceDate\":null,\"paymentDueDate\":null,\"startDate\":null,\"endDate\":null,\"periodDescription\":null,\"amount\":null,\"vat\":null,\"totalAmount\":null,\"year\":null,\"month\":null,\"id\":null}]";
    private static final String FIND_BY_CUST_ID_MONTH = "[{\"invoiceNumber\":343,\"customer\":{\"customerId\":1,\"firstName\":\"Test\",\"middleName\":null,\"lastName\":null,\"email\":null,\"addressId\":null,\"customerAddress\":null,\"id\":null},\"invoiceId\":null,\"invoicesType\":null,\"invoiceStatus\":null,\"invoiceTypeLocalized\":null,\"invoiceDate\":null,\"paymentDueDate\":null,\"startDate\":null,\"endDate\":null,\"periodDescription\":null,\"amount\":null,\"vat\":null,\"totalAmount\":null,\"year\":{\"year\":2016,\"id\":null},\"month\":{\"month\":1,\"year\":{\"year\":2016,\"id\":null},\"id\":null},\"id\":null}]";
    private static final String FIND_BY_CUST_ID_MONTH_FILTER = "[{\"invoiceNumber\":343,\"customer\":{\"customerId\":1,\"firstName\":\"Test\",\"middleName\":null,\"lastName\":null,\"email\":null,\"addressId\":null,\"customerAddress\":null,\"id\":null},\"invoiceId\":null,\"invoicesType\":\"SHOP_PURCHASE\",\"invoiceStatus\":null,\"invoiceTypeLocalized\":null,\"invoiceDate\":null,\"paymentDueDate\":null,\"startDate\":null,\"endDate\":null,\"periodDescription\":null,\"amount\":null,\"vat\":null,\"totalAmount\":null,\"year\":{\"year\":2016,\"id\":null},\"month\":{\"month\":1,\"year\":{\"year\":2016,\"id\":null},\"id\":null},\"id\":null}]";
    @Autowired
    private MockMvc mvc;
    @MockBean
    private InvoicesRepository invoicesRepository;
    @MockBean
    private CustomerInvoicesService customerInvoicesService;

    @Test
    public void shouldFindInvoicesByCustomerId() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1l);
        customer.setFirstName("Test");
        CustomerInvoice invoice = new CustomerInvoice();
        invoice.setCustomer(customer);
        invoice.setInvoiceNumber(343l);
        List<CustomerInvoice> list = new ArrayList<>();
        list.add(invoice);

        given(this.invoicesRepository.findByCustomerId(1l)).willReturn(list);

        this.mvc.perform(get("/sysapi/v1.0/invoices/?customerId=1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(FIND_BY_CUST_ID));

    }

    @Test
    public void shouldFindInvoicesByCustomerIdAndMonth() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1l);
        customer.setFirstName("Test");
        CustomerInvoice invoice = new CustomerInvoice();
        invoice.setCustomer(customer);
        invoice.setInvoiceNumber(343l);
        invoice.setYear(new Year(2016));
        invoice.setMonth(new Month(1, invoice.getYear()));
        List<CustomerInvoice> list = new ArrayList<>();
        list.add(invoice);

        given(this.invoicesRepository.findByCustomerIdMonth(1l, 2016, 1)).willReturn(list);

        this.mvc.perform(get("/sysapi/v1.0/invoices/?customerId=1&month=1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(FIND_BY_CUST_ID_MONTH));

    }

    @Test
    public void shouldFindInvoicesByCustomerIdAndMonthAndFilter() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1l);
        customer.setFirstName("Test");
        CustomerInvoice invoice = new CustomerInvoice();
        invoice.setCustomer(customer);
        invoice.setInvoiceNumber(343l);
        invoice.setYear(new Year(2016));
        invoice.setMonth(new Month(1, invoice.getYear()));
        invoice.setInvoicesType(InvoiceType.SHOP_PURCHASE);
        List<CustomerInvoice> list = new ArrayList<>();
        list.add(invoice);

        given(this.invoicesRepository.findByCustomerIdFilterMonth(1l, 2016, 1, InvoiceType.SHOP_PURCHASE)).willReturn(list);

        this.mvc.perform(get("/sysapi/v1.0/invoices/?customerId=1&filter=shop&month=1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(FIND_BY_CUST_ID_MONTH_FILTER));

    }

    @Test
    public void shouldGenerateInvoices() throws Exception {

        Customer customer = new Customer();
        customer.setCustomerId(1l);
        customer.setFirstName("Test");
        CustomerInvoice invoice = new CustomerInvoice();
        invoice.setCustomer(customer);
        invoice.setInvoiceNumber(343l);
        invoice.setYear(new Year(2016));
        invoice.setMonth(new Month(1, invoice.getYear()));
        invoice.setInvoicesType(InvoiceType.SHOP_PURCHASE);
        List<CustomerInvoice> list = new ArrayList<>();
        list.add(invoice);

        given(this.customerInvoicesService.generateInvoices()).willReturn(list);

        this.mvc.perform(post("/sysapi/v1.0/invoices/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(FIND_BY_CUST_ID_MONTH_FILTER));
    }
}
