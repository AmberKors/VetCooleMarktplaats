package nl.marktplaats.dao;


import nl.marktplaats.domain.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.BadRequestException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Generic Dao for all other Dao's
 *
 * @param <E>, the type of the extention, ie Userdao extends Dao<User>
 */
public abstract class Dao<E extends AbstractEntity<Integer>> {

    @PersistenceContext
    protected EntityManager em;

    public List<E> getAll() {
        return em.createNamedQuery(typeSimple() + ".findAll", E()).getResultList();
    }

    public E getById(Integer id) {
        return em.find(E(), id);
    }

    public List<E> get(String q) {
        TypedQuery<E> namedQuery = em.createNamedQuery(typeSimple() + ".search", E());
        namedQuery.setParameter("q", "%" + q + "%");
        return namedQuery.getResultList();
    }

    /**
     * Persist specified object
     *
     * @param c
     * @return
     */
    public E add(E c) {
        em.persist(c);
        return c;
    }

    /**
     * Remove specified object
     * Removes the object based on ID
     *
     * @param id
     * @return
     */
    public boolean remove(Integer id) {
        E e = em.find(E(), id);
        if (e == null) return false;

        em.remove(e);
        return true;
    }

    /**
     * Updates the object indicated by ID
     *
     * @param id
     * @param e
     * @return
     */
    public E update(Integer id, E e) {
        E found = em.find(E(), id);
        if (found == null) throw new BadRequestException("Entity with id " + id + " not found.");

        e.setId(id);
        return em.merge(e);
    }

    private String typeSimple() {
        return E().getSimpleName();
    }


    private Class<E> E() {
        ParameterizedType thisDaoClass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) thisDaoClass.getActualTypeArguments()[0];
    }

}
