package br.dev.andersonjsmi;

import lombok.Getter;


@Getter
public class Cliente {

	private String nome;

	public void setNome(String nome) {
		if(nome.isEmpty())
			System.out.println("O cliente precisa ter um nome cadastrado");
		else
			this.nome = nome;
	}
}
