package com.advantech.homework.dao;


import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDao<PK extends Serializable, T> {
    @PersistenceContext
    EntityManager entityManager;

    private final Class<T> persistentClass;

    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public void persist(T entity) { entityManager.persist(entity); }

    public T merge(T entity) { return  entityManager.merge(entity); }



    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public int getCount(String hql) {
        Query q = entityManager.createQuery(hql);
        return q.getResultList().size();
    }

    public List<T> queryForPage(String hql, int offset, int length) {
        Query q = entityManager.createQuery(hql);
        if (offset != -1 && length != -1)
        {
            q.setFirstResult(offset);
            q.setMaxResults(length);
        }
        return  q.getResultList();
    }


    public List<T> query(String hql) {
        Query q = entityManager.createQuery(hql);
        return  q.getResultList();
    }

    public List queryBySql(String sql) {
        return entityManager.createNativeQuery(sql).getResultList();
    }

    public List<T> queryBySqlEntity(String sql) {
        Query query = entityManager.createNativeQuery(sql);
//        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(persistentClass));
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    public List<T> queryBySqlToMap(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    public Object updateBySql(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        return query.executeUpdate();
    }

}

