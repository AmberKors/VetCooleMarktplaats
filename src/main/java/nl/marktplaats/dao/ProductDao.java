package nl.marktplaats.dao;

import nl.marktplaats.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static nl.marktplaats.util.Responses.throwBadRequest;

public class ProductDao {
    @PersistenceContext
    private EntityManager em;

    public List<Product> getProducts() {
        return em.createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    public Optional<Product> getProductById(int id) {
        return Optional.ofNullable(em.find(Product.class, id));
    }

    public Product add(Product c) {
        em.persist(c);
        return c;
    }



    public Product updateProduct(int id, Product updatedProduct) {
        updatedProduct.setId(id);
        return em.merge(updatedProduct);
    }

    public void deleteProduct(int id) {
        getProductById(id).ifPresentOrElse(em::remove, throwBadRequest(id));
    }

}
