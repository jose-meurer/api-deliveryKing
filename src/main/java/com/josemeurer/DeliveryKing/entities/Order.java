package com.josemeurer.DeliveryKing.entities;

import com.josemeurer.DeliveryKing.entities.enums.OrderStatus;
import com.josemeurer.DeliveryKing.entities.enums.PaymentMethod;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String observation;

    @Column(nullable = false)
    private Double productsPrice;

    @Column(nullable = false)
    private Double deliveryFee;

    @Column(nullable = false)
    private Double totalPrice;

    @Column(nullable = false)
    private PaymentMethod paymentMethod;
    private Double moneyChange;

    @Column(nullable = false)
    private OrderStatus status;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant orderTime; //Hora que o pedido foi feito

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant deliveryTime; //Hora que o pedido foi entregue

    @NotNull
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "deliveryman_id")
    private Deliveryman deliveryman;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @OneToMany(mappedBy = "order")
    private Set<OrderProduct> orderProducts = new HashSet<>();

    public Order() {
    }

    public Order(Long id, String observation, Double productsPrice, Double deliveryFee, Double totalPrice, PaymentMethod paymentMethod, Double moneyChange, OrderStatus status, Instant orderTime, Instant deliveryTime, Address address, Deliveryman deliveryman, User user) {
        this.id = id;
        this.observation = observation;
        this.productsPrice = productsPrice;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.moneyChange = moneyChange;
        this.status = status;
        this.orderTime = orderTime;
        this.deliveryTime = deliveryTime;
        this.address = address;
        this.deliveryman = deliveryman;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Double getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(Double productsPrice) {
        this.productsPrice = productsPrice;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getMoneyChange() {
        return moneyChange;
    }

    public void setMoneyChange(Double moneyChange) {
        this.moneyChange = moneyChange;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Instant getOrderTime() {
        return orderTime;
    }

    @PrePersist //Gera a data de quando o pedido foi feito
    public void prePersist() {
        orderTime = Instant.now();
    }

    public Instant getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Instant deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Deliveryman getDeliveryman() {
        return deliveryman;
    }

    public void setDeliveryman(Deliveryman deliveryman) {
        this.deliveryman = deliveryman;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
