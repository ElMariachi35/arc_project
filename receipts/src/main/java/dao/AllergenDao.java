package dao;

import javax.persistence.NoResultException;

import model.Allergen;
import cdi.Dao;

@Dao
public class AllergenDao extends EntityDao<Allergen> {

	@Override
	protected Class<Allergen> getResponseClass() {
		return Allergen.class;
	}

	public Allergen findByLetter(String letter) {
		try {
			return entityManager
					.createNamedQuery(Allergen.FIND_BY_LETTER, Allergen.class)
					.setParameter(Allergen.PARAM_LETTER, letter)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
