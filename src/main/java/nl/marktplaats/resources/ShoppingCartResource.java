package nl.marktplaats.resources;

import nl.marktplaats.dao.ShoppingCartDao;
import nl.marktplaats.domain.ShoppingCart;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Dependent
@Path("shopping-cart/{cartId}")
public class ShoppingCartResource {

    @Inject
    private ShoppingCartDao shoppingCartDao;

    @GET
    @Produces(APPLICATION_JSON)
    public ShoppingCart getOneShoppingCartById(@PathParam("cartId") int id) {
        return shoppingCartDao.getById(id);
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
