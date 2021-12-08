package nl.marktplaats.dao;

import nl.marktplaats.domain.Product;
import nl.marktplaats.domain.ShoppingCart;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ShoppingCartDao extends Dao<ShoppingCart> {
    public List<Product> getWithProducts(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts();
    }
}
