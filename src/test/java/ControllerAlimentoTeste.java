import controller.ControllerAlimento;
import dao.AlimentoDAO;
import model.MinhaException;
import model.Usuario;
import org.junit.Test;
import services.Dieta;

import static junit.framework.TestCase.assertEquals;

public class ControllerAlimentoTeste {
    Usuario userDieta = new Usuario();

    public ControllerAlimentoTeste(){
        userDieta.setAltura(1.90);
        userDieta.setPeso(50);
    }

    @Test
    public void retornaListaAlimentos(){
        ControllerAlimento controllerAlimento = new ControllerAlimento();
        controllerAlimento.listarAlimentosDaDieta(userDieta,"1");
    }

    @Test
    public void deveRetornar202(){
        ControllerAlimento controllerAlimento = new ControllerAlimento();
        String esperado = "202.0";
        String retorno = controllerAlimento.mostraCaloriasDoAlimentoPorGramas(userDieta,"3","Filé de frango",200);
        assertEquals(esperado,retorno);
    }

    @Test
    public void deveRetornarFalhaAoEncontrarAlimento(){
        String esperado = "Não foi localizado calorias desse alimento";
        ControllerAlimento controllerAlimento = new ControllerAlimento();
        String retorno = controllerAlimento.mostraCaloriasDoAlimentoPorGramas(userDieta,"3","alimento não registrado",200);
        assertEquals(esperado, retorno);
    }

    @Test
    public void deveRetornarValorNumericoComTrue(){
        ControllerAlimento controllerAlimento = new ControllerAlimento();
        Dieta dieta = new Dieta(userDieta);
        double esperado = 1800.0;
        double retorno = controllerAlimento.calculaSubtracaoDasCaloriasDiarias(dieta,200,true);
        assertEquals(esperado,retorno);
    }

    /*
    @Test
    public void deveRetornarValorNumericoPositivoComFalse(){
        ControllerAlimento controllerAlimento = new ControllerAlimento();
        Dieta dieta = new Dieta(userDieta);
        MinhaException esperado = new MinhaException("Você já gastou todas as calorias diárias");
        double retorno = controllerAlimento.calculaSubtracaoDasCaloriasDiarias(dieta,200,false);
        assertEquals(esperado,retorno);
    }

    */
}
