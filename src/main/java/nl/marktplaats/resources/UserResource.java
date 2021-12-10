package nl.marktplaats.resources;

import nl.marktplaats.dao.Dao;
import nl.marktplaats.domain.User;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Dependent
public class UserResource {
    private int id;

    @Inject
    private Dao<User> userDao;


    public void setId(int id) {
        this.id = id;
    }

    @GET
    @Produces(APPLICATION_JSON)
    public User getOneUserById() {
        return userDao.getById(this.id);
    }
}


