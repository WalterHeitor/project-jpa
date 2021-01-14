package br.com.waltersoft.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.waltersoft.modelo.Conta;
import br.com.waltersoft.modelo.Movimentacao;
import br.com.waltersoft.modelo.enums.TipoMovimentacao;

public class TestaRelacinamento {
	public static void main(String[] args) {
		 Conta conta = new Conta();
		    conta.setAgencia(123123);
		    conta.setNumero(456456);
		    conta.setSaldo(300.0);
		    conta.setTitular("Leonardo");

		        Movimentacao movimentacao = new Movimentacao();
		        movimentacao.setData(LocalDateTime.now());
		        movimentacao.setDescricao("Churrascaria");
		        movimentacao.setValor(new BigDecimal(200.0));
		        movimentacao.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
		        movimentacao.setConta(conta);

		        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		        EntityManager em = emf.createEntityManager();

		        em.getTransaction().begin();
		        em.persist(conta);
		        em.persist(movimentacao);
		        em.getTransaction().commit();

		        em.close();
	}
	
}
