package com.josemeurer.DeliveryKing.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_deliveryman")
public class Deliveryman implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String vehiclePlate;

    @OneToMany(mappedBy = "deliveryman")
    private Set<Order> orders = new HashSet<>();

    @Column(nullable = false)
    @ManyToMany
    @JoinTable(name = "tb_deliveryman_phone",
            joinColumns = @JoinColumn(name = "deliveryman_id"),
            inverseJoinColumns = @JoinColumn(name = "phone_id")
    )
    private Set<Phone> phones = new HashSet<>();


    public Deliveryman() {
    }

    public Deliveryman(Long id, String name, String vehiclePlate) {
        this.id = id;
        this.name = name;
        this.vehiclePlate = vehiclePlate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deliveryman that = (Deliveryman) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
