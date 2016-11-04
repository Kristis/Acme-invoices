package nl.acme.invoices.domain.invoice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.acme.invoices.domain.customer.Customer;
import nl.acme.invoices.domain.utils.Month;
import nl.acme.invoices.domain.utils.Year;
import org.neo4j.ogm.annotation.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by kristisvaskys on 03/11/2016.
 */
@NodeEntity(label = "CustomerInvoices")
public class CustomerInvoice {

    @Id
    @GraphId
    @JsonProperty("id")
    private Long id;

    @Property
    private Long invoiceNumber;

    @Relationship(type = "BILL_FOR", direction = Relationship.OUTGOING)
    private Customer customer;

    @Property
    private String invoiceId;

    @Property
    private InvoiceType invoicesType;

    @Property
    private InvoiceStatus invoiceStatus;

    @Property
    private String invoiceTypeLocalized;

    @Property
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date invoiceDate;

    @Property
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paymentDueDate;

    @Property
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Property
    private Date endDate;

    @Property
    private String periodDescription;

    @Property
    private Double amount;

    @Property
    private Double vat;

    @Property
    private Double totalAmount;

    @Relationship(type = "BILL_YEAR", direction = Relationship.OUTGOING)
    @Transient
    private Year year;

    @Transient
    @Relationship(type = "BILL_MONTH", direction = Relationship.OUTGOING)
    private Month month;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public InvoiceType getInvoicesType() {
        return invoicesType;
    }

    public void setInvoicesType(InvoiceType invoicesType) {
        this.invoicesType = invoicesType;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoiceTypeLocalized() {
        return invoiceTypeLocalized;
    }

    public void setInvoiceTypeLocalized(String invoiceTypeLocalized) {
        this.invoiceTypeLocalized = invoiceTypeLocalized;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(Date paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public Long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPeriodDescription() {
        return periodDescription;
    }

    public void setPeriodDescription(String periodDescription) {
        this.periodDescription = periodDescription;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }
}
