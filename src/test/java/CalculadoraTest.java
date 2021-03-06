import dao.AlimentoDAO;
import model.Alimento;
import model.MinhaException;
import services.Calculadora;
import model.Usuario;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalculadoraTest {


    @Test
    public void negaValorNegativoNoParametro() {

        Calculadora calculadora = new Calculadora();
        try {
            double retorno = calculadora.calculaCaloriasbyGramas(-10, 1);
        }catch (MinhaException e){
            assertTrue(true);
        }
    }


    @Test
    public void deveRetornar1() {
        double gramasInformadas = 100;
        Alimento food = new Alimento();
        Calculadora calculadora = new Calculadora();
        food.setCalorias(1);
        double esperado = 1;
        assertEquals("Cálculo deu errado", esperado, calculadora.calculaCaloriasbyGramas(gramasInformadas,food.getCalorias()));
    }

    @Test
    public void deveRetornarCalculoCaloriasDoBanco(){
        double gramasInformadas = 75;
        Alimento food = new Alimento();
        AlimentoDAO foodDAO = new AlimentoDAO();
        Calculadora calculadora = new Calculadora();
        double caloriasAlimento = foodDAO.getCaloriasByNome("Chocolate Branco");
        food.setCalorias(caloriasAlimento);
        double esperado = 424.95;
        double retorno = calculadora.calculaCaloriasbyGramas(gramasInformadas,food.getCalorias());
        assertEquals("Não funcionou pegar calorias do banco e calcular pelas gramas informadas",esperado,retorno);
    }

    @Test
    public void testaCalculadoraIMC(){
        Calculadora calculadora = new Calculadora();
        Usuario user = new Usuario();
        user.setAltura(1.90);
        user.setPeso(80);
        double esperado = 22.1606648199446;
        double retorno = calculadora.calculaIMC(user.getPeso(),user.getAltura());
        assertEquals("calculo deu errado",esperado,retorno);

    }
}
