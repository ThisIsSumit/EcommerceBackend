package org.example.ecommercebackend.repositories;

import org.example.ecommercebackend.entities.AppRole;
import org.example.ecommercebackend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}
