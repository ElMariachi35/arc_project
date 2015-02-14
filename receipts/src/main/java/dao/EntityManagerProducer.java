package dao;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cdi.Service;

@Service
public class EntityManagerProducer implements Serializable {

	@Produces
	@PersistenceContext
	transient EntityManager entityManager;

}