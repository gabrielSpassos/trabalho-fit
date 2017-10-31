package controller;

import model.Usuario;
import services.Dieta;
import view.Menu;
import model.MinhaException;

public class Main {
    public static void main(String[] args){

        Menu menu = new Menu();
        Usuario usuarioLogin = new Usuario();

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
                        menu.mostrarMensagemErro(e);
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
                                if(!(opcaoDeRefeicao.equals("1"))|| !(opcaoDeRefeicao.equals("2")) || !(opcaoDeRefeicao.equals("3"))){
                                    break;
                                }
                                String validadorDoIf = menu.validaInsercaoRefeicao();
                                if(validadorDoIf.equals("1")){
                                    try {
                                        double caloriasTotaisPorNome = menu.mostrarCaloriasTotaisPorNome(usuarioLogin,opcaoDeRefeicao);
                                        menu.mostrarRestanteDeCaloriasDiarias(dietaMetodo, caloriasTotaisPorNome, primeiraRefeicaoDia);
                                    }catch (MinhaException e){
                                        menu.mostrarMensagemErro(e);
                                        continue;
                                    }
                                    if(primeiraRefeicaoDia){
                                        primeiraRefeicaoDia = false;
                                    }
                                }else if(validadorDoIf.equals("2")){
                                    menu.mostrarMensagemVolteSempre();
                                }else{
                                    menu.mostrarMensagemOpcaoInvalida();
                                }
                                break;
                            case "x":
                                sairLogin = 1;
                                break;
                            case "X":
                                sairLogin = 1;
                                break;
                            default:
                                menu.mostrarMensagemOpcaoInvalida();
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
                                menu.mostrarMensagemErro(e);
                                continue;
                            }
                            break;
                        case "4":
                            try{
                                menu.caseExcluiUsuarioPorNome();
                            }catch (MinhaException e){
                                menu.mostrarMensagemErro(e);
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
                            menu.mostrarMensagemOpcaoInvalida();
                    }
                    break;
                case "x":
                    sair = 1;
                    break;
                case "X":
                    sair = 1;
                    break;
                default:
                    menu.mostrarMensagemOpcaoInvalida();

            }
        }while(sair == 0);

        menu.mostrarMensagemDespedida();
    }
}
