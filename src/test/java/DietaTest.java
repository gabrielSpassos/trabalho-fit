import services.Dieta;
import model.Usuario;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DietaTest {

    Usuario usuarioNormal = new Usuario();
    Usuario usuarioMagro = new Usuario();
    Usuario usuarioObeso = new Usuario();

    public DietaTest(){
        usuarioNormal.setAltura(1.90);
        usuarioNormal.setPeso(80);
        usuarioMagro.setAltura(1.95);
        usuarioMagro.setPeso(56.4);
        usuarioObeso.setAltura(1.65);
        usuarioObeso.setPeso(90.4);
    }

    @Test
    public void deveRetornarCaloriasLimite250(){
        Dieta dieta = new Dieta(usuarioNormal);
        double esperado = 250;
        double retorno = dieta.decideCaloriasLimites();
        assertEquals("Deu erro na caloria limite",esperado,retorno);
    }

    @Test
    public void deveRetornarCaloriasDiarias2000(){
        Dieta dieta = new Dieta(usuarioNormal);
        double esperado = 2000;
        double retorno = dieta.decideTotalCaloriasLimiteDiarias();
        assertEquals("Não retornou limite diário igual",esperado,retorno);
    }

    @Test
    public void deveRetornarCaloriasLimite400(){
        Dieta dietaMagro = new Dieta(usuarioMagro);
        double esperado = 400;
        double retorno = dietaMagro.decideCaloriasLimites();
        assertEquals("Deu erro na caloria limite",esperado,retorno);
    }

    @Test
    public void deveRetornarCaloriasDiarias4000(){
        Dieta dietaMagro = new Dieta(usuarioMagro);
        double esperado = 4000;
        double retorno = dietaMagro.decideTotalCaloriasLimiteDiarias();
        assertEquals("Não retornou limite diário igual",esperado,retorno);
    }
    @Test
    public void deveRetornarCaloriasLimite100(){
        Dieta dietaMagro = new Dieta(usuarioObeso);
        double esperado = 100;
        double retorno = dietaMagro.decideCaloriasLimites();
        assertEquals("Deu erro na caloria limite",esperado,retorno);
    }

    @Test
    public void deveRetornarCaloriasDiarias1250(){
        Dieta dietaMagro = new Dieta(usuarioObeso);
        double esperado = 1250;
        double retorno = dietaMagro.decideTotalCaloriasLimiteDiarias();
        assertEquals("Não retornou limite diário igual",esperado,retorno);
    }

}
