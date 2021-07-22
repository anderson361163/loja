package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO; //separa por underline quando tá em camelcase
	private LocalDate data = LocalDate.now();

	@ManyToOne
	private Cliente cliente;
	
	/*
	 
	 //Caso fosse um relacionamento simples com apenas as colunas de id
	 //bastaria fazer esse relacionamento, contudo, como vai ser necessário
	 //manter um histório dos preços dentre outras informações, então vai precisar
	 //mapear uma nova tabela
	 
	 @ManyToMany
	 @JoinTable(name="sadads")
	 private List<Produto> produtos;
	*/
	
	//Do lado do OneToMany tem que colocar
	//que ele é o inverso do ManyToOne
	@OneToMany(mappedBy = "pedido",  //a string passada aqui é o nome do atributo do outro lado
			cascade = CascadeType.ALL) //para inserir os dados na tabela Pedido e item de pedido simultaneamente 
	private List<ItemPedido> itens = new ArrayList<>();
	
	public Pedido() {
	}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}
	
	//já atualiza os dados bi-direcionalmente
	public void adicionarItem(ItemPedido item) {
		
		item.setPedido(this); //propria classe atual
		this.getItens().add(item); 
		this.valorTotal = this.valorTotal.add(item.getValor());
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCategoria() {
		return cliente;
	}

	public void setCategoria(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

}
