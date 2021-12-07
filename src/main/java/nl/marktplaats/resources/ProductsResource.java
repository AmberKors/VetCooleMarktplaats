package nl.marktplaats.resources;

import nl.marktplaats.dao.ProductDao;
import nl.marktplaats.domain.Product;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("products")
public class ProductsResource {
    @Inject
    private ProductDao productDao;

    @Inject
    private ProductResource productResource;

    @GET
    @Produces(APPLICATION_JSON)
    public List<Product> getProducts() {
        return this.productDao.getAll();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Product add(Product input) {
        return productDao.add(input);
    }

    @Path("{productId}")
    public ProductResource productResource(@PathParam("productId") int id) {
        this.productResource.setId(id);
        return this.productResource;
    }


}
