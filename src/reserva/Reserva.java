package reserva;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import arquivo.ConfigArquivoQuartos;
import arquivo.ConfigArquivoReservas;
import financeiro.Entrada;
import quarto.Quarto;
import validacao.ValidacaoData;
import validacao.ValidacaoDouble;
import validacao.ValidacaoInt;

public class Reserva {
	private Date dataEstimadaCheckin;
	private Date dataEstimadaCheckout;
	private String hospede;
	private double valor;
	private Quarto quarto;
	private boolean pago;
	private String observacoes;

	public static ArrayList<Reserva> reservas = new ArrayList<>();

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
			return "N�o";
		}
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Reserva(Scanner sc, Quarto quarto) {
		System.out.println("Nome do h�spede:");
		this.hospede = sc.next();

		setValor(ValidacaoDouble.validacao(sc, "Valor: ", true));

		System.out.println("Informe a data e a hora:");
		sc.nextLine();
		String dataString = sc.nextLine();
		try {
			this.dataEstimadaCheckin = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dataString);
		} catch (ParseException ex) {
			Logger.getLogger(Reserva.class.getName()).log(Level.SEVERE, null, ex);
		}

		this.quarto = quarto;

		System.out.println("Observa��es:");
		this.observacoes = sc.nextLine();

		System.out.println("Foi paga?");
		System.out.println("[1] Sim");
		System.out.println("[0] N�o");
		int pago = sc.nextInt();

		this.pago = pago == 1;
		if (this.pago) {
			new Entrada(this.valor, this.dataEstimadaCheckin, "Reserva do quarto " + this.quarto.getNumero(), true);
			System.out.println("Aviso: Uma entrada foi criada!");
		}

		this.quarto.setOcupado(true);
		reservas.add(this);
		ConfigArquivoQuartos.atualizaQuartos();
		ConfigArquivoReservas.cadastraReserva(this);
	}

	public Reserva(String hospede, Double valor, Date dataEstimadaCheckin, Date dataEstimadaCheckout, String observacoes, boolean pago,
			Quarto quarto) {
		this.hospede = hospede;
		this.valor = valor;
		this.dataEstimadaCheckin = dataEstimadaCheckin;
		this.dataEstimadaCheckout = dataEstimadaCheckout;
		this.observacoes = observacoes;
		this.pago = pago;
		this.quarto = quarto;

		reservas.add(this);
	}

	public static Reserva cadastraReservaInterface(String hospede, Double valor, Date dataEstimadaCheckin, Date dataEstimadaCheckout,
			String observacoes, boolean pago, Quarto quarto) {
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

	public static void listaReservas() {
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		int i = 1;
		for (Reserva reserva : reservas) {
			System.out.println("ID: " + i);
			System.out.println("Nome do h�spede: " + reserva.getHospede());
			System.out.println("Valor: " + reserva.getValor());
			System.out.println("Data e hora: " + formatar.format(reserva.getdataEstimadaCheckin()));
			System.out.println("Observa��es: " + reserva.getObservacoes());
			System.out.println();
			i++;
		}
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

	public static void editaReserva(Scanner sc) {
		Reserva.listaReservas();
		int opcaoSaida = ValidacaoInt.validacao(sc, "Informe um ID para editar:", true);

		Reserva reserva;
		try {
			reserva = Reserva.reservas.get(opcaoSaida - 1);
		} catch (Exception e) {
			reserva = null;
		}

		if (reserva != null) {
			System.out.println("Nome do h�pede atual: " + reserva.hospede);
			System.out.println("Novo nome do h�spede:");
			reserva.setHospede(sc.nextLine());

			System.out.println("Valor atual: " + reserva.valor);
			reserva.setValor(ValidacaoDouble.validacao(sc, "Novo valor:", false));

			SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			System.out.println("Data atual: " + formatar.format(reserva.dataEstimadaCheckin));
			reserva.dataEstimadaCheckin = ValidacaoData.validacao(sc, "Informe a data e a hora:", false);

			System.out.println("Observacoes atuais: " + reserva.observacoes);
			System.out.print("Novas observa��es: ");
			reserva.setObservacoes(sc.nextLine());

			ConfigArquivoReservas.atualizaReservas();
		} else {
			System.out.println("ID inv�lido");
		}
	}

	public static void excluiReserva(Scanner sc) {
		Reserva.listaReservas();

		int id = ValidacaoInt.validacao(sc, "Digite o ID da reserva que deseja deletar:", true);
		Reserva reserva;
		try {
			reserva = Reserva.reservas.get(id - 1);
		} catch (Exception e) {
			reserva = null;
		}

		if (reserva != null) {
			Reserva.reservas.remove(reserva);
			reserva = null;
			ConfigArquivoReservas.atualizaReservas();
		} else {
			System.out.println("ID inv�lido");
		}
	}
}
