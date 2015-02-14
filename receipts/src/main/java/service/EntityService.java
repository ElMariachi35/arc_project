package service;

import java.io.Serializable;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import model.BaseEntity;
import dao.EntityDao;


public abstract class EntityService<T extends BaseEntity> implements
		Serializable {

	protected abstract EntityDao<T> getDao();

	@Transactional(TxType.MANDATORY)
	public T save(T entity) {
		if (entity.getId() > 0) {
			return getDao().update(entity);
		}
		return getDao().create(entity);
	}

	@Transactional(TxType.MANDATORY)
	public void delete(T entity) {
		getDao().delete(entity);
	}

	public T findById(long id) {
		return getDao().findById(id);
	}
}
