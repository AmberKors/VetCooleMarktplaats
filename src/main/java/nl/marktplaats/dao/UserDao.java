package nl.marktplaats.dao;


import nl.marktplaats.domain.SimplifiedUser;
import nl.marktplaats.domain.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UserDao extends Dao<User> {


    public User findByLogin(SimplifiedUser u) {
        TypedQuery<User> query = em.createQuery("Select u from User u where u.password = :password and u.username= :username", User.class);
        query.setParameter("username", u.getUsername());
        query.setParameter("password", u.getPassword());
        List<User> resultList = query.getResultList();
        if (resultList.size() == 0) {
            return null;
        } else {
            return resultList.get(0);
        }
    }
}



