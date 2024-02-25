package pl.example.business.dao;

import pl.example.domain.Cart;

public interface CartDAO {


     Cart saveCart(Cart cart);
     void deleteCart(Integer cartId);
     Cart findCartById(Integer cartId);
}
