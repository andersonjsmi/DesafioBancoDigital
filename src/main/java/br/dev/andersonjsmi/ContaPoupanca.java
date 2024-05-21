package br.dev.andersonjsmi;

public class ContaPoupanca extends Conta {

	public ContaPoupanca (Cliente cliente) {
		super(cliente);
	}

	@Override
	public void imprimirExtrato () {
		System.out.println("=== Extrato Conta Poupança ===");
		super.imprimirInfosComuns();
	}

	public void rendimento (double selic, double taxaReferencial){
		if (selic < 0.085){
			depositar((selic * 0.7 + taxaReferencial) * saldo);
		} else {
			depositar((0.0005 + taxaReferencial) * saldo);
		}
	}
}
