package br.com.waltersoft.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TEsteCriaTabelas {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager entityManager = emf.createEntityManager();
		emf.close();
	}
}
