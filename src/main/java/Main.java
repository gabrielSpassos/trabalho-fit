import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {
        //balaca para printar colorido no console
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";

        AlimentoDAO alimentoDAO = new AlimentoDAO();
        Menu menu = new Menu();

        byte sair = 0;
        do {
            String opcao = menu.menuInicial();
            switch (opcao) {
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
                        menu.caseExcluiUsuarioPegarNome();
                    }catch (MinhaException e){
                        System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
                        continue;
                    }
                    break;
                case "5":
                    String opcaoRefeicao = menu.menuAlimentos();
                    List<Alimento> alimentosList = null;
                    switch (opcaoRefeicao) {
                    case "1":
                        alimentosList = alimentoDAO.read(100,1);
                        break;
                    case "2":
                        alimentosList = alimentoDAO.read(100,2);
                        break;
                    case "3":
                        alimentosList = alimentoDAO.read(100,3);
                        break;
                    default:
                        System.out.println("Opção inválida");
                }

                    if(alimentosList!=null){
                        for(int i = 0;i<alimentosList.size();i++){
                            System.out.println("Nome do alimento: "+alimentosList.get(i).getNome());
                            System.out.println("Calorias do alimento: "+alimentosList.get(i).getCalorias());
                            System.out.println("");
                        }
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
