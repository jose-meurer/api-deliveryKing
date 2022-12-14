package com.josemeurer.DeliveryKing.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_orderProduct")
public class OrderProduct implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @Column(columnDefinition = "TEXT")
    private String observation;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Double priceHistoryProduct; //Valor na data da compra

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderProduct() {
    }

    public OrderProduct(Long id, Order order, Integer quantity, String observation, Product product) {
        this.id = id;
        this.order = order;
        this.quantity = quantity;
        this.observation = observation;
        this.product = product;
        this.priceHistoryProduct = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPriceHistoryProduct() {
        return priceHistoryProduct;
    }

    public void setPriceProduct(Double priceProduct) {
        this.priceHistoryProduct = priceProduct;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
