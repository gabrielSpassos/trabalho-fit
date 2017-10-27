import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner ler = new Scanner(System.in);
    Usuario pessoa = new Usuario();
    UsuarioDAO pessoaDAO = new UsuarioDAO();
    ControllerMenu controllerMenu = new ControllerMenu();

    public String menuInicial() {
        System.out.println("\n**Projeto Fit da Massa**\n");
        System.out.println("**MENU**");
        System.out.println("1 - Fazer Login");
        System.out.println("2 - Cadastrar novo usuário");
        System.out.println("3 - Listar usuários cadastrados");
        System.out.println("4 - Atualizar Cadastro");
        System.out.println("5 - Excluir Cadastro");
        System.out.println("6 - Lista de Alimentos da Dieta");
        System.out.println("X - Sair");
        System.out.print("\nDigite sua opção: ");
        String opcao = ler.nextLine();
        return opcao;
    }

    public Usuario caseLogin(){
        System.out.println("\n**Login**\n");
        System.out.print("Digite seu nome: ");
        String nomeLogin = ler.nextLine();
        return controllerMenu.loginUsuario(nomeLogin);
    }

    public void listarDadosLogin(Usuario user){
        System.out.println("\nLogin: "+user.getNome());
        System.out.println("Peso: "+user.getPeso());
        System.out.println("Altura: "+user.getAltura());
    }

    public void caseCriarUsuario(){

        System.out.println("\n**Inserindo Usuário**\n");
        System.out.print("Digite seu nome: ");
        String nomePessoa = ler.nextLine();
        System.out.print("Digite seu peso em KG: ");
        double pesoPessoa = ler.nextDouble();
        System.out.print("Digite sua altura em metros: ");
        double alturaPessoa = ler.nextDouble();
        controllerMenu.createUsuario(nomePessoa,pesoPessoa,alturaPessoa);

    }

    public void caseListarUsuarios(){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        System.out.println("\n**Lista de Usuários**\n");
        List<Usuario> listaUsuarios;
        listaUsuarios = controllerMenu.listUsuario();
        for (int i = 0; i < listaUsuarios.size(); i++) {
            System.out.println("ID do Usuário: " + listaUsuarios.get(i).getId());
            System.out.println("Nome do Usuário: " + listaUsuarios.get(i).getNome());
            System.out.println("Peso do Usuário: " + decimalFormat.format(listaUsuarios.get(i).getPeso()));
            System.out.println("Altura do Usuário: " + decimalFormat.format(listaUsuarios.get(i).getAltura()));
            System.out.println("\n");
        }
    }

    public void caseAtualizarUsuarioPegarNome(){
        System.out.println("\n**Atualizando Usuário**\n");
        System.out.print("Digite o nome do do usuário a ser alterado: ");
        String nomeAtualizacao = ler.nextLine();
        int idAtualizacao = controllerMenu.pegarIdPorNome(nomeAtualizacao);
        if(idAtualizacao != 0){
            caseAtualizarUsuario(nomeAtualizacao,idAtualizacao);
        }

    }

    public void caseAtualizarUsuario(String nomeAtualizacao, int idAtualizacao){
        System.out.print("Digite o novo peso do(a) "+nomeAtualizacao+": ");
        double pesoPessoa = ler.nextDouble();
        System.out.print("Digite a nova altura do(a) "+nomeAtualizacao+": ");
        double alturaPessoa = ler.nextDouble();
        controllerMenu.updateUsuario(pesoPessoa,alturaPessoa,idAtualizacao);
    }

    public void caseExcluiUsuarioPegarNome(){
        System.out.println("\n**Excluindo Usuário**\n");
        System.out.print("Digite o nome do usuário a ser excluído: ");
        String nomeExclusao = ler.nextLine();
        int idExclusao = controllerMenu.pegarIdPorNome(nomeExclusao);
        controllerMenu.deleteUsuario(idExclusao);
    }

    public void menuAlimentos(Usuario user){
        System.out.println("\n**Seleção de Refeição**\n");
        System.out.println("1 - Café da Manhã");
        System.out.println("2 - Almoço");
        System.out.println("3 - Janta");
        System.out.print("Digite sua opção: ");
        String opcaoRetorno = ler.nextLine();
        ControllerAlimento controllerAlimento = new ControllerAlimento();
        controllerAlimento.listarAlimentosDaDieta(user,opcaoRetorno);
    }

    public void mostraImcUsuario(Usuario user){
        System.out.printf("IMC do usuário: %.2f", controllerMenu.pegaImcUsuario(user));
    }
}
