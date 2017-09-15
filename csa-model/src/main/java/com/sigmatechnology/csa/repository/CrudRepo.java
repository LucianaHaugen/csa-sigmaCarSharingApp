package com.sigmatechnology.csa.repository;

/**
 * Created by lucianahaugen on 31/08/17.
 */

import com.sigmatechnology.csa.entity.AbstractBaseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**A repository class that add basic CRUD functionality */
public class CrudRepo<T extends AbstractBaseEntity> implements CrudDao<T> {

    /** Choice of Logger */
    private static final Logger LOG = LoggerFactory.getLogger(CrudRepo.class);

    /**
     * The entity manager.
     */
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * The entity class.
     */
    protected final Class<T> entityClass;

    /**
     * Default constructor.
     * <p/>
     * Initializes the entity class type
     */
    @SuppressWarnings("unchecked")
    public CrudRepo() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }


    @Override
    @Transactional
    public T create(T entity) {
        if (entity.getId() != null) {
            throw new CrudException("create() performed on entity with id != null, Use createOrUpdate() instead");
        }
        if (LOG.isTraceEnabled()) {
            LOG.trace("Persisting entity: " + entity);
        }
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.refresh(entity);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public T update(T entity) {
        if (entity.getId() == null) {
            throw new CrudException("update() performed on entity with id == null, Use createOrUpdate() instead");
        }
        return merge(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public T merge(T entity) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Merging entity: " + entity);
        }
        entity = entityManager.merge(entity);
        entityManager.flush();
        entityManager.refresh(entity);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public T detach(T entity) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Detaching entity: " + entity);
        }
        entityManager.detach(entity);
        entity.setId(null);
        if (LOG.isTraceEnabled()) {
            LOG.trace("Detached entity: " + entity);
        }
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public T createOrUpdate(T entity) {
        if (entity.getId() == null) {
            // no id => create
            if (entity.getId() == null) {
                return create(entity);
            }
        }
        return update(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(Long id) {
        if (LOG.isTraceEnabled()) {
            LOG.trace(String.format("Deleting '%s' entity with id: %d", entityClass.getCanonicalName(), id));
        }
        Object reference = entityManager.getReference(entityClass, id);
        entityManager.remove(reference);
        entityManager.flush();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T entity) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Deleting entity: " + entity);
        }
        entityManager.remove(entity);
        entityManager.flush();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T find(Long id) {
        if (LOG.isTraceEnabled()) {
            LOG.trace(String.format("Looking up '%s' entity with id: %d", entityClass.getCanonicalName(), id));
        }
        return entityManager.find(entityClass, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> listAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        Root<T> rootEntry = query.from(entityClass);
        CriteriaQuery<T> all = query.select(rootEntry);
        query.orderBy(builder.asc(rootEntry.get("id")));
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long size() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(entityClass)));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    @Transactional
    public List<T> batch(List<T> entities) {
        List<T> returnList = new ArrayList<T>();
        for(T entity:entities) {
            entity = this.createOrUpdate(entity);
            returnList.add(entity);
        }
        return returnList;
    }

    @Override
    public void clear() {
        entityManager.clear();
    }
}
