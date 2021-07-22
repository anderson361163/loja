package br.com.alura.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	/*A classe mae EntityManagerFactory (especifica��o), tem como filha a classe Persistence (Hibernate)
	da qual tem um m�todo est�tico chamado createentitymanagerfactory que usa a persistence unit
	*/
	private static final EntityManagerFactory FACTORY =
			Persistence.createEntityManagerFactory("loja");
	
	public static EntityManager getEntityManager() {
		//EntityManager � uma interface n�o uma classe
		return FACTORY.createEntityManager();
	}

	
}
