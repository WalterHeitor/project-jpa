package br.com.waltersoft.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.waltersoft.modelo.Conta;

public class CriaConta {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();

        Conta conta = new Conta();
        conta.setTitular("Walter");
        conta.setNumero(612345);
        conta.setAgencia(543216);
        conta.setSaldo(2500.90);

        em.getTransaction().begin();
        em.persist(conta);
        em.getTransaction().commit();
        em.close();
        
        Conta conta1 = new Conta();
        conta1.setTitular("Marcia");
        conta1.setNumero(123456);
        conta1.setAgencia(654321);
        conta1.setSaldo(100.90);
        
        EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("contas");
        EntityManager em1 = emf1.createEntityManager();
        conta1.setSaldo(500.0);
        
        em1.getTransaction().begin();
        em1.persist(conta1);
        em1.getTransaction().commit();
        em1.close();
        conta1.setSaldo(3500.0);// O estado Detached significa que o objeto não está vinculado ao EntityManager
        
        EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("contas");
        EntityManager em2 = emf.createEntityManager();
        
        em2.getTransaction().begin();
        em2.merge(conta1);
        em2.getTransaction().commit();
        em2.close();
        
    }
}
