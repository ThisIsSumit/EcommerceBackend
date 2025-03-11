package org.example.ecommercebackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="addresses")
@Data
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long addressId;

    @NotBlank
    @Size(max=5,
            message = "street name must be atleast of 5 characters"
            )
    private String street;

    @NotBlank
    @Size(max=5,
            message = "buildingName name must be atleast of 5 characters"
    )
    private String buildingName;

    @NotBlank
    @Size(max=5,
            message = "city name must be atleast of 5 characters"
    )
    private String city;


    @NotBlank
    @Size(max=5,
            message = "state name must be atleast of 5 characters"
    )
    private String state;

    @NotBlank
    @Size(max=5,
            message = "city name must be atleast of 5 characters"
    )
    private String country;

    @NotBlank
    @Size(max=6,
            message = "pincode name must be atleast of 5 characters"
    )
    private String pincode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> users= new ArrayList<>();

    public Address(String street,
                   String buildingName,
                   String city,
                   String state,
                   String country,
                   String pincode) {
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
}
