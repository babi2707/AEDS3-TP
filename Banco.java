
/************************************************************
 * AEDS3 - TP01 
 * 
 * Integrantes: Bárbara Luciano e Luisa Nogueira
 * 
************************************************************/
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

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

    // ------- dados em bytes -------

    public byte[] toByteArray() throws Exception {
        ByteArrayOutputStream put = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(put);

        // --- escreve os dados ---
        data.writeInt(getIdconta());
        data.writeUTF(getNomePessoa());

        // --- escreve todos os emails ---
        for (int i = 0; i < getEmail().size(); i++) {
            data.writeUTF(getEmail().get(i));
        }

        data.writeUTF(getNomeUsuario());
        data.writeUTF(getSenha());
        data.writeUTF(getCpf());
        data.writeUTF(getCidade());
        data.writeInt(getTransferenciasRealizadas());
        data.writeFloat(getSaldoConta());

        put.close(); // fecha o fluxo de dados
        data.close(); // fecha o fluxo de dados

        return put.toByteArray(); // retorna os dados em bytes

    }

    // -----------------------

    // ---------------------------------------
}

public class Banco {

    // --------------- CREATE ---------------
    public static boolean create(RandomAccessFile arq, Conta conta) throws Exception {
        boolean resp = false;

        try {
            arq.seek(arq.length()); // Ponteiro vai para o final do arquivo
            arq.writeByte(0); // Lápide
            arq.writeInt(conta.toByteArray().length);// Escreve os dados em bytes

            arq.writeInt(conta.getIdconta());
            arq.writeUTF(conta.getNomePessoa());

            // --- escreve todos os emails ---
            for (int i = 0; i < conta.getEmail().size(); i++) {
                arq.writeUTF(conta.getEmail().get(i));
            }

            arq.writeUTF(conta.getNomeUsuario());
            arq.writeUTF(conta.getSenha());
            arq.writeUTF(conta.getCpf());
            arq.writeUTF(conta.getCidade());
            arq.writeInt(conta.getTransferenciasRealizadas());
            arq.writeFloat(conta.getSaldoConta());

            arq.seek(0); // Ponteiro vai para o início do arquivo
            resp = true;
        } catch (Exception e) {
            throw new Exception("Erro ao inserir registro");
        }

        return resp;
    }

    // --------------------------------------

    // --------------- READ ---------------

    public static Conta readId(RandomAccessFile arq, int pesquisa) throws Exception {

        try {
            Conta conta = new Conta();
            arq.seek(4); // Ponteiro no inicio do arquivo

            while (arq.getFilePointer() < arq.length()) { // Até o ponteiro chegar ao final do arquivo
                if (arq.readByte() == 0) {
                    int tam = arq.readInt();

                    conta.setIdConta(arq.readInt());

                    if (conta.getIdconta() == pesquisa) { // Caso o id da conta seja igual ao id procurado
                        conta.setNomePessoa(arq.readUTF());

                        String[] emails = new String[1000]; // armazenar os emails

                        // --- ler todos os emails ---
                        for (int i = 0; i < conta.getEmail().size(); i++) {
                            emails[i] = arq.readUTF();
                        }

                        conta.setEmail(new ArrayList<String>(Arrays.asList(emails))); // adiciona os emails no arraylist

                        conta.setNomeUsuario(arq.readUTF());
                        conta.setSenha(arq.readUTF());
                        conta.setCpf(arq.readUTF());
                        conta.setCidade(arq.readUTF());
                        conta.setTransferenciasRealizadas(arq.readInt());
                        conta.setSaldoConta(arq.readFloat());

                        return conta;
                    } else {
                        arq.skipBytes(tam - 4); // Pula o restante do registro
                    }
                } else {
                    arq.skipBytes(arq.readInt()); // Pula o registro todo
                }
            }

            return null;

        } catch (Exception e) {
            System.out.println("Não foi possível ler o registro!");
            return null;
        }
    }

