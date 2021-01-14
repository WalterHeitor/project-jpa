package br.com.waltersoft.modelo.connect;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectFactory {
	
	protected EntityManager entityManager;

	public EntityManager getConnection() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("contas");
		if(entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}
}
