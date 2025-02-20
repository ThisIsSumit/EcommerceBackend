package org.example.ecommercebackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String productName;
    private Double  price;
    private String description;
    private String image;
    private Integer quantity;
    private double discount;
    private double specialPrice;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

}
