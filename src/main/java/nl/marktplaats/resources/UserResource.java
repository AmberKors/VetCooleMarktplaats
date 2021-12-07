package nl.marktplaats.resources;

import nl.marktplaats.dao.UserDao;
import nl.marktplaats.domain.User;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Dependent
public class UserResource {

    private int id;

    @Inject
    UserDao userDao;


    @PUT
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public User put(User updatedUser) {
        return userDao.update(this.id, updatedUser);
    }

    public void setId(int id) {
        this.id = id;
    }


}


