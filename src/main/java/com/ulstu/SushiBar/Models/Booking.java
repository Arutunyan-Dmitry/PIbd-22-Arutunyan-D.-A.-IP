package com.ulstu.SushiBar.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column()
    private String status;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "order_fk")
    private Dish dish;

    public Booking() {}

    public Booking(String status) {
        this.status = status;
    }

    public Long getId() { return id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

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
                ", status='" + status + '\'' +
                '}';
    }
}

