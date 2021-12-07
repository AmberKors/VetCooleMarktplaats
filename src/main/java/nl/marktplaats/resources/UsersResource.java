package nl.marktplaats.resources;

import nl.marktplaats.dao.UserDao;
import nl.marktplaats.domain.User;

import javax.inject.Inject;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("users")
public class UsersResource {


    @Inject
    private UserDao userDao;

    @Inject
    private UserResource userResource;

    @Path("{userId}")
    public UserResource userResource(@PathParam("userId") int id) {
        this.userResource.setId(id);
        return this.userResource;
    }


    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public User add(User input) {
        return this.userDao.add(input);
    }
}


