package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import model.BaseEntity;


public abstract class EntityDao<T extends BaseEntity> implements Serializable {

	@Inject
	EntityManager entityManager;

	protected abstract Class<T> getResponseClass();

	@Transactional(TxType.MANDATORY)
	public T create(T entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Transactional(TxType.MANDATORY)
	public T update(T entity) {
		return entityManager.merge(entity);
	}

	@Transactional(TxType.MANDATORY)
	public void delete(T entity) {
		entityManager.remove(update(entity));
	}

	public T findById(long id) {
		return entityManager.find(getResponseClass(), id);
	}

	public List<T> findAll() {
		CriteriaBuilder criteriaBuilder = getEntityManager()
				.getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder
				.createQuery(getResponseClass());
		query.from(getResponseClass());
		return getEntityManager().createQuery(query).getResultList();
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
