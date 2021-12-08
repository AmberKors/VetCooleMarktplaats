package nl.marktplaats.resources;

import nl.marktplaats.dao.UserDao;


import javax.enterprise.context.Dependent;
import javax.inject.Inject;


@Dependent
public class UserResource {

    private int id;

    @Inject
    UserDao userDao;


    public void setId(int id) {
        this.id = id;
    }


}


