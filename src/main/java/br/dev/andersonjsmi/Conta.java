package br.dev.andersonjsmi;

import lombok.Data;

import java.util.HashSet;

@Data
public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;
	protected HashSet<String> operacoes = new HashSet<>();

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
		if ((saldo > valor) && valor > 0.0 ) {
			//evita saque de valor acima do saldo e valor negativo
			saldo -= valor;
			System.out.println("Operação Realizada, Saldo atual: R$" + String.valueOf(saldo));
			operacoes.add("Saque no valor de " + (-valor));
		}
		else
			System.out.println("Você não tem saldo suficiente para realizar a operação! Saldo atual: R$" + saldo);
	}

	@Override
	public void depositar(double valor) {
		if(valor > 0.0) {
			//evita deposito de valor negativo
			saldo += valor;
			System.out.println("Operação Realizada, Saldo atual: R$" + String.valueOf(saldo));
			operacoes.add("Deposito no valor de " + valor);
		}
		else
			System.out.println("Valor incorreto para a operação!");

	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		if(saldo > valor || valor > 0)
			this.sacar(valor);
		contaDestino.depositar(valor);
		System.out.println("Transferência realizada!");
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));

		operacoes.forEach(System.out::println);
	}
}
