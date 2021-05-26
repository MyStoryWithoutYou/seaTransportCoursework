package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OrderInfo.
 */
@Entity
@Table(name = "order_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Min(value = 0)
    @Column(name = "jhi_order", nullable = false)
    private Integer order;

    @NotNull
    @Min(value = 0)
    @Column(name = "length", nullable = false)
    private Integer length;

    @NotNull
    @Min(value = 0)
    @Column(name = "width", nullable = false)
    private Integer width;

    @NotNull
    @Min(value = 0)
    @Column(name = "height", nullable = false)
    private Integer height;

    @NotNull
    @Min(value = 0)
    @Column(name = "volume", nullable = false)
    private Integer volume;

    @NotNull
    @Min(value = 0)
    @Column(name = "weight", nullable = false)
    private Integer weight;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderInfo id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getOrder() {
        return this.order;
    }

    public OrderInfo order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getLength() {
        return this.length;
    }

    public OrderInfo length(Integer length) {
        this.length = length;
        return this;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return this.width;
    }

    public OrderInfo width(Integer width) {
        this.width = width;
        return this;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return this.height;
    }

    public OrderInfo height(Integer height) {
        this.height = height;
        return this;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getVolume() {
        return this.volume;
    }

    public OrderInfo volume(Integer volume) {
        this.volume = volume;
        return this;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public OrderInfo weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderInfo)) {
            return false;
        }
        return id != null && id.equals(((OrderInfo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderInfo{" +
            "id=" + getId() +
            ", order=" + getOrder() +
            ", length=" + getLength() +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", volume=" + getVolume() +
            ", weight=" + getWeight() +
            "}";
    }
}
