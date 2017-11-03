
import controller.ControllerMenu;
import model.Usuario;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ControllerMenuTest {

    @Test
    public void deveEncontrarNomeCorrespondente(){
        boolean esperado = true;
        ControllerMenu controllerMenu = new ControllerMenu();
        boolean retorno = controllerMenu.validaNomePassadoComNomesBanco("Gabriel SAntos dos Passos");
        assertEquals(esperado, retorno);
    }

    @Test
    public void naoDeveEncontrarNomeCorrespondente(){
        boolean esperado = false;
        ControllerMenu controllerMenu = new ControllerMenu();
        boolean retorno = controllerMenu.validaNomePassadoComNomesBanco("teste inexistente");
        assertEquals(esperado, retorno);
    }

    @Test
    public void deveRetornarId3(){
        int esperado = 3;
        ControllerMenu controllerMenu = new ControllerMenu();
        int retorno = controllerMenu.pegarIdPorNome("Rodrigo Dourado");
        assertEquals(esperado, retorno);
    }

    @Test
    public void deveRetornarIMC(){
        Usuario user = new Usuario();
        user.setPeso(87);
        user.setAltura(1.85);
        double esperado = 25.420014609203797;
        ControllerMenu controllerMenu = new ControllerMenu();
        double retorno = controllerMenu.pegaImcUsuario(user);
        assertEquals(esperado,retorno);
    }
}
