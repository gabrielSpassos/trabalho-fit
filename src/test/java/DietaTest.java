import services.Dieta;
import model.Usuario;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DietaTest {

    Usuario u = new Usuario();

    public DietaTest(){
        u.setAltura(1.90);
        u.setPeso(80);
    }

    @Test
    public void deveRetornarCaloriasLimite100(){
        Dieta dieta = new Dieta(u);
        System.out.println("Peso: "+u.getAltura());
        System.out.println("Altura: "+u.getPeso());
        double esperado = 100;
        double retorno = dieta.decideCaloriasLimites();
        assertEquals("Deu erro na caloria limite",esperado,retorno);

    }
}
