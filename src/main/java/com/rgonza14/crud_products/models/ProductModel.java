package com.rgonza14.crud_products.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct")
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @NotNull(message = "The field cannot be null")
    private String title;

    @Positive(message = "The price must be a positive value")
    @NotNull(message = "The field cannot be null")
    private int price;

    @Positive(message = "The stock must be a positive value")
    @NotNull(message = "The field cannot be null")
    private int stock;

    public ProductModel(Long id, String title, int price, int stock) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }
}
