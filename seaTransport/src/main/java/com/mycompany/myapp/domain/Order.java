package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Order.
 */
@Entity
@Table(name = "jhi_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Min(value = 0)
    @Column(name = "customer", nullable = false)
    private Integer customer;

    @NotNull
    @Min(value = 0)
    @Column(name = "port", nullable = false)
    private Integer port;

    @Column(name = "date_of_loading")
    private LocalDate dateOfLoading;

    @Column(name = "date_of_shipment")
    private LocalDate dateOfShipment;

    @NotNull
    @Min(value = 0)
    @Column(name = "status", nullable = false)
    private Integer status;

    @NotNull
    @Min(value = 0)
    @Column(name = "transport", nullable = false)
    private Integer transport;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getCustomer() {
        return this.customer;
    }

    public Order customer(Integer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public Integer getPort() {
        return this.port;
    }

    public Order port(Integer port) {
        this.port = port;
        return this;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public LocalDate getDateOfLoading() {
        return this.dateOfLoading;
    }

    public Order dateOfLoading(LocalDate dateOfLoading) {
        this.dateOfLoading = dateOfLoading;
        return this;
    }

    public void setDateOfLoading(LocalDate dateOfLoading) {
        this.dateOfLoading = dateOfLoading;
    }

    public LocalDate getDateOfShipment() {
        return this.dateOfShipment;
    }

    public Order dateOfShipment(LocalDate dateOfShipment) {
        this.dateOfShipment = dateOfShipment;
        return this;
    }

    public void setDateOfShipment(LocalDate dateOfShipment) {
        this.dateOfShipment = dateOfShipment;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Order status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTransport() {
        return this.transport;
    }

    public Order transport(Integer transport) {
        this.transport = transport;
        return this;
    }

    public void setTransport(Integer transport) {
        this.transport = transport;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        return id != null && id.equals(((Order) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Order{" +
            "id=" + getId() +
            ", customer=" + getCustomer() +
            ", port=" + getPort() +
            ", dateOfLoading='" + getDateOfLoading() + "'" +
            ", dateOfShipment='" + getDateOfShipment() + "'" +
            ", status=" + getStatus() +
            ", transport=" + getTransport() +
            "}";
    }
}
