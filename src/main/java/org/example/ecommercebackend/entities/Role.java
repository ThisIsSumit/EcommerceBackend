package org.example.ecommercebackend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Integer roleId;

    @ToString.Exclude
    @Enumerated(EnumType.STRING) // by default this  is saved as
    // enum  type but we want to save string
    @Column(length=20,name ="role_name" )
    private AppRole roleName;

    public Role(AppRole appRole) {
        this.roleName= appRole;
    }

}
