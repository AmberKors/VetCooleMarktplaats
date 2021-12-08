package nl.marktplaats.resources;

import nl.marktplaats.dao.ShoppingCartDao;
import nl.marktplaats.domain.Product;
import nl.marktplaats.domain.ShoppingCart;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Dependent
@Path("shopping-cart/{cartId}")
public class ShoppingCartResource {

    @Inject
    private ShoppingCartDao shoppingCartDao;

    @GET
    @Produces(APPLICATION_JSON)
    public List<Product> getWithProducts(@PathParam("cartId") int id) {
        ShoppingCart shoppingCart = shoppingCartDao.getById(id);
        return shoppingCartDao.getWithProducts(shoppingCart);
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public ShoppingCart updateShoppingCart(@PathParam("cartId") int id, ShoppingCart input) {
        return shoppingCartDao.update(id, input);
    }

    @DELETE
    public void deleteShoppingCartById(@PathParam("cartId") int id) {
        shoppingCartDao.remove(id);
    }

}
