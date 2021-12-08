package nl.marktplaats.resources;

import nl.marktplaats.dao.UserDao;
import nl.marktplaats.domain.SimplifiedUser;
import nl.marktplaats.domain.User;

import javax.inject.Inject;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("login")
public class LoginResource {

    @Inject
    private UserDao userDao;

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public User add(SimplifiedUser u){
        User byLogin = userDao.findByLogin(u);
        if (byLogin == null) throw new NotAuthorizedException("Gebruiker niet gevonden");
        return byLogin;
    }

}
