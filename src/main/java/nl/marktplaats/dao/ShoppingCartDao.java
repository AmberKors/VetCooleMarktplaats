package nl.marktplaats.dao;

import nl.marktplaats.domain.Product;
import nl.marktplaats.domain.ShoppingCart;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ShoppingCartDao extends Dao<ShoppingCart> {
    public List<Product> getShoppingCartProducts(int id) {
        ShoppingCart cart = getById(id);

        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.shoppingCart=:cart", Product.class);
        query.setParameter("cart", cart);
        return query.getResultList();
    }
}
