package br.dev.andersonjsmi;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private List<Cliente> clientes = new ArrayList<>();
    private List<Conta> contas = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    public void run() {
        System.out.println("Digite o nome do Banco");
        String nomeBanco = scanner.nextLine();

        Banco banco = new Banco();
        banco.setNome(nomeBanco);

        while (true){
            System.out.println(String.format("Bem vindo ao Banco %s digite uma opção para continuar", banco.getNome()));
            System.out.println("1 - Criar Cliente");
            System.out.println("2 - Criar Conta");
            System.out.println("3 - Realizar Saque");
            System.out.println("4 - Realizar Depósito");
            System.out.println("5 - Realizar Transferencia");
            System.out.println("6 - Imprimir Extrato");

            int option = Integer.parseInt(scanner.nextLine());

            switch (option){
                case 1:
                    createCliente();
                    break;
                case 2:
                    createConta();
                    break;
                case 3:
                    saque();
                    break;
                case 4:
                    deposito();
                    break;
                case 5:
                    transferencia();
                    break;
                case 6:
                    extrato();
                    break;
            }
        }

    }

    public void createCliente () {
        System.out.println("Digite o nome do cliente:");
        String nome = scanner.nextLine();
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        this.clientes.add(cliente);
        System.out.println(String.format("Cliente %s adicionado!", cliente.getNome()));
    }

    public void createConta () {
        Conta conta = null;
        Cliente cliente;
        AtomicInteger count = new AtomicInteger();
        System.out.println("Selecione o cliente:");

        this.clientes.forEach(c -> {
            count.getAndIncrement();
            System.out.println(String.format("%d - %s ", count.get(), c.getNome()));
        });

        int idCliente = Integer.parseInt(scanner.nextLine());

        cliente = this.clientes.get(idCliente - 1);

        System.out.println("Digite o tipo de operaço");
        System.out.println("1 - Corrente");
        System.out.println("2 - Poupança");
        int op = Integer.parseInt(scanner.nextLine());

        switch (op){
            case 1:
                conta = new ContaCorrente(cliente);
                break;
            case 2:
                conta = new ContaPoupanca(cliente);
                break;
        }


        this.contas.add(conta);
        System.out.println(String.format("Conta %d criada!", conta.getNumero()));
    }

    public void saque () {
        System.out.println("Digite o numero da conta:");
        int numConta = Integer.parseInt(scanner.nextLine());
        int endConta = procurarConta(numConta);
        Conta conta = this.contas.get(endConta);

        System.out.println("Digite o valor do saque: ");
        double valor = Double.parseDouble(scanner.nextLine());

        conta.sacar(valor);

    }

    public void deposito () {
        System.out.println("Digite o numero da conta:");
        int numConta = Integer.parseInt(scanner.nextLine());
        int endConta = procurarConta(numConta);
        Conta conta = this.contas.get(endConta);

        System.out.println("Digite o valor do depósito:");
        double valor = Double.parseDouble(scanner.nextLine());

        conta.depositar(valor);
    }

    public void transferencia () {
        Conta origem;
        Conta destino;

        System.out.println("Digite o numero da conta origem:");
        int origConta = Integer.parseInt(scanner.nextLine());
        origem = this.contas.get(procurarConta(origConta));

        System.out.println("Digite o numero da conta destino:");
        int destConta = Integer.parseInt(scanner.nextLine());
        int c = procurarConta(destConta);
        destino = this.contas.get(c);

        System.out.println("Digite o valor da transferência:");
        double valor = Double.parseDouble(scanner.nextLine());

            origem.transferir(valor, destino);
    }

    public void extrato () {
        System.out.println("Digite o numero da conta");
        int nunConta = Integer.parseInt(scanner.nextLine());
        int endConta = procurarConta(nunConta);
        Conta conta = contas.get(endConta);
        conta.imprimirExtrato();
    }

    public int procurarConta (int numConta) {
        AtomicInteger endereco = new AtomicInteger();
        AtomicInteger contador = new AtomicInteger(0);
        this.contas.stream().forEach(conta -> {

            if(conta.getNumero() == numConta){
                endereco.set(contador.get());
            }
            contador.getAndIncrement();

        });

        return endereco.get();
    }
}