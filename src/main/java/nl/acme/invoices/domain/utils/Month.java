package nl.acme.invoices.domain.utils;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Split invoices in months.
 * Every month have relationship with month
 * <p>
 * Created by kristisvaskys on 04/11/2016.
 */
@NodeEntity
public class Month {

    @GraphId
    private Long Id;

    @Property
    private Integer month;


    @Relationship(type = "IN_YEAR", direction = Relationship.OUTGOING)
    private Year year;

    public Month(Integer month, Year year) {
        this.month = month;
        this.year = year;
    }

    public Month() {
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
