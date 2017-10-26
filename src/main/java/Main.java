import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {
        //balaca para printar colorido no console
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";

        AlimentoDAO alimentoDAO = new AlimentoDAO();
        Menu menu = new Menu();
        Usuario usuarioLogin = new Usuario();

        byte sair = 0;
        do {
            String opcao = menu.menuInicial();
            switch (opcao) {
                case "1":
                    try {
                        usuarioLogin = menu.caseLogin();
                        menu.listarDadosLogin(usuarioLogin);
                    }catch (NullPointerException e){
                        System.out.println(ANSI_RED + "Login Invalido" + ANSI_RESET);
                    }
                    break;
                case "2":
                    menu.caseCriarUsuario();
                    break;
                case "3":
                    menu.caseListarUsuarios();
                    break;
                case "4":
                    try{
                        menu.caseAtualizarUsuarioPegarNome();
                    }catch (MinhaException e){
                        System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
                        continue;
                    }
                    break;
                case "5":
                    try{
                        menu.caseExcluiUsuarioPegarNome();
                    }catch (MinhaException e){
                        System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
                        continue;
                    }
                    break;
                case "6":
                    menu.menuAlimentos(usuarioLogin);
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
