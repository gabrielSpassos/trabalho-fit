import org.junit.Test;

import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;

public class AlimentoTest {

    Scanner ler = new Scanner(System.in);

    @Test
    public void negaValorNegativoNoParametro() {
        String esperado = "Não pode ser informado valor negativo de peso";
        Alimento food = new Alimento();
        String retorno = food.calculaCaloriasbyGramas(-10);
        assertEquals(esperado, retorno);
    }

    @Test
    public void deveRetornar1() {
        double gramasInformadas = 100;
        Alimento food = new Alimento();
        food.setCalorias(1);
        String esperado = "1.0";
        assertEquals("Cálculo deu errado", esperado, food.calculaCaloriasbyGramas(gramasInformadas));
    }

    @Test
    public void deveRetornarCalculoCaloriasDoBanco(){
        double gramasInformadas = 75;
        Alimento food = new Alimento();
        AlimentoDAO foodDAO = new AlimentoDAO();
        double caloriasAlimento = foodDAO.getCaloriasByNome("Chocolate Branco");
        System.out.println(caloriasAlimento);
        food.setCalorias(caloriasAlimento);
        String esperado = "424.95";
        String retorno = food.calculaCaloriasbyGramas(gramasInformadas);
        assertEquals("Não funcionou pegar calorias do banco e calcular pelas gramas informadas",esperado,retorno);
    }
}
