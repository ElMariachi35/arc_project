package service;

import java.util.List;

import javax.inject.Inject;

import model.Allergen;
import cdi.Service;
import dao.AllergenDao;
import dao.EntityDao;

@Service
public class AllergenService extends EntityService<Allergen>{

	@Inject
	private AllergenDao allergenDao;
	
	@Override
	protected EntityDao<Allergen> getDao() {
		return allergenDao;
	}

	public List<Allergen> findAll() {
		return allergenDao.findAll();
	}

	public Allergen findByLetter(String letter) {
		return allergenDao.findByLetter(letter);
	}
	
}
