import controller.ControllerAlimento;
import model.MinhaException;
import model.Usuario;
import org.junit.Test;
import services.Dieta;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;


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
        double esperado = 202.0;
        double retorno = controllerAlimento.mostraCaloriasDoAlimentoPorGramas(userDieta,"3","Filé de frango",200);
        assertEquals(esperado,retorno);
    }


    @Test
    public void deveRetornarFalhaAoEncontrarAlimento(){

        ControllerAlimento controllerAlimento = new ControllerAlimento();
        try {
            controllerAlimento.mostraCaloriasDoAlimentoPorGramas(userDieta, "3", "alimento não registrado", 200);
        }catch (MinhaException e){
            assertTrue(true);
        }
    }

    @Test
    public void deveRetornarValorNumericoComTrue(){
        ControllerAlimento controllerAlimento = new ControllerAlimento();
        Dieta dieta = new Dieta(userDieta);
        double esperado = 3800.0;
        double retorno = controllerAlimento.calculaSubtracaoDasCaloriasDiarias(dieta,200,true);
        assertEquals(esperado,retorno);
    }


    @Test
    public void deveRetornarValorNumericoPositivoComFalse(){
        ControllerAlimento controllerAlimento = new ControllerAlimento();
        Dieta dieta = new Dieta(userDieta);

        try {
            controllerAlimento.calculaSubtracaoDasCaloriasDiarias(dieta, 200, false);
        }catch (MinhaException e){
            assertTrue(true);
        }
    }


}
