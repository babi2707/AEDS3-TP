
/************************************************************
 * AEDS3 - TP01 
 * 
 * Integrantes: Bárbara Luciano e Luisa Nogueira
 * 
************************************************************/
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Conta {

    // --------------- atributos ---------------

    private int idConta;
    private String nomePessoa;
    private ArrayList<String> email;
    private String nomeUsuario;
    private String senha;
    private String cpf;
    private String cidade;
    private int transferenciasRealizadas;
    private float saldoConta;

    // -----------------------------------------

    // --------------- construtores ---------------

    public Conta() {
        this.idConta = 0;
        this.nomePessoa = "";
        this.email = new ArrayList<>();
        this.nomeUsuario = "";
        this.senha = "";
        this.cpf = "";
        this.cidade = "";
        this.transferenciasRealizadas = 0;
        this.saldoConta = 0;
    }

    public Conta(int idConta, String nomePessoa, ArrayList<String> email, String nomeUsuario, String senha, String cpf,
            String cidade, int transferenciasRealizadas, float saldoConta) {
        setIdConta(idConta);
        setNomePessoa(nomePessoa);
        setEmail(email);
        setNomeUsuario(nomeUsuario);
        setSenha(senha);
        setCpf(cpf);
        setCidade(cidade);
        setTransferenciasRealizadas(transferenciasRealizadas);
        setSaldoConta(saldoConta);
    }

    // --------------------------------------------

    // --------------- gets e sets ---------------

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getIdconta() {
        return this.idConta;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getNomePessoa() {
        return this.nomePessoa;
    }

    public void setEmail(ArrayList<String> email) {
        this.email = email;
    }

    public ArrayList<String> getEmail() {
        return this.email;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeUsuario() {
        return this.nomeUsuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setTransferenciasRealizadas(int transferenciasRealizadas) {
        this.transferenciasRealizadas = transferenciasRealizadas;
    }

    public int getTransferenciasRealizadas() {
        return this.transferenciasRealizadas;
    }

    public void setSaldoConta(float saldoConta) {
        this.saldoConta = saldoConta;
    }

    public float getSaldoConta() {
        return this.saldoConta;
    }

    // -------------------------------------------

    // --------------- metodos ---------------
    // ------- printar -------

    public Conta print() {
        return (this);
    }

    // -----------------------

    // ---------------------------------------
}

public class Banco {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int resp = 0, id = 0, r = 0;
        ArrayList<Conta> account = new ArrayList<Conta>();

        FileWriter file = new FileWriter("arquivo.txt");
        BufferedWriter saida = new BufferedWriter(file);

        // --------------- menu ---------------

        do {
            System.out.println("\n ----- Iniciando o Sistema --------");
            System.out.println("\n--------------- menu ---------------");
            System.out.println("\t1- Criar conta");
            System.out.println("\t2- Realizar transferencia");
            System.out.println("\t3- Ler registro (ID)");
            System.out.println("\t4- Atualizar registro");
            System.out.println("\t5- Deletar registro");
            System.out.println("\t6- Ordenar arquivo");
            System.out.println("------------------------------------\n");

            do {
                // -------- pegar a escolha do usuario --------
                System.out.print("Digite a opcao desejada: ");
                resp = sc.nextInt();

                // -------- mensagem de erro --------
                if (resp < 1 || resp > 6) {
                    System.out.print("Opcao invalida!");
                }
            } while (resp < 1 || resp > 6); // repetir enquanto a opcao for invalida

            // --------------- realizar a opcao desejada ---------------

            switch (resp) {
                case 1:
                    Conta cont = new Conta();
                    int answer = 0;
                    System.out.println("\n\nOpcao escolhida: \n\t1- Criar conta");

                    cont.setIdConta(id++);
                    System.out.println("Seu ID é: " + cont.getIdconta() + "\n\n");

                    System.out.print("Digite o Nome da Pessoa:");
                    cont.setNomePessoa(sc.next());

                    // -------- loop para adicionar varios emails --------
                    do {
                        int arroba = 0, ponto = 0; // variavel para verificar se o email tem @
                        String temp = "";

                        // -------- loop para verificar se o email tem @ --------
                        do {
                            System.out.print("Digite o Email:");
                            temp = sc.next(); // coloca o email em uma variavel temporaria

                            for (int i = 0; i < temp.length(); i++) {
                                if (temp.charAt(i) == '@') {
                                    arroba++;
                                } else if (temp.charAt(i) == '.' && i > temp.indexOf("@")) {
                                    ponto++;
                                }
                            }

                            if (arroba == 0 || ponto == 0) {
                                System.out.println("Email invalido!");
                            } else {
                                cont.getEmail().add(temp);
                            }

                        } while (arroba == 0 || ponto == 0);

                        System.out.println("Deseja adicionar um novo email? (1 - sim / 2 - não)");
                        answer = sc.nextInt();

                    } while (answer == 1);

                    // -------- loop para verificar se o username é repetido --------
                    int repeat = 0; // variavel para verificar se é repetido
                    do {
                        String tmp = "";
                        System.out.print("Digite o username:");
                        tmp = sc.next();

                        for (int i = 0; i < account.size(); i++) {
                            if (account.get(i).getNomeUsuario().contains(tmp)) {
                                repeat++;
                            } else {
                                repeat = 0;
                            }
                        }

                        if (repeat != 0) {
                            System.out.println("Usuario invalido! Esse username ja existe. Escolha outro!");
                        } else {
                            cont.setNomeUsuario(tmp);
                        }

                    } while (repeat != 0);

                    System.out.print("Digite a Senha:");
                    cont.setSenha(sc.next());

                    // ------ loop para verificar se o CPF é valido ------
                    do {
                        System.out.print("Digite o Cpf:");
                        cont.setCpf(sc.next());
                        if (cont.getCpf().length() != 11) {
                            System.out.println("CPF inválido!");
                        }

                    } while (cont.getCpf().length() != 11);

                    System.out.print("Digite a cidade:");
                    cont.setCidade(sc.next());

                    System.out.print("Digite o saldo da conta: R$");
                    cont.setSaldoConta(sc.nextFloat()); // saldo da conta

                    cont.setTransferenciasRealizadas(0); // Por uma nova conta não existe transferências

                    saida.write("(" + cont.getIdconta() + ") " + cont.getNomePessoa() + " " + cont.getEmail() + " "
                            + cont.getNomeUsuario() + " " + cont.getSenha() + " " + cont.getCpf() + " "
                            + cont.getCidade()
                            + " " + cont.getTransferenciasRealizadas() + " " + cont.getSaldoConta() + "\n");

                    account.add(cont);
                    System.out.println("\nSua conta foi cadastrada com sucesso!");
                    break;

                case 2:
                    System.out.println("\n\nOpcao escolhida: \n\t 2- Realizar transferencia");
                    break;

                case 3:
                    int numID = 0, count = 0;
                    System.out.println("\n\nOpcao escolhida: \n\t3- Ler registro (ID)\n");

                    // -------- pegar a escolha do usuario --------
                    System.out.print("Digite o numero do ID que deseja ler: ");
                    numID = sc.nextInt();

                    // ------ loop para ler todas as contas que estão gravadas no arquivo ------
                    for (int i = 0; i < account.size(); i++) { // --- enquanto i não for do tamanho do arquivo, continua
                                                               // o loop
                        if (account.get(i).getIdconta() == numID) {
                            System.out.println("\n\nID: " + account.get(i).getIdconta());
                            System.out.println("Nome da pessoa: " + account.get(i).getNomePessoa());
                            System.out.println("Email: " + account.get(i).getEmail());
                            System.out.println("Username: " + account.get(i).getNomeUsuario());
                            System.out.println("Senha: " + account.get(i).getSenha());
                            System.out.println("CPF: " + account.get(i).getCpf());
                            System.out.println("Cidade: " + account.get(i).getCidade());
                            System.out.println("Saldo da conta: " + account.get(i).getSaldoConta());
                            System.out.println(
                                    "Transferências já realizadas: " + account.get(i).getTransferenciasRealizadas());
                        } else {
                            count++;
                        }
                    }

                    if (count == account.size()) {
                        System.out.println("\nNumero ID não encontrado!");
                    } else {
                        System.out.println("\nArquivo de registros lido com sucesso!");
                    }

                    break;

                case 4:
                    System.out.println("\n\nOpcao escolhida: \n\t4- Atualizar registro");

                    int update = 0;

                    do {
                        int idUpdate, exists = 0;
                        do {
                            System.out.println("\nDigite o ID da conta que deseja atualizar: ");
                            idUpdate = sc.nextInt();

                            for (int i = 0; i < account.size(); i++) {
                                if (account.get(i).getIdconta() == idUpdate) {
                                    exists++;
                                    break;
                                }
                            }

                            if (exists == 0) {
                                System.out.println("\nConta nao existe!");
                            } else {
                                break;
                            }
                        } while (exists == 0);

                    } while (update != 0);

                    // -------- loop para atualizar a conta de acordo com o ID --------
                    for (int j = 0; j < account.size(); j++) {
                        System.out.print("Digite o Nome da Pessoa:");
                        account.get(j).setNomePessoa(sc.next());

                        // -------- loop para adicionar varios emails --------
                        do {
                            int arroba = 0, ponto = 0; // variavel para verificar se o email tem @
                            String temp = "";

                            // -------- loop para verificar se o email tem @ --------
                            do {
                                System.out.print("Digite o Email:");
                                temp = sc.next(); // coloca o email em uma variavel temporaria

                                for (int i = 0; i < temp.length(); i++) {
                                    if (temp.charAt(i) == '@') {
                                        arroba++;
                                    } else if (temp.charAt(i) == '.' && i > temp.indexOf("@")) {
                                        ponto++;
                                    }
                                }

                                if (arroba == 0 || ponto == 0) {
                                    System.out.println("Email invalido!");
                                } else {
                                    account.get(j).getEmail().add(temp);
                                }

                            } while (arroba == 0 || ponto == 0);

                            System.out.println("Deseja adicionar um novo email? (1 - sim / 2 - não)");
                            answer = sc.nextInt();

                        } while (answer == 1);

                        // -------- loop para verificar se o username é repetido --------
                        int repeat2 = 0; // variavel para verificar se é repetido
                        do {
                            String tmp = "";
                            System.out.print("Digite o username:");
                            tmp = sc.next();

                            for (int i = 0; i < account.size(); i++) {
                                if (account.get(i).getNomeUsuario().contains(tmp)) {
                                    repeat2++;
                                } else {
                                    repeat2 = 0;
                                }
                            }

                            if (repeat2 != 0) {
                                System.out.println("Usuario invalido! Esse username ja existe. Escolha outro!");
                            } else {
                                account.get(j).setNomeUsuario(tmp);
                            }

                        } while (repeat2 != 0);

                        System.out.print("Digite a Senha:");
                        account.get(j).setSenha(sc.next());

                        // ------ loop para verificar se o CPF é valido ------
                        do {
                            System.out.print("Digite o Cpf:");
                            account.get(j).setCpf(sc.next());
                            if (account.get(j).getCpf().length() != 11) {
                                System.out.println("CPF inválido!");
                            }

                        } while (account.get(j).getCpf().length() != 11);

                        System.out.print("Digite a cidade:");
                        account.get(j).setCidade(sc.next());

                        System.out.print("Digite o saldo da conta: R$");
                        account.get(j).setSaldoConta(sc.nextFloat()); // saldo da conta

                        account.get(j).setTransferenciasRealizadas(0); // Por uma nova conta não existe transferências

                        saida.write("(" + account.get(j).getIdconta() + ") " + account.get(j).getNomePessoa() + " " + account.get(j).getEmail() + " "
                                + account.get(j).getNomeUsuario() + " " + account.get(j).getSenha() + " " + account.get(j).getCpf() + " "
                                + account.get(j).getCidade()
                                + " " + account.get(j).getTransferenciasRealizadas() + " " + account.get(j).getSaldoConta() + "\n");
                    }

                    System.out.println("\nSua conta foi atualizada com sucesso!");
                    break;

                case 5:
                    System.out.println("\n\nOpcao escolhida: \n\t5- Deletar registro");
                    break;

                case 6:
                    System.out.println("\n\nOpcao escolhida: \n\t6- Ordenar arquivo");
                    break;

                default:
                    System.out.print("\n\nOpcao invalida!");
                    break;
            }

            // -------- perguntar se o usuario quer continuar --------
            do {
                System.out.print("\n\nDeseja continuar? (1 - sim / 0 - nao): ");
                r = sc.nextInt();

                // -------- mensagem de erro --------
                if (r != 1 && r != 0) {
                    System.out.print("Opção invalida!");
                }

                // -------- mensagem de despedida --------
                if (r == 0) {
                    System.out.println("\n\nObrigado por usar o nosso sistema!");
                    break;
                }
            } while (r != 1 && r != 0);

        } while (r == 1);

        // ------------------------------------
        saida.close();
    }

}
