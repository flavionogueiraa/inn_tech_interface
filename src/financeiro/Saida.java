package financeiro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import arquivo.ConfigArquivoSaidas;
import usuario.Usuario;

public class Saida {
	static double totalSaidas;

	private int id;
	private double valor;
	private Date dataCriacao;
	private String motivo;
	private String observacoes;
	private Usuario usuarioCriacao;

	public static ArrayList<Saida> saidas = new ArrayList<>();
	private static int idCont = 0;

	public int getId() {
		return id;
	}

	private void setId() {
		Saida.idCont++;
		this.id = idCont;
	}

	public static double getTotalSaidas() {
		return totalSaidas;
	}

	public static void setTotalSaidas(double totalSaidas) {
		Saida.totalSaidas = totalSaidas;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public String getDataCriacaoFormatada() {
		if (this.dataCriacao != null) {
			SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return formatar.format(this.getDataCriacao());
		} else {
			return "-";
		}
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
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

	public Saida(double valor, Date dataCriacao, String motivo, String observacoes) {
		this.valor = valor;
		this.dataCriacao = dataCriacao;
		this.motivo = motivo;
		this.observacoes = observacoes;
		this.setId();
		this.usuarioCriacao = Usuario.usuarioLogado;

		saidas.add(this);
		Saida.totalSaidas += this.valor;
	}

	public static Saida cadastraSaidaInterface(double valor, Date dataCriacao, String motivo, String observacoes) {
		Saida nova_saida = new Saida(valor, dataCriacao, motivo, observacoes);
		ConfigArquivoSaidas.atualizaSaida();

		return nova_saida;
	}

	public void deletaSaida() {
		Saida.saidas.remove(this);
		ConfigArquivoSaidas.atualizaSaida();
	}

	public static double totalSaidaMes(int mes) {
		double totalSaidaMes = 0;
		for (Saida saida : Saida.saidas) {
			String[] data = saida.getDataCriacaoFormatada().split("/");
			int mesPagamento = Integer.parseInt(data[1]);
			if (mesPagamento == mes) {
				totalSaidaMes += saida.getValor();
			}
		}

		return totalSaidaMes;
	}

	@SuppressWarnings("deprecation")
	public static LinkedHashMap<String, Double> saidasMes() {
		LinkedHashMap<String, Double> saidasMes = new LinkedHashMap<String, Double>();
		int mesAtual = new Date().getMonth() + 1;
		int mesInicial = mesAtual - 11;

		int anoAtual = new Date().getYear() + 1900;
		int anoPassado = anoAtual - 1;

		if (mesInicial <= 0) {
			for (int i = mesAtual; i <= 12; i++) {
				double totalMes = Saida.totalSaidaMes(i);
				saidasMes.put((i + "/" + Integer.toString(anoPassado)), totalMes);
			}
			for (int i = 1; i <= mesAtual; i++) {
				double totalMes = Saida.totalSaidaMes(i);
				saidasMes.put((i + "/" + Integer.toString(anoAtual)), totalMes);
			}
		} else {
			for (int i = mesInicial; i <= mesAtual; i++) {
				double totalMes = Saida.totalSaidaMes(i);
				saidasMes.put((i + Integer.toString(anoAtual)), totalMes);
			}
		}

		return saidasMes;
	}
}
