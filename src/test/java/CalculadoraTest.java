import org.junit.Test;

import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;

public class CalculadoraTest {

    @Test
    public void negaValorNegativoNoParametro() {
        String esperado = "Não pode ser informado valor negativo de peso";
        Calculadora calculadora = new Calculadora();
        String retorno = calculadora.calculaCaloriasbyGramas(-10,1);
        assertEquals(esperado, retorno);
    }

    @Test
    public void deveRetornar1() {
        double gramasInformadas = 100;
        Alimento food = new Alimento();
        Calculadora calculadora = new Calculadora();
        food.setCalorias(1);
        String esperado = "1.0";
        assertEquals("Cálculo deu errado", esperado, calculadora.calculaCaloriasbyGramas(gramasInformadas,food.getCalorias()));
    }

    @Test
    public void deveRetornarCalculoCaloriasDoBanco(){
        double gramasInformadas = 75;
        Alimento food = new Alimento();
        AlimentoDAO foodDAO = new AlimentoDAO();
        Calculadora calculadora = new Calculadora();
        double caloriasAlimento = foodDAO.getCaloriasByNome("Chocolate Branco");
        System.out.println(caloriasAlimento);
        food.setCalorias(caloriasAlimento);
        String esperado = "424.95";
        String retorno = calculadora.calculaCaloriasbyGramas(gramasInformadas,food.getCalorias());
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
