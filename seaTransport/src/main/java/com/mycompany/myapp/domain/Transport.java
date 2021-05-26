package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Transport.
 */
@Entity
@Table(name = "transport")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Transport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "transport_name", length = 50, nullable = false)
    private String transportName;

    @NotNull
    @Min(value = 0)
    @Column(name = "max_weight", nullable = false)
    private Integer maxWeight;

    @NotNull
    @Min(value = 0)
    @Column(name = "speed", nullable = false)
    private Integer speed;

    @NotNull
    @Min(value = 0)
    @Column(name = "deck_size", nullable = false)
    private Integer deckSize;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transport id(Long id) {
        this.id = id;
        return this;
    }

    public String getTransportName() {
        return this.transportName;
    }

    public Transport transportName(String transportName) {
        this.transportName = transportName;
        return this;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public Integer getMaxWeight() {
        return this.maxWeight;
    }

    public Transport maxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
        return this;
    }

    public void setMaxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Integer getSpeed() {
        return this.speed;
    }

    public Transport speed(Integer speed) {
        this.speed = speed;
        return this;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getDeckSize() {
        return this.deckSize;
    }

    public Transport deckSize(Integer deckSize) {
        this.deckSize = deckSize;
        return this;
    }

    public void setDeckSize(Integer deckSize) {
        this.deckSize = deckSize;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transport)) {
            return false;
        }
        return id != null && id.equals(((Transport) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transport{" +
            "id=" + getId() +
            ", transportName='" + getTransportName() + "'" +
            ", maxWeight=" + getMaxWeight() +
            ", speed=" + getSpeed() +
            ", deckSize=" + getDeckSize() +
            "}";
    }
}
