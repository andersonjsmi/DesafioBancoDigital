package br.dev.andersonjsmi;

public class ContaCorrente extends Conta {

	public ContaCorrente(Cliente cliente) {
		super(cliente);
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Corrente ===");
		super.imprimirInfosComuns();
	}

	public void taxaMensal (double valor){
		//taxa de uso da conta, permite que o saldo fique negativo
		this.saldo -= valor;
	}
	
}
