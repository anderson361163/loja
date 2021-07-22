package br.com.alura.VO;

import java.time.LocalDate;

//é uma classe de valor que ser apenas para receber valores
public class RelatorioDeVendasVO {

	private String nomeProduto;
	private Long quantidadeVendas;
	private LocalDate dataUltimaVenda;
	
	public RelatorioDeVendasVO(String nomeProduto, Long quantidadeVendas, LocalDate dataUltimaVenda) {
		this.nomeProduto = nomeProduto;
		this.quantidadeVendas = quantidadeVendas;
		this.dataUltimaVenda = dataUltimaVenda;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public Long getQuantidadeVendas() {
		return quantidadeVendas;
	}

	public LocalDate getDataUltimaVenda() {
		return dataUltimaVenda;
	}

	@Override
	public String toString() {
		return "-----------------------------------------------------------\n"
				+ "RelatorioDeVendasVO [\nnomeProduto=" + nomeProduto + ",\n"
				+ " quantidadeVendas=" + quantidadeVendas+ ",\n"
				+ " dataUltimaVenda=" + dataUltimaVenda + "\n]\n"
				+ "-----------------------------------------------------------\n";
	}

	
	
}
