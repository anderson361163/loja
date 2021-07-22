package br.com.alura.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	/*A classe mae EntityManagerFactory (especificação), tem como filha a classe Persistence (Hibernate)
	da qual tem um método estático chamado createentitymanagerfactory que usa a persistence unit
	*/
	private static final EntityManagerFactory FACTORY =
			Persistence.createEntityManagerFactory("loja");
	
	public static EntityManager getEntityManager() {
		//EntityManager é uma interface não uma classe
		return FACTORY.createEntityManager();
	}

	
}
