package nl.acme.invoices.services;

import nl.acme.invoices.domain.invoice.CustomerInvoice;

import java.util.List;

/**
 * Created by kristisvaskys on 08/11/2016.
 */
public interface CustomerInvoicesService {
    List<CustomerInvoice> generateInvoices();
}
