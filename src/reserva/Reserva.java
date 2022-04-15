package reserva;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import arquivo.ConfigArquivoQuartos;
import arquivo.ConfigArquivoReservas;
import financeiro.Entrada;
import quarto.Quarto;

public class Reserva {
	private int id;
	private Date dataEstimadaCheckin;
	private Date dataEstimadaCheckout;
	private String hospede;
	private double valor;
	private Quarto quarto;
	private boolean pago;
	private String observacoes;

	public static ArrayList<Reserva> reservas = new ArrayList<>();
	private static int idCont = 0;

	public int getId() {
		return id;
	}

	private void setId() {
		Reserva.idCont++;
		this.id = idCont;
	}

	public Date getdataEstimadaCheckin() {
		return dataEstimadaCheckin;
	}

	public String getdataEstimadaCheckinFormatada() {
		if (this.dataEstimadaCheckin != null) {
			SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return formatar.format(this.getdataEstimadaCheckin());
		} else {
			return "-";
		}
	}

	public void setdataEstimadaCheckin(Date dataEstimadaCheckin) {
		this.dataEstimadaCheckin = dataEstimadaCheckin;
	}

	public Date getdataEstimadaCheckout() {
		return dataEstimadaCheckout;
	}

	public String getdataEstimadaCheckoutFormatada() {
		if (this.dataEstimadaCheckout != null) {
			SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return formatar.format(this.getdataEstimadaCheckout());
		} else {
			return "-";
		}
	}

	public void setdataEstimadaCheckout(Date dataEstimadaCheckout) {
		this.dataEstimadaCheckout = dataEstimadaCheckout;
	}

	public String getHospede() {
		return hospede;
	}

	public void setHospede(String hospede) {
		this.hospede = hospede;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public String isPagoSimNao() {
		if (this.isPago()) {
			return "Sim";
		} else {
			return "Nao";
		}
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Reserva(String hospede, Double valor, Date dataEstimadaCheckin, Date dataEstimadaCheckout,
			String observacoes, boolean pago, Quarto quarto) {
		this.hospede = hospede;
		this.valor = valor;
		this.dataEstimadaCheckin = dataEstimadaCheckin;
		this.dataEstimadaCheckout = dataEstimadaCheckout;
		this.observacoes = observacoes;
		this.pago = pago;
		this.quarto = quarto;
		this.setId();

		reservas.add(this);
	}

	public static Reserva cadastraReservaInterface(String hospede, Double valor, Date dataEstimadaCheckin,
			Date dataEstimadaCheckout, String observacoes, boolean pago, Quarto quarto) {
		Reserva nova_reserva = new Reserva(hospede, valor, dataEstimadaCheckin, null, observacoes, pago, quarto);
		ConfigArquivoReservas.atualizaReservas();

		nova_reserva.quarto.setOcupado(true);
		if (nova_reserva.isPago()) {
			new Entrada(valor, dataEstimadaCheckin, "Reserva do quarto " + quarto.getNumero(), true);
		}
		return nova_reserva;
	}

	public void deletaReserva() {
		Reserva.reservas.remove(this);
		ConfigArquivoReservas.atualizaReservas();
	}

	public static Reserva getReserva(int numeroQuarto) {
		for (Reserva reserva : reservas) {
			if (reserva.quarto.getNumero() == numeroQuarto) {
				return reserva;
			}
		}

		return null;
	}

	public void finalizaReserva() {
		this.dataEstimadaCheckout = new Date();
		this.quarto.setOcupado(false);
		ConfigArquivoQuartos.atualizaQuartos();
		new Entrada(this.valor, this.dataEstimadaCheckout, "Reserva do quarto " + this.quarto.getNumero(), true);
	}
}
