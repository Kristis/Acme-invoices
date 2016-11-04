package nl.acme.invoices.utils;

import nl.acme.invoices.domain.customer.Customer;
import nl.acme.invoices.domain.invoice.CustomerInvoice;
import nl.acme.invoices.domain.invoice.InvoiceStatus;
import nl.acme.invoices.domain.invoice.InvoiceType;
import nl.acme.invoices.domain.utils.Month;
import nl.acme.invoices.domain.utils.Year;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

/**
 * Created by kristisvaskys on 04/11/2016.
 */
public class CustomerInvoiceUtils {

    private static final Double VAT = 0.20;

    public static CustomerInvoice createCustomerInvoice(Customer customer) {
        Random random = new Random();
        CustomerInvoice invoice = new CustomerInvoice();
        invoice.setAmount(random.nextDouble());
        invoice.setVat(VAT);
        invoice.setCustomer(customer);
        invoice.setTotalAmount(invoice.getAmount() + (invoice.getAmount() * VAT));
        invoice.setInvoiceNumber(random.nextLong());
        invoice.setStartDate(new Date());
        invoice.setEndDate(new Date());
        invoice.setPaymentDueDate(new Date());
        invoice.setInvoiceId(new BigInteger(130, random).toString(32));
        invoice.setPeriodDescription(" Random invoice");
        invoice.setInvoicesType(InvoiceType.ADVANCE_PAYMENT);
        invoice.setInvoiceStatus(InvoiceStatus.NEW);
        invoice.setInvoiceTypeLocalized("test");
        invoice.setInvoiceDate(new Date());
        invoice.setYear(new Year(2016));
        invoice.setMonth(new Month(11, invoice.getYear()));
        return invoice;
    }
}
