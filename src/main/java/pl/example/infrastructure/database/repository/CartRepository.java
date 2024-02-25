package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.CartDAO;
import pl.example.domain.Cart;
import pl.example.infrastructure.database.entity.CartEntity;
import pl.example.infrastructure.database.repository.jpa.CartJpaRepository;
import pl.example.infrastructure.database.repository.mapper.CartEntityMapper;

@Repository
@AllArgsConstructor
public class CartRepository implements CartDAO {

    private final CartJpaRepository cartJpaRepository;
    private final CartEntityMapper cartEntityMapper;


    @Override
    public Cart saveCart(Cart cart) {
        CartEntity toSave = cartEntityMapper.mapToEntity(cart);
        CartEntity saved = cartJpaRepository.save(toSave);
        return cartEntityMapper.mapFromEntity(saved);
    }

    @Override
    public void deleteCart(Integer cartId) {
        cartJpaRepository.deleteById(cartId);
    }

    @Override
    public Cart findCartById(Integer cartId) {
        return cartJpaRepository.findById(cartId)
                .map(cartEntityMapper::mapFromEntity).get();
    }

}
