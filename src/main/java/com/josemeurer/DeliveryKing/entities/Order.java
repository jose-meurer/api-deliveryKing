package com.josemeurer.DeliveryKing.entities;

import com.josemeurer.DeliveryKing.entities.enums.OrderStatus;
import com.josemeurer.DeliveryKing.entities.enums.PaymentMethod;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

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
    private Double productsPrice;
    private Double deliveryFee;
    private Double totalPrice;
    private PaymentMethod paymentMethod;
    private Double moneyChange;
    private OrderStatus status;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant orderTime; //Hora que o pedido foi feito

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant deliveryTime; //Hora que o pedido foi entregue

    private String address; //mudar para classe

    @ManyToOne
    @JoinColumn(name = "deliveryman_id")
    private Deliveryman deliveryman;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany(mappedBy = "order")
//    private Set<OrderProduct> orderProducts = new HashSet<>();
}
