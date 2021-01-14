package br.com.waltersoft.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.waltersoft.modelo.Categoria;
import br.com.waltersoft.modelo.Cliente;
import br.com.waltersoft.modelo.Conta;
import br.com.waltersoft.modelo.Movimentacao;
import br.com.waltersoft.modelo.enums.TipoMovimentacao;

public class Testes {

	public static void main(String[] args) {
		// teste();
		// testaClienteConta();
		//testeJPQL();

		testeJPQLMovimentacaoDeUmaCategoria();
	}

	public static void teste() {
		Categoria categoria = new Categoria("Viagem");
		Categoria categoria2 = new Categoria("Negócios");

		Conta conta = new Conta();
		conta.setId(2L);

		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setDescricao("Viagem à São Paulo");
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacao.setData(LocalDateTime.now());
		movimentacao.setValor(new BigDecimal(300.0));
		movimentacao.setCategorias(Arrays.asList(categoria, categoria2));

		Movimentacao movimentacao2 = new Movimentacao();
		movimentacao2.setDescricao("Viagem ao Rio de Janeiro");
		movimentacao2.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacao2.setData(LocalDateTime.now());
		movimentacao2.setValor(new BigDecimal(400.0));
		movimentacao2.setCategorias(Arrays.asList(categoria, categoria2));

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(categoria);
		em.persist(categoria2);
		em.persist(movimentacao);
		em.persist(movimentacao2);

		em.getTransaction().commit();
		em.close();

	}

	public static void testaClienteConta() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		Conta conta = new Conta();
		conta.setId(1L);

		Cliente cliente = new Cliente();
		cliente.setNome("Leonardo");
		cliente.setEndereco("Rua do Ouvidor, 50");
		cliente.setProfissao("Professor");
		cliente.setConta(conta);

		em.getTransaction().begin();

		em.persist(cliente);

		em.getTransaction().commit();
		em.close();
	}

	// importações omitidas

	public static void testeJPQL() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		Conta conta = new Conta();
		conta.setId(8L);
		
		//String jpql = "select m from Movimentacao m where m.conta.id =8";
		String jpql = "select m from Movimentacao m where m.conta = :pConta";

		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		List<Movimentacao> resultList = query.getResultList();

		for (Movimentacao movimentacao : resultList) {
			System.out.println("Descrição: " + movimentacao.getDescricao());
			System.out.println("Tipo: " + movimentacao.getTipoMovimentacao());
		}
		
		System.out.println("teste aceito");
		System.out.println(resultList);
	}
	
	public static void testeJPQLMovimentacaoDeUmaCategoria() {

	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
	        EntityManager em = emf.createEntityManager();

	        String sql = "select m from Movimentacao m join m.categorias c  where c = :pCategoria";

	        Categoria categoria = new Categoria();
	        categoria.setId(2L);

	        TypedQuery<Movimentacao> query = em.createQuery(sql, Movimentacao.class);
	        query.setParameter("pCategoria", categoria);

	        List<Movimentacao> movimentacoes = query.getResultList();
	        for (Movimentacao movimentacao : movimentacoes) {
	            System.out.println("Descrição: " + movimentacao.getDescricao());
	            System.out.println("Valor: " + movimentacao.getValor());
	            System.out.println("Tipo: " + movimentacao.getTipoMovimentacao());
	        }
	    }
}
