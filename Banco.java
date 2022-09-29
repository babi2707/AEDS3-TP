
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
            } while (resp < 1 || resp > 6);

            // --------------- realizar a opcao desejada ---------------

            switch (resp) {
                case 1:
                    Conta cont = new Conta();
                    int answer = 0;
                    System.out.println("\n\nOpcao escolhida: \n\t1- Criar conta");

                    cont.setIdConta(id++);
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

                        System.out.println("Deseja adicionar um novo email? 1 para sim ou 2 para nao");
                        answer = sc.nextInt();

                    } while (answer == 1);

                    System.out.print("Digite o Nome de Usuario:");
                    cont.setNomeUsuario(sc.next());
                    System.out.print("Digite a Senha:");
                    cont.setNomePessoa(sc.next());
                    System.out.print("Digite o Cpf:");
                    cont.setNomePessoa(sc.next());
                    System.out.print("Digite a cidade:");
                    cont.setNomePessoa(sc.next());

                    saida.write("(" + cont.getIdconta() + ") " + cont.getNomePessoa() + " " + cont.getEmail() + " "
                            + cont.getNomeUsuario() + " " + cont.getSenha() + " " + cont.getCpf() + " "
                            + cont.getCidade()
                            + " " + cont.getTransferenciasRealizadas() + " " + cont.getSaldoConta() + "\n");

                    account.add(cont);
                    break;

                case 2:
                    System.out.println("\n\nOpcao escolhida: \n\t 2- Realizar transferencia");
                    break;

                case 3:
                    System.out.println("\n\nOpcao escolhida: \n\t3- Ler registro (ID)");
                    break;

                case 4:
                    System.out.println("\n\nOpcao escolhida: \n\t4- Atualizar registro");
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
                    System.out.print("Opcao invalida!");
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
