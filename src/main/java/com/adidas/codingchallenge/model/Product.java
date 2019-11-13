package com.adidas.codingchallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    @NotNull
    private String name;

    @Column(name = "product_description")
    private String description;

    // price store in EUR
    @Column(name = "product_price")
    private Double price;

    @Column(name = "price_currency")
    private String currency = "EUR";

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
