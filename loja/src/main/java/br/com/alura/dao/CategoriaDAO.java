package br.com.alura.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.modelo.Categoria;

public class CategoriaDAO {
	
	//EntityManager é uma interface não uma classe
	//como EntityManager vai ser usado por todos os métodos
	private EntityManager em;

	//Quem for criar o objeto vai ter que passar o em
	public CategoriaDAO(EntityManager em) {
		super();
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria){
		this.em.persist(categoria);
	}
	
	public void atualizar(Categoria categoria){
		this.em.merge(categoria);
	}

	public void remover(Categoria categoria){
		//CASO ALGUMA 
		categoria = em.merge(categoria);
		this.em.remove(categoria);
	}
	
}
