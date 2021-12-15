package nl.marktplaats.dao;


import nl.marktplaats.domain.SimplifiedUser;
import nl.marktplaats.domain.User;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.TypedQuery;
import java.util.List;

import static nl.marktplaats.util.PasswordUtils.digestPassword;

@Singleton
@Startup
public class UserDao extends Dao<User> {


    public User findByLogin(SimplifiedUser u) {
        TypedQuery<User> query = em.createQuery("Select u from User u where u.password = :password and u.username= :username", User.class);
        query.setParameter("username", u.getUsername());
        query.setParameter("password", digestPassword(u.getPassword()));
        List<User> resultList = query.getResultList();
        if (resultList.size() == 0) {
            return null;
        } else {
            return resultList.get(0);
        }
    }
}



