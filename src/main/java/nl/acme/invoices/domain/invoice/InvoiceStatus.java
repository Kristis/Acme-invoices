package nl.acme.invoices.domain.invoice;

/**
 * Invoices status.
 * NEW - just created
 * IN_PROGRESS - under payment process
 * COMPLETED - payed
 * <p>
 * Created by kristisvaskys on 03/11/2016.
 */
public enum InvoiceStatus {
    NEW,
    IN_PROGRESS,
    COMPLETED

}
