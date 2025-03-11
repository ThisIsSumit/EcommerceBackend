package org.example.ecommercebackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="users",
uniqueConstraints = {@UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email"),
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @NotBlank
    @Size(max = 30)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(max = 120)
    @Column(name="password")
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //creating the junction table
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name ="role_id")
    )
    private Set<Role> roles=new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="user_address",
    joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
       )
    private List<Address> addresses=new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            orphanRemoval = true
            )
    private Set<Product> products=new HashSet<>();
}
