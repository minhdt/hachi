package com.hachi.fashion.service;

import com.hachi.fashion.entity.BaseEntity;
import com.hachi.fashion.util.SearchBase;
import com.hachi.fashion.util.Validator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortOrder;

/**
 * @author Nguyen Trong Cuong
 * @param <T>
 * @since 09/10/2014
 * @version 1.0
 */
public abstract class SB_BaseBean<T extends BaseEntity> implements SB_BaseLocal<T> {

    private static final long serialVersionUID = 4127686125157546489L;

    @PersistenceContext
    protected EntityManager em;

    protected final Class<T> entityClass;

    public SB_BaseBean(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T find(long id) {
        return em.find(entityClass, id);
    }

    /* Callback method */
    protected void onBeforePersist(T entity) {
    }

    /* Callback method */
    protected void onAfterPersist(T entity) {
    }

    @Override
    public T persist(T entity) {
        onBeforePersist(entity);
        em.persist(entity);
        em.flush();
        onAfterPersist(entity);
        return entity;
    }

    /* Callback method */
    protected void onBeforeUpdate(T entity) {
    }

    /* Callback method */
    protected void onAfterUpdate(T entity) {
    }

    @Override
    public T update(T entity) {
        onBeforeUpdate(entity);
        em.merge(entity);
        onAfterUpdate(entity);
        return entity;
    }

    /* Callback method */
    protected void onBeforeRemove(T entity) {
    }

    /* Callback method */
    protected void onAfterRemove(T entity) {
    }

    @Override
    public void remove(T entity) {
        onBeforeRemove(entity);
        em.remove(em.merge(entity));
        onAfterRemove(entity);
    }

    @Override
    public void removeListBase(List<T> listEntity) {
        for (T item : listEntity) {
            remove(item);
        }
    }

    @Override
    public void mergeListEntity(List<T> listEntity) {
        for (T item : listEntity) {
            update(item);
        }
    }

    @Override
    public int countBase(List<SearchBase> search, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(entityClass);
        cq.select(cb.count(root));

        List<Predicate> predicates = buildConditions(search, filters, root, cb);

        if (predicates != null && !predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }

    @Override
    public List<T> findAllBase(List<SearchBase> search, int start, int range, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);

        List<Predicate> predicates = buildConditions(search, filters, root, cb);

        if (predicates != null && !predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        Order order = buildOrderBy(sortField, sortOrder, cb, root);

        if (order != null) {
            cq.orderBy(order);
        }

        TypedQuery<T> q = em.createQuery(cq);

        if (start >= 0) {
            q.setFirstResult(start);
        }
        if (range > 0) {
            q.setMaxResults(range);
        }

        return q.getResultList();
    }

    protected List<Predicate> buildConditions(List<SearchBase> search, Map<String, Object> filters, Root<T> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.addAll(buildConditions(filters, root, cb));

        if (search != null) {
            for (SearchBase item : search) {
                if (item != null) {
                    Predicate predicate = buildCondition(item, root, cb);
                    if (predicate != null) {

                    }
                }
            }
        }
        return predicates;
    }

    protected List<Predicate> buildConditions(Map<String, Object> filters, Root<T> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            Predicate predicate = cb.equal(root.get(entry.getKey()), entry.getValue());
            if (predicate != null) {
                predicates.add(predicate);
            }
        }

        return predicates;
    }

    protected Predicate buildCondition(SearchBase item, Root<T> root, CriteriaBuilder cb) {
        Predicate predicate = null;
        if (item.getColName() != null && item.getExpression() != null && item.getParam() != null) {
            Expression<?> field = root.get(item.getColName());

            switch (item.getExpression()) {
                case SearchBase.EXPRESSION_EQUALS:
                    predicate = cb.equal(field, item.getParam());
                    break;
                case SearchBase.EXPRESSION_NOT_EQUALS:
                    predicate = cb.notEqual(field, item.getParam());
                    break;
                case SearchBase.EXPRESSION_GREATE:
                    if (item.getParam() instanceof Integer) {
                        Expression<Integer> fieldInt = root.get(item.getColName());
                        predicate = cb.greaterThan(fieldInt, (Integer) item.getParam());
                    } else if (item.getParam() instanceof Double) {
                        Expression<Double> fieldDouble = root.get(item.getColName());
                        predicate = cb.greaterThan(fieldDouble, (Double) item.getParam());
                    } else if (item.getParam() instanceof Long) {
                        Expression<Long> fieldLong = root.get(item.getColName());
                        predicate = cb.greaterThan(fieldLong, (Long) item.getParam());
                    } else if (item.getParam() instanceof Date) {
                        Expression<Date> fieldDate = root.get(item.getColName());
                        predicate = cb.greaterThan(fieldDate, (Date) item.getParam());
                    }
                    break;
                case SearchBase.EXPRESSION_GREATE_OR_EQUALS:
                    if (item.getParam() instanceof Integer) {
                        Expression<Integer> fieldInt = root.get(item.getColName());
                        predicate = cb.greaterThanOrEqualTo(fieldInt, (Integer) item.getParam());
                    } else if (item.getParam() instanceof Double) {
                        Expression<Double> fieldDouble = root.get(item.getColName());
                        predicate = cb.greaterThanOrEqualTo(fieldDouble, (Double) item.getParam());
                    } else if (item.getParam() instanceof Long) {
                        Expression<Long> fieldLong = root.get(item.getColName());
                        predicate = cb.greaterThanOrEqualTo(fieldLong, (Long) item.getParam());
                    } else if (item.getParam() instanceof Date) {
                        Expression<Date> fieldDate = root.get(item.getColName());
                        predicate = cb.greaterThanOrEqualTo(fieldDate, (Date) item.getParam());
                    }
                    break;
                case SearchBase.EXPRESSION_LESS:
                    if (item.getParam() instanceof Integer) {
                        Expression<Integer> fieldInt = root.get(item.getColName());
                        predicate = cb.lessThan(fieldInt, (Integer) item.getParam());
                    } else if (item.getParam() instanceof Double) {
                        Expression<Double> fieldDouble = root.get(item.getColName());
                        predicate = cb.lessThan(fieldDouble, (Double) item.getParam());
                    } else if (item.getParam() instanceof Long) {
                        Expression<Long> fieldLong = root.get(item.getColName());
                        predicate = cb.lessThan(fieldLong, (Long) item.getParam());
                    } else if (item.getParam() instanceof Date) {
                        Expression<Date> fieldDate = root.get(item.getColName());
                        predicate = cb.lessThan(fieldDate, (Date) item.getParam());
                    }
                    break;
                case SearchBase.EXPRESSION_LESS_OR_EQUALS:
                    if (item.getParam() instanceof Integer) {
                        Expression<Integer> fieldInt = root.get(item.getColName());
                        predicate = cb.lessThanOrEqualTo(fieldInt, (Integer) item.getParam());
                    } else if (item.getParam() instanceof Double) {
                        Expression<Double> fieldDouble = root.get(item.getColName());
                        predicate = cb.lessThanOrEqualTo(fieldDouble, (Double) item.getParam());
                    } else if (item.getParam() instanceof Long) {
                        Expression<Long> fieldLong = root.get(item.getColName());
                        predicate = cb.lessThanOrEqualTo(fieldLong, (Long) item.getParam());
                    } else if (item.getParam() instanceof Date) {
                        Expression<Date> fieldDate = root.get(item.getColName());
                        predicate = cb.lessThanOrEqualTo(fieldDate, (Date) item.getParam());
                    }
                    break;
                case SearchBase.EXPRESSION_IN:
                    predicate = field.in((List<?>) item.getParam());
                    break;
                case SearchBase.EXPRESSION_NOT_IN:
                    predicate = cb.not(field.in((List<?>) item.getParam()));
                    break;
                case SearchBase.EXPRESSION_LIKE:
                    Expression<String> fieldStrLike = root.get(item.getColName());
                    predicate = cb.like(cb.upper(fieldStrLike), "%" + item.getParam().toString().trim().toUpperCase() + "%");
                    break;
                case SearchBase.EXPRESSION_NOT_LIKE:
                    Expression<String> fieldStrNL = root.get(item.getColName());
                    predicate = cb.notLike(cb.upper(fieldStrNL), "%" + item.getParam().toString().trim().toUpperCase() + "%");
                    break;
                case SearchBase.EXPRESSION_NULL:
                    predicate = cb.isNull(field);
                    break;
                case SearchBase.EXPRESSION_NOT_NULL:
                    predicate = cb.isNotNull(field);
                    break;
            }
        }
        return predicate;
    }

    protected Order buildOrderBy(String sortField, SortOrder sortOrder, CriteriaBuilder cb, Root<T> root) {
        Order order = null;
        if (sortOrder != null && Validator.isNotNull(sortField)) {
            switch (sortOrder) {
                case ASCENDING:
                    order = cb.asc(root.get(sortField));
                    break;
                case DESCENDING:
                    order = cb.desc(root.get(sortField));
                    break;
            }
        }
        return order;
    }
}
