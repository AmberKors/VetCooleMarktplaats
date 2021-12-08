package nl.marktplaats.resources;

import nl.marktplaats.dao.UserDao;
import nl.marktplaats.domain.User;
import org.eclipse.microprofile.openapi.annotations.Operation;

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
    @Operation(description = "Get specific user on ID")
    public UserResource userResource(@PathParam("userId") int id) {
        this.userResource.setId(id);
        return this.userResource;
    }


    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Operation(description = "Register new user to User-DBtable")
    public User add(User u) {
        return userDao.add(u);
    }


}

