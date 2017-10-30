package controller;

import model.Usuario;
import services.Dieta;
import view.Menu;
import model.MinhaException;

import java.sql.SQLException;



public class Main {
    public static void main(String[] args) throws SQLException {
        //variaveis para printar colorido no console
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";

        Menu menu = new Menu();
        Usuario usuarioLogin = new Usuario();
        ControllerAlimento controllerAlimento = new ControllerAlimento();

        byte sair = 0;
        do {
            String opcao = menu.menuInicial();
            switch (opcao){
                case "1":
                    //case de logar
                    byte sairLogin = 0;
                    boolean primeiraRefeicaoDia = true;
                    try {
                        usuarioLogin = menu.caseLogin();
                        menu.listarDadosLogin(usuarioLogin);
                        menu.mostraImcUsuario(usuarioLogin);

                    } catch (MinhaException e) {
                        System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
                        sairLogin = 1;
                    }

                    Dieta dietaMetodo = new Dieta(usuarioLogin);

                    while(sairLogin == 0) {
                        String opcaoLogin = menu.menuLogin();
                        switch (opcaoLogin) {
                            case "1":
                                //case de listar dados
                                menu.listarDadosLogin(usuarioLogin);
                                menu.mostraImcUsuario(usuarioLogin);
                                break;
                            case "2":
                                //case de informar refeicao
                                String opcaoDeRefeicao = menu.imprimeMenuRefeicoes(usuarioLogin);
                                String validadorDoIf = menu.validaInsercaoRefeicao();
                                if(validadorDoIf.equals("1")){
                                    String caloriasTotais = menu.mostrarCaloriasTotaisPorNome(usuarioLogin,opcaoDeRefeicao);
                                    Double subtracaoCalorias = Double.parseDouble(caloriasTotais);
                                    menu.mostrarRestanteDeCaloriasDiarias(dietaMetodo,subtracaoCalorias,primeiraRefeicaoDia);
                                    if(primeiraRefeicaoDia == true){
                                        primeiraRefeicaoDia = false;
                                    }
                                }else if(validadorDoIf.equals("2")){
                                    menu.mostrarMensagemVolteSempre();
                                }else{
                                    System.out.println("Opção Inválida");
                                }
                                break;
                            case "x":
                                sairLogin = 1;
                                break;
                            case "X":
                                sairLogin = 1;
                                break;
                            default:
                                System.out.println("Opção Inválida");
                        }
                    }

                    break;
                case "2":
                    //case de adm
                    String opcaoMenuADM = menu.menuAdm();
                    switch (opcaoMenuADM){
                        case "1":
                            menu.caseCriarUsuario();
                            break;
                        case "2":
                            menu.caseListarUsuarios();
                            break;
                        case "3":
                            try{
                                menu.caseAtualizarUsuarioPegarNome();
                            }catch (MinhaException e){
                                System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
                                continue;
                            }
                            break;
                        case "4":
                            try{
                                menu.caseExcluiUsuarioPorNome();
                            }catch (MinhaException e){
                                System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
                                continue;
                            }
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

        System.out.println("Thanks for trying FitApp :D");
    }
}
