package nl.marktplaats.resources;

import nl.marktplaats.domain.User;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Dependent
public class UserResource {

    @Inject
    private long id;

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public User post(User newUser) {return userDao.create(this.id, newUser);}

}
