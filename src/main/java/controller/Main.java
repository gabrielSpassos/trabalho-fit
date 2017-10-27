package controller;

import model.Usuario;
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

        byte sair = 0;
        do {
            String opcao = menu.menuInicial();
            switch (opcao){
                case "1":
                    byte sairLogin = 0;
                    try {
                        usuarioLogin = menu.caseLogin();
                        menu.listarDadosLogin(usuarioLogin);
                        menu.mostraImcUsuario(usuarioLogin);
                    } catch (NullPointerException e) {
                        System.out.println(ANSI_RED + "Login Invalido" + ANSI_RESET);
                        sairLogin = 1;
                    }

                    while(sairLogin == 0) {
                        String opcaoLogin = menu.menuLogin();
                        switch (opcaoLogin) {
                            case "1":
                                menu.listarDadosLogin(usuarioLogin);
                                menu.mostraImcUsuario(usuarioLogin);
                                break;
                            case "2":
                                String opcaoDeRefeicao = menu.imprimeMenuRefeicoes(usuarioLogin);
                                String validadorDoIf = menu.validaInsercaoRefeicao();
                                if(validadorDoIf.equals("1")){
                                    menu.mostrarCaloriasPorNome(usuarioLogin,opcaoDeRefeicao);
                                }else if(validadorDoIf == "2"){

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
