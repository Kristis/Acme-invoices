package nl.acme.invoices.domain.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.Id;

/**
 * Customer entity. Store basic information.
 * <p>
 * Created by kristisvaskys on 03/11/2016.
 */
@NodeEntity
public class Customer {

    @GraphId
    @Id
    @JsonProperty("id")
    private Long Id;

    @Property(name = "customerID")
    private Long customerId;

    @Property
    private String firstName;

    @Property
    private String middleName;

    @Property
    private String lastName;

    @Property
    private String email;

    @Property(name = "addressID")
    private String addressId;

    @Relationship(type = "LIVING_IN", direction = Relationship.OUTGOING)
    private CustomerAddress customerAddress;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}
