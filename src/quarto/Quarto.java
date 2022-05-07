package quarto;

import java.util.ArrayList;

import arquivo.ConfigArquivoQuartos;
import usuario.Usuario;

public class Quarto {
	private int id, numero, capacidade;
	private String descricao;
	private boolean ocupado;
	private Usuario usuarioCriacao;

	public static ArrayList<Quarto> quartos = new ArrayList<>();
	private static int idCont = 0;

	public int getId() {
		return id;
	}

	private void setId() {
		Quarto.idCont++;
		this.id = idCont;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public String isOcupadoSimNao() {
		if (this.isOcupado()) {
			return "Sim";
		} else {
			return "Nao";
		}
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuarioCriacao() {
		return usuarioCriacao;
	}

	public void setUsuarioCriacao(Usuario usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}
	
	public String getNomeUsuario() {
		if (usuarioCriacao != null) {
			return usuarioCriacao.getNome();
		} else {
			return "-";
		}
	}

	public Quarto(int numero, int capacidade, String descricao, boolean ocupado) {
		this.numero = numero;
		this.capacidade = capacidade;
		this.descricao = descricao;
		this.ocupado = ocupado;
		this.setId();
		this.usuarioCriacao = Usuario.usuarioLogado;

		quartos.add(this);
	}

	public static Quarto cadastraQuartoInterface(int numero, int capacidade, String descricao) {
		Quarto novo_quarto = new Quarto(numero, capacidade, descricao, false);
		ConfigArquivoQuartos.atualizaQuartos();

		return novo_quarto;
	}

	public static Quarto getQuarto(int numero) {
		for (Quarto quarto : quartos) {
			if (quarto.numero == numero) {
				return quarto;
			}
		}
		return null;
	}

	public static Quarto getQuartoDisponivel(int numero) {
		for (Quarto quarto : quartos) {
			if (quarto.numero == numero && quarto.isOcupado() == false) {
				return quarto;
			}
		}
		return null;
	}

	public static String quartosDisponiveis() {
		String quartosDisponiveis = "";
		for (Quarto quarto : Quarto.quartos) {
			if (!quarto.isOcupado()) {
				quartosDisponiveis += quarto.getNumero() + " ";
			}
		}
		if (quartosDisponiveis.isEmpty()) {
			quartosDisponiveis = "Nenhum";
		}
		return quartosDisponiveis;
	}

	public void deletaQuarto() {
		Quarto.quartos.remove(this);
		ConfigArquivoQuartos.atualizaQuartos();
	}
}
