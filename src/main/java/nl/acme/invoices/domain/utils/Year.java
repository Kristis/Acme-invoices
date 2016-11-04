package nl.acme.invoices.domain.utils;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by kristisvaskys on 04/11/2016.
 */
@NodeEntity
public class Year {

    @GraphId
    private Long Id;

    @Property
    private Integer year;

    public Year(Integer year) {
        this.year = year;
    }

    public Year() {
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

}
