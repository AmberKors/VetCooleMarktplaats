package nl.marktplaats.dao;

import nl.marktplaats.domain.Product;

import javax.ejb.Stateless;
import java.time.LocalDate;

@Stateless
public class ProductDao extends Dao<Product> {

    @Override
    public Product add(Product c) {
        c.setDatePublished(LocalDate.now());
        return super.add(c);
    }
}
