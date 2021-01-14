package br.com.waltersoft.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.waltersoft.modelo.Conta;

public class TestandoEstados {
public static void main(String[] args) {
	//transiente
	Conta conta = new Conta();
    conta.setTitular("Walter Heitor");
    conta.setNumero(612345);
    conta.setAgencia(543216);
    conta.setSaldo(2500.90);
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
    EntityManager em = emf.createEntityManager();

    em.getTransaction().begin();
    //transient -> manager
    em.persist(conta);
    //manager -> removed
    em.getTransaction().commit();
}
}
