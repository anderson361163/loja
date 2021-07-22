package br.com.alura.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.VO.RelatorioDeVendasVO;
import br.com.alura.loja.modelo.Pedido;

public class PedidoDAO {
	
	private EntityManager em;

	public PedidoDAO(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
	
	//forma pouco adquada, mas que funciona, contudo não da para saber o que vem dentro
//	public List<Object[]> relatorioDeVendas(){
//		String jpql = "SELECT produto.nome, "
//				+ "SUM(item.quantidade), "
//				+ "MAX(pedido.data) "
//				+ "FROM Pedido pedido "
//				+ "JOIN pedido.itens item "
//				+ "JOIN item.produto produto "
//				+ "GROUP BY produto.nome "
//				+ "ORDER BY item.quantidade DESC";
//		return em.createQuery(jpql, Object[].class)
//				.getResultList();
//	}
	// para descomentar pode usar selecionar e usar
	// ctrl + 7
	
	//Classes VO são classes que não tem comportamento só estado
	public List<RelatorioDeVendasVO> relatorioDeVendas(){
		String jpql = "SELECT new br.com.alura.VO.RelatorioDeVendasVO("
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome " 
				+ "ORDER BY item.quantidade DESC";
		return em.createQuery(jpql, RelatorioDeVendasVO.class)
				.getResultList();
	}
	
	
	
	
	
}
