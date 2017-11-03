package controller;

import model.Alimento;
import model.MinhaException;
import model.Usuario;
import dao.AlimentoDAO;
import services.Calculadora;
import services.Dieta;
import view.Menu;

import java.util.List;

public class ControllerAlimento{

    AlimentoDAO alimentoDAO = new AlimentoDAO();

    public void listarAlimentosDaDieta(Usuario userDieta, String opcaoRefeicao){
        Dieta dieta = new Dieta(userDieta);
        double caloriasLimite = dieta.decideCaloriasLimites();
        imprimeListaDeAlimentos(opcaoRefeicao,caloriasLimite);
    }

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
                Menu menu = new Menu();
                menu.mostrarMensagemOpcaoInvalida();
        }

        if(alimentosList!=null){
            for(int i = 0;i<alimentosList.size();i++){
                System.out.println("\nNome do alimento: "+alimentosList.get(i).getNome());
                System.out.println("Calorias do alimento: "+alimentosList.get(i).getCalorias());
            }
        }
    }

    public double mostraCaloriasDoAlimentoPorGramas(Usuario userDieta, String opcaoDeRefeicao,String nomeDigitado, double quantidadeEmGramas){
        try{
            if(validaNomeAlimentoComBanco(nomeDigitado,userDieta,opcaoDeRefeicao)){
                double caloriasAlimento = alimentoDAO.getCaloriasByNome(nomeDigitado);
                Calculadora calculadora = new Calculadora();
                double caloriasAlimentoPorGramas = calculadora.calculaCaloriasbyGramas(quantidadeEmGramas,caloriasAlimento);
                return caloriasAlimentoPorGramas;
            }

            throw new MinhaException("Não foi localizado calorias desse alimento");
        }catch (MinhaException e){
            throw new MinhaException("Não foi localizado calorias desse alimento");
        }

    }

    private boolean validaNomeAlimentoComBanco(String nomeDigitado,Usuario userDieta,String opcaoRefeicao){
        List<Alimento> listaAlimentos;
        Dieta dieta = new Dieta(userDieta);
        double caloriasLimite = dieta.decideCaloriasLimites();
        Integer opcaoDeRefeicao = Integer.parseInt(opcaoRefeicao);
        try{
            listaAlimentos = alimentoDAO.read(caloriasLimite,opcaoDeRefeicao);
            for(int i = 0;i<listaAlimentos.size();i++){
                if(listaAlimentos.get(i).getNome().equalsIgnoreCase(nomeDigitado)){
                    return true;
                }
            }
            return false;
        }catch (MinhaException e){
            throw new MinhaException("Falha ao localizar nome no banco de dados");
        }
    }

    public double calculaSubtracaoDasCaloriasDiarias(Dieta dietaMetodo, double caloriasComidas, boolean primeiraRefeicaoDia){
        double totalCaloriasLimiteDiarias;
        Calculadora calculadora = new Calculadora();

        if(primeiraRefeicaoDia) {
            totalCaloriasLimiteDiarias = dietaMetodo.decideTotalCaloriasLimiteDiarias();
        }else{
            if(dietaMetodo.getTotalCaloriasLimiteDiarias()<=0){
                throw new MinhaException("Você já gastou todas as calorias diárias");
            }
            totalCaloriasLimiteDiarias = dietaMetodo.getTotalCaloriasLimiteDiarias();
        }
        double subtracaoCalorias = calculadora.calculaCaloriasRestantes(totalCaloriasLimiteDiarias,caloriasComidas);
        dietaMetodo.setTotalCaloriasLimiteDiarias(subtracaoCalorias);
        if(subtracaoCalorias<=0){
            double valorAbsolutoSubtracaoCalorias = Math.abs(subtracaoCalorias);
            throw new MinhaException("Você já gastou todas as calorias diárias, você comeu a mais "+valorAbsolutoSubtracaoCalorias+ " calorias do limite da sua dieta");
        }else{
            return subtracaoCalorias;
        }


    }


}
