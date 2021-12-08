package nl.marktplaats.resources;

import nl.marktplaats.dao.Dao;
import nl.marktplaats.domain.Product;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Dependent
public class ProductResource {
    private int id;

    @Inject
    private Dao<Product> productDao;

    public void setId(int id) {
        this.id = id;
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Product getOneProductById() {
        return productDao.getById(this.id);
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Product updateProduct(Product input) {
        return productDao.update(this.id, input);
    }

    @DELETE
    public void deleteProductById() {
        productDao.remove(this.id);
    }
}
