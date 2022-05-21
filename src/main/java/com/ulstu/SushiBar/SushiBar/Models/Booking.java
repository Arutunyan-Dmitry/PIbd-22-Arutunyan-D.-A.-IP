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
    @Column()
    private int amount;
    private int pavement;

    public Booking() {}

    public Booking(String status, Dish dish, int amount, int pavement) {
        this.statusR = status;
        this.dish = dish;
        this.amount = amount;
        this.pavement = pavement;
    }

    public Long getId() { return id; }

    public String getStatusR() { return statusR; }
    public void setStatusR(String statusR) { this.statusR = statusR; }

    public Dish getDish() { return dish; }
    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getAmount() {return amount;}
    public void setAmount(int amount) {this.amount = amount;}

    public int getPavement() {return pavement;}
    public void setPavement(int pavement) {this.pavement = pavement;}

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

