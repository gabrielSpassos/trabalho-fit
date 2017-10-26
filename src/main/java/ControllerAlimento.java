import java.util.List;

public class ControllerAlimento {

    AlimentoDAO alimentoDAO = new AlimentoDAO();

    private void imprimeListaDeAlimentos(String opcaoRefeicao, double caloriasLimite){
        List<Alimento> alimentosList = null;
        switch (opcaoRefeicao) {
            case "1":
                alimentosList = alimentoDAO.read(caloriasLimite,1);
                break;
            case "2":
                alimentosList = alimentoDAO.read(caloriasLimite,2);
                break;
            case "3":
                alimentosList = alimentoDAO.read(caloriasLimite,3);
                break;
            default:
                System.out.println("Opção inválida");
        }

        if(alimentosList!=null){
            for(int i = 0;i<alimentosList.size();i++){
                System.out.println("\nNome do alimento: "+alimentosList.get(i).getNome());
                System.out.println("Calorias do alimento: "+alimentosList.get(i).getCalorias());
            }
        }
    }


    public void listarAlimentosDaDieta(Usuario userDieta, String opcaoRefeicao){
        Dieta dieta = new Dieta(userDieta);
        double caloriasLimite = dieta.decideCaloriasLimites();
        imprimeListaDeAlimentos(opcaoRefeicao,caloriasLimite);
        System.out.println("Calorias Limite: "+caloriasLimite);

    }




}
