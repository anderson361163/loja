package br.com.alura.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDAO {
	
	//EntityManager é uma interface não uma classe
	//como EntityManager vai ser usado por todos os métodos
	private EntityManager em;

	//Quem for criar o objeto vai ter que passar o em
	public ProdutoDAO(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}

	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}

	public void remover(Produto produto) {
		produto = em.merge(produto);
		this.em.remove(produto);
	}
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public List<Produto> buscarPorNomeDaCategoria(String nome) {
		//String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
		return em.createNamedQuery("Produto.produtosPorCategoria",
				Produto.class)
				.setParameter("nome", nome)
				.getResultList();
//		return em.createQuery("SELECT p FROM Produto p WHERE p.categoria.nome = :nome",
//				Produto.class)
//				.setParameter("nome", nome)
//				.getResultList();
	}
	
	public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
	
	
	
	
	//parametros opcionais, só na versão 2.0 da JPA, mas já existia no hibernate antes 
	public List<Produto> buscarPorParametrosComCriteria(
			String nome, BigDecimal preco, LocalDate dataCadastro){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		//semelhante ao JPQL que tem como retorno o TypedQuery 
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filtros = builder.and();
		if(nome != null && !nome.trim().isEmpty()) {
			//Seleciona o objeto e escolhe o filtro que vai ser realizado
			//no primeiro parametro dentro do filtro equals, se coloca 
			//qual vai ser o campo da tabela que vai ser filtrado
			//enquanto no segundo parametro vai ter o parametro para busca
			filtros = builder.and(filtros, builder.equal  (from.get("nome"), nome));
		}
		
		if(preco != null) {
			filtros = builder.and(filtros, builder.equal  (from.get("preco"), preco));
		}
		if(dataCadastro != null) {
			filtros = builder.and(filtros, builder.equal  (from.get("dataCadastro"), dataCadastro));
		}
		query.where(filtros);
		
		return em.createQuery(query).getResultList();
		
	}

}
