package com.example.nobsv2.Product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity //Maps class to mysql
@Data
@Table(name = "product")
public class Product {

    @Id // tags variable id as the primary key for mysql
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto generates id starting from 1, 2, 3, ...
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Name is required.")
    @Column(name = "name")
    private String name;

    @Size(message = "Description must be atleast 20 characters.")
    @Column(name = "description")
    private String description;

    @PositiveOrZero(message = "Price must be non-negative.")
    @Column(name = "price")
    private double price;

    public Product() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
