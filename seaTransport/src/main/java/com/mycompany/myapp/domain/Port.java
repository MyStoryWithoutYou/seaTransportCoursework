package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Port.
 */
@Entity
@Table(name = "port")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Port implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Min(value = 0)
    @Column(name = "loading_port_latitude", nullable = false)
    private Integer loadingPortLatitude;

    @NotNull
    @Min(value = 0)
    @Column(name = "loading_port_longitude", nullable = false)
    private Integer loadingPortLongitude;

    @NotNull
    @Min(value = 0)
    @Column(name = "shipment_port_latitude", nullable = false)
    private Integer shipmentPortLatitude;

    @NotNull
    @Min(value = 0)
    @Column(name = "shipment_port_longitude", nullable = false)
    private Integer shipmentPortLongitude;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Port id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getLoadingPortLatitude() {
        return this.loadingPortLatitude;
    }

    public Port loadingPortLatitude(Integer loadingPortLatitude) {
        this.loadingPortLatitude = loadingPortLatitude;
        return this;
    }

    public void setLoadingPortLatitude(Integer loadingPortLatitude) {
        this.loadingPortLatitude = loadingPortLatitude;
    }

    public Integer getLoadingPortLongitude() {
        return this.loadingPortLongitude;
    }

    public Port loadingPortLongitude(Integer loadingPortLongitude) {
        this.loadingPortLongitude = loadingPortLongitude;
        return this;
    }

    public void setLoadingPortLongitude(Integer loadingPortLongitude) {
        this.loadingPortLongitude = loadingPortLongitude;
    }

    public Integer getShipmentPortLatitude() {
        return this.shipmentPortLatitude;
    }

    public Port shipmentPortLatitude(Integer shipmentPortLatitude) {
        this.shipmentPortLatitude = shipmentPortLatitude;
        return this;
    }

    public void setShipmentPortLatitude(Integer shipmentPortLatitude) {
        this.shipmentPortLatitude = shipmentPortLatitude;
    }

    public Integer getShipmentPortLongitude() {
        return this.shipmentPortLongitude;
    }

    public Port shipmentPortLongitude(Integer shipmentPortLongitude) {
        this.shipmentPortLongitude = shipmentPortLongitude;
        return this;
    }

    public void setShipmentPortLongitude(Integer shipmentPortLongitude) {
        this.shipmentPortLongitude = shipmentPortLongitude;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Port)) {
            return false;
        }
        return id != null && id.equals(((Port) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Port{" +
            "id=" + getId() +
            ", loadingPortLatitude=" + getLoadingPortLatitude() +
            ", loadingPortLongitude=" + getLoadingPortLongitude() +
            ", shipmentPortLatitude=" + getShipmentPortLatitude() +
            ", shipmentPortLongitude=" + getShipmentPortLongitude() +
            "}";
    }
}
