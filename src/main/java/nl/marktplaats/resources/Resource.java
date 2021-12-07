package nl.marktplaats.resources;



import nl.marktplaats.dao.Dao;
import nl.marktplaats.domain.AbstractEntity;

import javax.ws.rs.*;
import java.util.Collection;

public abstract class Resource<E extends AbstractEntity<Integer>> {

    protected Dao<Integer,E> dao;

    public abstract void setDao(Dao<Integer, E> dao);

    @GET
    public Collection<E> getAll(@QueryParam("q") String q) {
        return q == null ? dao.getAll() : dao.get(q);
    }

    @GET @Path("{id}")
    public E get(@PathParam("id") Integer id) {
        return dao.getById(id);
    }

    @POST
    public E post(E e) {
        if (dao.add(e) != null) {
            return e;
        } else {
            throw new RuntimeException("Post " + e + " failed.");
        }
    }

    @DELETE @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        if (!dao.remove(id)) {
            throw new BadRequestException("Delete with id " + id + " failed.");
        }
    }

    @PUT @Path("{id}")
    public E put(@PathParam("id") Integer id, E e) {
        return dao.update(id, e);
    }
}
