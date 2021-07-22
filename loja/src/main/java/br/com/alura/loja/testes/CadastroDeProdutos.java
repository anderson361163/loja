package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.CategoriaId;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProdutos {
	public static void main(String[] args) {
		Produto celular = new Produto();
		
		celular.setNome("Xiaomi Redmi 8");
		celular.setDescricao("Muito legal");
		celular.setPreco(new BigDecimal("800"));
		
	
		
		/* COMO NO PERSISTENCE UNIT FOI DEFINIDO QUE AS TRANSAÇÕES
		 *  SERIAM GERENCIADAS LOCALMENTE E NÃO GERIDAS POR UM SERVIDOR,
		 *  CERTIFICADO PELA ORACLE, FICAMOS OBRIGADO A INDICAR QUANDO SE ABRE
		 *  E FECHA AS TRANSAÇÕES 
		*/
		
		/**
		 * A propriedade a seguir tem as seguintes tipos
		 *
		 * Propriedade:
		 //<property name="hibernate.hbm2ddl.auto" value="update" />
		 * 
		 * cria e atualiza atributos das tabelas (update)
		 * apaga tudo e cria do zero (create)
		 * cria as tabelas, mas assim que finalizar o programa já dropa (create-drop)
		 * só valida se as estruturas estão ok e gera um log (validate)
		 */

		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		em.persist(celular);
		em.getTransaction().commit();
//		em.close();
		
		
		//-----------------------

		ProdutoDAO dao = new ProdutoDAO(em);
		
		em.getTransaction().begin();
		
		dao.cadastrar(celular);
		
		em.getTransaction().commit();
		
		
		em.find(Categoria.class, new CategoriaId("CELULARES", "xpto"));
		
		
		
		em.close();
		
		System.out.println("Obrigado pelo ótimo suporte, Professor!");

	}
}
