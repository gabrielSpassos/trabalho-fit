package view;

import model.MinhaException;
import model.Usuario;
import controller.ControllerMenu;
import controller.ControllerAlimento;
import services.Dieta;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;


public class Menu {

    //variaveis para printar colorido no console
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";

    Scanner ler = new Scanner(System.in);
    ControllerMenu controllerMenu = new ControllerMenu();
    ControllerAlimento controllerAlimento = new ControllerAlimento();

    public String menuInicial(){
        System.out.println("\n**Projeto Fit da Massa**\n");
        System.out.println("**MENU**");
        System.out.println("1 - Logar");
        System.out.println("2 - Opções de manipulação do cadastro");
        System.out.println("X - Sair");
        System.out.print("\nDigite sua opção: ");
        String opcao = ler.nextLine();
        return opcao;
    }

    public String menuAdm() {
        System.out.println("\n**Projeto Fit da Massa**\n");
        System.out.println("**Menu de Cadastros**");
        System.out.println("1 - Cadastrar novo usuário");
        System.out.println("2 - Listar usuários cadastrados");
        System.out.println("3 - Atualizar Cadastro");
        System.out.println("4 - Excluir Cadastro");
        System.out.println("X - Sair");
        System.out.print("\nDigite sua opção: ");
        String opcao = ler.nextLine();
        return opcao;
    }

    public String menuLogin(){
        System.out.println("\n**Projeto Fit da Massa**\n");
        System.out.println("**Menu de Login**");
        System.out.println("1 - Mostrar suas informações");
        System.out.println("2 - Lista de Alimentos da Dieta");
        System.out.println("X - Voltar ao menu anterior");
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
        String mensagem = controllerMenu.createUsuario(nomePessoa,pesoPessoa,alturaPessoa);
        mostrarMensagemSucesso(mensagem);
    }

    public void caseListarUsuarios(){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        System.out.println("\n**Lista de Usuários**\n");
        List<Usuario> listaUsuarios;
        listaUsuarios = controllerMenu.listUsuario();
        for (int i = 0; i < listaUsuarios.size(); i++) {
            System.out.println("ID do Usuário: " + listaUsuarios.get(i).getId());
            System.out.println("Nome do Usuário: " + listaUsuarios.get(i).getNome());
            System.out.println("Peso do Usuário: " + decimalFormat.format(listaUsuarios.get(i).getPeso()) +"kg");
            System.out.println("Altura do Usuário: " + decimalFormat.format(listaUsuarios.get(i).getAltura())+"m");
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

    private void caseAtualizarUsuario(String nomeAtualizacao, int idAtualizacao){
        System.out.print("Digite o novo peso do(a) "+nomeAtualizacao+": ");
        double pesoPessoa = ler.nextDouble();
        System.out.print("Digite a nova altura do(a) "+nomeAtualizacao+": ");
        double alturaPessoa = ler.nextDouble();
        String mensagem = controllerMenu.updateUsuario(pesoPessoa,alturaPessoa,idAtualizacao);
        mostrarMensagemSucesso(mensagem);
    }

    public void caseExcluiUsuarioPorNome(){
        System.out.println("\n**Excluindo Usuário**\n");
        System.out.print("Digite o nome do usuário a ser excluído: ");
        String nomeExclusao = ler.nextLine();
        int idExclusao = controllerMenu.pegarIdPorNome(nomeExclusao);
        String mensagem = controllerMenu.deleteUsuario(idExclusao);
        mostrarMensagemSucesso(mensagem);
    }

    public String imprimeMenuRefeicoes(Usuario user){
        System.out.println("\n**Seleção de Refeição**\n");
        System.out.println("1 - Café da Manhã");
        System.out.println("2 - Almoço");
        System.out.println("3 - Janta");
        System.out.print("Digite sua opção: ");
        String opcaoRetorno = ler.nextLine();
        controllerAlimento.listarAlimentosDaDieta(user,opcaoRetorno);
        return opcaoRetorno;
    }

    public void mostraImcUsuario(Usuario user){
        System.out.printf("IMC do usuário: %.2f\n", controllerMenu.pegaImcUsuario(user));
    }

    public String validaInsercaoRefeicao (){
        System.out.println("\nQuer inserir uma refeição feita: ");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        System.out.print("Digite sua opção: ");
        String opcao = ler.nextLine();
        return opcao;
    }

    public double mostrarCaloriasTotaisPorNome(Usuario user, String opcaoDeRefeicao){
        System.out.print("\nDigite o nome da refeição feita: ");
        String nomeRefeicao = ler.nextLine();
        System.out.print("\nDigite quantos gramas/litros você comeu de "+nomeRefeicao+": ");
        double quantidadeEmGramas = ler.nextDouble();
        double caloriasTotais = controllerAlimento.mostraCaloriasDoAlimentoPorGramas(user, opcaoDeRefeicao,nomeRefeicao,quantidadeEmGramas);
        System.out.printf("Calorias totais ingeridas de "+nomeRefeicao+": "+caloriasTotais+"\n");
        return caloriasTotais;
    }

    public double mostrarRestanteDeCaloriasDiarias(Dieta dieta, double caloriasTotais, boolean primeiraRefeicaoDia){
        double subtracaoCaloriasDiarias = controllerAlimento.calculaSubtracaoDasCaloriasDiarias(dieta,caloriasTotais, primeiraRefeicaoDia);
        System.out.printf("Restante de calorias que voce pode comer hoje é: %.2f\n",subtracaoCaloriasDiarias);
        return subtracaoCaloriasDiarias;
    }

    public void mostrarMensagemVolteSempre(){
        System.out.println(ANSI_BLUE+ "Pode voltar sempre ;)"+ ANSI_RESET);
    }

    public void mostrarMensagemOpcaoInvalida(){
        System.out.println(ANSI_RED+ "Opção Inválida" + ANSI_RESET);
    }

    public void mostrarMensagemDespedida(){
        System.out.println(ANSI_BLUE+ "Thanks for trying FitApp :D"+ ANSI_RESET);
    }

    public void mostrarMensagemErro(MinhaException e){
        System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
    }

    private void mostrarMensagemSucesso(String mensagem){
        System.out.println(ANSI_PURPLE + mensagem + ANSI_RESET);
    }

}
