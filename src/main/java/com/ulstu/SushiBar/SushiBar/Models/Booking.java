package com.ulstu.SushiBar.SushiBar.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column()
    private String statusR;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "order_fk")
    private Dish dish;

    public Booking() {}

    public Booking(String status, Dish dish) {
        this.statusR = status;
        this.dish = dish;
    }

    public Long getId() { return id; }

    public String getStatusR() { return statusR; }
    public void setStatusR(String statusR) { this.statusR = statusR; }

   public Dish getDish() { return dish; }
    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking order = (Booking) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", status='" + statusR + '\'' +
                '}';
    }
}