    public static Conta readUser(RandomAccessFile arq, String pesquisa) {
        try {
            Conta conta = null;
            boolean searchUser = false;

            arq.seek(4); // Ponteiro no inicio do arquivo
            while (arq.getFilePointer() < arq.length() && !searchUser) {
                if (arq.readByte() == 0) {
                    conta = new Conta();
                    arq.readInt();

                    conta.setIdConta(arq.readInt());
                    conta.setNomePessoa(arq.readUTF());

                    String[] emails = new String[1000]; // armazenar os emails

                    // --- ler todos os emails ---
                    for (int i = 0; i < conta.getEmail().size(); i++) {
                        emails[i] = arq.readUTF();
                    }

                    conta.setEmail(new ArrayList<String>(Arrays.asList(emails))); // adiciona os emails no arraylist

                    conta.setNomePessoa(arq.readUTF());
                    conta.setSenha(arq.readUTF());
                    conta.setCpf(arq.readUTF());
                    conta.setCidade(arq.readUTF());
                    conta.setTransferenciasRealizadas(arq.readInt());
                    conta.setSaldoConta(arq.readFloat());

                    if (conta.getNomeUsuario().equals(pesquisa)) {
                        searchUser = true;
                    }
                } else { // arquivo ecluido
                    arq.skipBytes(arq.readInt());
                }
            }

            return conta;
        } catch (Exception e) {
            System.out.println("Não foi possivel ler o registro!");
            return null;
        }
    }

    // --------------------------------------

    // --------------- UPDATE ---------------

    public static boolean update(RandomAccessFile arq, Conta conta) {
        try {

            arq.seek(4); // Ponteiro no inicio do arquivo
            while (arq.getFilePointer() < arq.length()) { // Enquanto nao chegar no fim do arquivo
                if (arq.readByte() == 0) {
                    int tam = arq.readInt();

                    if (arq.readInt() == conta.getIdconta()) { // Verifica se o ID da conta é igual ao da conta a ser
                                                               // atualizada
                        if (tam >= conta.toByteArray().length) { // verifica o tamanho dos registros, se iguais encaixa
                                                                 // no mesmo registro
                            arq.writeUTF(conta.getNomePessoa());

                            // --- atualiza todos os emails ---
                            for (int i = 0; i < conta.getEmail().size(); i++) {
                                arq.writeUTF(conta.getEmail().get(i));
                            }

                            arq.writeUTF(conta.getNomeUsuario());
                            arq.writeUTF(conta.getSenha());
                            arq.writeUTF(conta.getCpf());
                            arq.writeUTF(conta.getCidade());
                            arq.writeInt(conta.getTransferenciasRealizadas());
                            arq.writeFloat(conta.getSaldoConta());

                            return true;

                        } else {// se o tam do registro for menor deve-se criar um novo registro
                            arq.seek(arq.getFilePointer() - 9); // Ponteiro no inicio do arq
                            arq.writeByte(1);
                            return create(arq, conta); // chama o metodo para criar outro registro
                        }
                    } else {
                        arq.skipBytes(tam - 4);
                    }
                } else {
                    arq.skipBytes(arq.readInt());
                }
            }

            return true;
        } catch (Exception e) {
            System.out.println("Não foi possivel atualizar o registro!");
            return false;
        }
    }

    // --------------------------------------

    // --------------- DELETE ---------------
    public static boolean delete(RandomAccessFile arq, Conta conta) {
        try {
            arq.seek(4); // Ponteiro no inico do arquivo
            while (arq.getFilePointer() < arq.length()) {
                if (arq.readByte() == 0) {
                    int tam = arq.readInt();
                    int id = arq.readInt();

                    if (id == conta.getIdconta()) { // Compara os ids, se iguais a conta sera excluida
                        arq.seek(arq.getFilePointer() - 9); // Ponteiro volta pro inicio do arquivo
                        arq.writeByte(1);
                        return true;
                    } else {
                        arq.skipBytes(tam - 4); // Pula o resto do registro
                    }
                } else {
                    arq.skipBytes(arq.readInt()); // Pula todo o registro
                }
            }

            return false;
        } catch (Exception e) {
            System.out.println("Arquivo nao existe ou nao pode ser excluido!");
            return false;
        }
    }
    // --------------------------------------

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int resp = 0, id = 0, r = 0, linhas = 0;
        Conta cont = new Conta();

        RandomAccessFile arq = new RandomAccessFile("contas.txt", "rw");

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
                    cont = new Conta();
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

                        cont.setNomeUsuario(tmp);

                        /*
                         * for (int i = 0; i < cont.; i++) {
                         * if (cont.getEmail().get(i).getNomeUsuario().contains(tmp)) {
                         * repeat++;
                         * } else {
                         * repeat = 0;
                         * }
                         * }
                         * if (repeat != 0) {
                         * System.out.
                         * println("Usuario invalido! Esse username ja existe. Escolha outro!");
                         * } else {
                         * cont.setNomeUsuario(tmp);
                         * }
                         */

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

