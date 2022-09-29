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
}

public class Banco {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int resp = 0, id = 0;
        ArrayList<Conta> account = new ArrayList<>();

        try {
            FileWriter file = new FileWriter("arquivoSequencial.txt");
            BufferedWriter saida = new BufferedWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // --------------- menu ---------------

        System.out.println("\n--------------- menu ---------------");
        System.out.println("\t1- Criar conta");
        System.out.println("\t2- Realizar transferencia");
        System.out.println("\t3- Ler registro (ID)");
        System.out.println("\t4- Atualizar registro");
        System.out.println("\t5- Deletar registro");
        System.out.println("\t6- Ordenar arquivo");
        System.out.println("------------------------------------\n");

        do {
            // -------- pegar a escolha do usuario  --------
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
                // ----- Criar conta ------
                Conta cont = new Conta(); 
                int answer = 1;
                System.out.println("\n\nOpcao escolhida: \n\t1- Criar conta\n");

                cont.setIdConta(id++);
                System.out.println("Digite o nome da pessoa:");
                cont.setNomePessoa(sc.next());
                do {
                    System.out.println("Digite o Email:");
                    cont.getEmail().add(sc.next());
                    System.out.println("Deseja adicionar um novo email? sim ou nao");
                    if(sc.next().equals("nao")){
                        answer = 0;
                    }
            
                } while (answer == 1);
                System.out.println("Digite o nome do Usuário:");
                cont.setNomeUsuario(sc.next());
                System.out.println("Digite a Senha:");
                cont.setNomePessoa(sc.next());
                System.out.println("Digite o CPF:");
                cont.setNomePessoa(sc.next());
                System.out.println("Digite a cidade:");
                cont.setNomePessoa(sc.next());
                break;

            case 2:
                //  ----- Realizar Transferência -----
                System.out.println("\n\nOpcao escolhida: \n\t 2- Realizar transferencia\n");
                
                break;

            case 3:
                System.out.println("\n\nOpcao escolhida: \n\t3- Ler registro (ID)\n");
                break;

            case 4:
                System.out.println("\n\nOpcao escolhida: \n\t4- Atualizar registro\n");
                break;

            case 5:
                System.out.println("\n\nOpcao escolhida: \n\t5- Deletar registro\n");
                break;

            case 6:
                System.out.println("\n\nOpcao escolhida: \n\t6- Ordenar arquivo\n");
                break;
        
            default:
            System.out.print("\n\nOpcao invalida!\n");
                break;
        }


        // ------------------------------------
    }

}
