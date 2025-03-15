package org.example.ecommercebackend.repositories;

import org.example.ecommercebackend.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    CartItem findCartItemByProductIdAndCartId(Long cartId, Long productId);

    void deleteCartItemByProductIdAndCartId(Long cartId, Long productId);
}