                    arq.writeChars("(" + cont.getIdconta() + ") " + cont.getNomePessoa() + " " + cont.getEmail() + " "
                            + cont.getNomeUsuario() + " " + cont.getSenha() + " " + cont.getCpf() + " "
                            + cont.getCidade()
                            + " " + cont.getTransferenciasRealizadas() + " " + cont.getSaldoConta() + "\n");

                    if (create(arq, cont)) {
                        System.out.println("Conta criada com sucesso!");
                    } else {
                        System.out.println("Erro ao criar conta!");
                    }

                    break;

                
                case 2:
                System.out.println("\n\nOpcao escolhida: \n\t 2- Realizar transferencia");
                System.out.println("\nDigite a conta:");
                String conta1 = sc.next();
                System.out.println("\nDigite a conta para qual deseja transferir: ");
                String conta2 = sc.next();
                System.out.println("\nDigite o valor a ser transferido: ");
                float valorTrans = sc.nextFloat();
                
                break;
                
                case 3:
                int numID = 0, count = 0;

                System.out.println("\n\nOpcao escolhida: \n\t3- Ler registro (ID)\n");

                // -------- pegar a escolha do usuario --------
                System.out.print("Digite o numero do ID que deseja ler: ");
                numID = sc.nextInt();

                
                if (cont.getIdconta() == numID) {
                System.out.println("\n\nID: " + cont.getIdconta());
                System.out.println("Nome da pessoa: " + cont.getNomePessoa());
                System.out.println("Email: " + cont.getEmail());
                System.out.println("Username: " + cont.getNomeUsuario());
                System.out.println("Senha: " + cont.getSenha());
                System.out.println("CPF: " + cont.getCpf());
                System.out.println("Cidade: " + cont.getCidade());
                System.out.println("Saldo da conta: " + cont.getSaldoConta());
                System.out.println(
                "Transferências já realizadas: " +
                cont.getTransferenciasRealizadas());
                } else {
                count++;
                }
                
                if (count == id) {
                System.out.println("\nNumero ID não encontrado!");
                } else {
                System.out.println("\nArquivo de registros lido com sucesso!");
                }
                break;

                /*
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
                System.out.
                println("Usuario invalido! Esse username ja existe. Escolha outro!");
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
                account.get(j).setTransferenciasRealizadas(0); // Por uma nova conta não
                existe transferências
                arq.writeChars("(" + account.get(j).getIdconta() + ") " +
                account.get(j).getNomePessoa() + " "
                + account.get(j).getEmail() + " "
                + account.get(j).getNomeUsuario() + " " + account.get(j).getSenha() + " "
                + account.get(j).getCpf() + " "
                + account.get(j).getCidade()
                + " " + account.get(j).getTransferenciasRealizadas() + " "
                + account.get(j).getSaldoConta() + "\n");
                }
                System.out.println("\nSua conta foi atualizada com sucesso!");
                break;
                case 5:
                System.out.println("\n\nOpcao escolhida: \n\t5- Deletar registro");
                System.out.println("\nDigite o usuario da conta que deseja excluir: ");
                String auxAccount = sc.next(); // le o usuario que se quer deletar
                for (int i = 0; i < account.size(); i++) { // loop para verificar se o
                usuario na posicao i se
                // iguala ao usuario que se deseja deletar
                if (account.get(i).getNomeUsuario().equals(auxAccount)) {
                account.remove(i);
                }
                }
                System.out.println("\nA conta foi deletada com sucesso!");
                break;
                case 6:
                System.out.println("\n\nOpcao escolhida: \n\t6- Ordenar arquivo");
                int aux1 = 0;
                do {
                System.out.println("\n ----- Tipos de balanceamento --------");
                System.out.println("\t1- Intercalação Balanceada comum");
                System.out.
                println("\t2- Intercalação Balanceada com blocos de tamanho variável");
                System.out.
                println("\t3- Intercalação Balanceada com seleção por substituição");
                System.out.println("\t4- Intercalação usando n+1 arquivos");
                System.out.println("\t5- Intercalação Polifásica");
                System.out.println("\t6- Ordenar arquivo");
                System.out.println("------------------------------------\n");
                System.out.println("\nDigite o metodo de balanceamneto: ");
                } while (aux1 == 1);
                break;
                */

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
        arq.close();
    }

}
