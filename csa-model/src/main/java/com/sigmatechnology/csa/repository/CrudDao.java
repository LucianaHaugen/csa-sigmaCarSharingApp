package com.sigmatechnology.csa.repository;

import java.util.List;

/**
 * Created by lucianahaugen on 31/08/17.
 */
public interface CrudDao<T>{
    //Persists a
    T create(T entity);
    T update(T entity);
    T merge(T entity);
    T detach(T entity);
    T createOrUpdate(T entity);

    void delete(Long id);
    void delete(T entity);

    /**
     * Returns an entity based on it's id.
     *
     * @param id			The id of the entity
     * @return				The fetched entity
     */
    T find(Long id);

    List<T> listAll();

    /**
     * Get size / number of posts in entity table.
     * @return The size of the table
     */
    long size();

    List<T> batch(List<T> entities);

    void clear();

}