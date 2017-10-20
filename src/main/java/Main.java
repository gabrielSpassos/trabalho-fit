import com.mysql.jdbc.integration.c3p0.MysqlConnectionTester;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Usuario pessoa = new Usuario();
        UsuarioDAO pessoaDAO = new UsuarioDAO();
        Scanner ler = new Scanner(System.in);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        //menu
        byte sair = 0;
        do {
            System.out.println("\n**Projeto Fit da Massa**\n\n");
            System.out.println("**MENU**");
            System.out.println("1 - Cadastrar novo usuário");
            System.out.println("2 - Listar usuários cadastrados");
            System.out.println("3 - Atualizar Cadastro");
            System.out.println("4 - Excluir Cadastro");
            System.out.println("X - Sair");
            System.out.println("\nDigite sua opção: ");
            String opcao = ler.nextLine();

            switch (opcao) {
                case "1":
                    System.out.println("**Inserindo Usuário**\n");
                    System.out.println("Digite seu nome: ");
                    pessoa.setNome(ler.nextLine());
                    System.out.println("Digite seu peso em KG: ");
                    pessoa.setPeso(ler.nextDouble());
                    System.out.println("Digite sua altura em metros: ");
                    pessoa.setAltura(ler.nextDouble());
                    pessoaDAO.create(pessoa);
                    Dieta diet = new Dieta(pessoa);
                    System.out.println("Resposta: " + diet.decideDieta());
                    break;
                case "2":
                    System.out.println("**Lista de Usuários**\n");
                    List<Usuario> listaUsuarios;
                    listaUsuarios = pessoaDAO.read();
                    System.out.println("\n");
                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        System.out.println("ID do Usuário: " + listaUsuarios.get(i).getId());
                        System.out.println("Nome do Usuário: " + listaUsuarios.get(i).getNome());
                        System.out.println("Peso do Usuário: " + decimalFormat.format(listaUsuarios.get(i).getPeso()));
                        System.out.println("Altura do Usuário: " + decimalFormat.format(listaUsuarios.get(i).getAltura()));
                        System.out.println("\n");
                    }
                    break;
                case "3":
                    System.out.println("**Atualizando Usuário**\n");
                    System.out.println("Digite o nome do do usuário a ser alterado: ");
                    String nomeAtualizacao = ler.nextLine();
                    int idAtualizacao = pessoaDAO.getIdbyName(nomeAtualizacao);
                    System.out.println("ID do "+nomeAtualizacao+": "+idAtualizacao);
                    System.out.println("Digite o novo peso do(a) "+nomeAtualizacao+": ");
                    pessoa.setPeso(ler.nextDouble());
                    System.out.println("Digite a nova altura do(a) "+nomeAtualizacao+": ");
                    pessoa.setAltura(ler.nextDouble());
                    pessoaDAO.update(pessoa,idAtualizacao);
                    break;
                case "4":
                    System.out.println("**Excluindo Usuário**\n");
                    System.out.println("Digite o nome do usuário a ser excluído: ");
                    String nomeExclusao = ler.nextLine();
                    int idExclusao = pessoaDAO.getIdbyName(nomeExclusao);
                    System.out.println("ID do "+nomeExclusao+": "+idExclusao);
                    pessoaDAO.delete(idExclusao);
                    break;
                case "x":
                    sair = 1;
                    break;
                case "X":
                    sair = 1;
                    break;
                default:
                    System.out.println("Opção Inválida");
            }
        }while(sair == 0);



    }
}
