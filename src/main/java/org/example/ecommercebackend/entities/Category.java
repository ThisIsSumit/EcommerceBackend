package org.example.ecommercebackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity(name="categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @NotBlank(message = "name should not be blank")
    @Size(min=5,message = "Category name must contain atleast 5 characters")
    private String categoryName;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> products;

}
