import dao.AlimentoDAO;
import model.Alimento;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class AliementoDAOTest {

    @Test
    public void deveRetornar26CaloriasDoBanco(){
        double esperado = 26.00;
        AlimentoDAO foodDAO = new AlimentoDAO();
        double retorno = foodDAO.getCaloriasByNome("Água de coco verde");
        assertEquals("Deu erro ao pegar do bando", esperado,retorno);
    }

    @Test
    public void deveRetornarErroDoBanco(){
        double esperado = 0;
        AlimentoDAO foodDAO = new AlimentoDAO();
        double retorno = foodDAO.getCaloriasByNome("dshajhds");
        assertEquals("Não deu erro ao pegar do bando", esperado,retorno);
    }

    @Test
    public void deveRetornarListaCheia(){
        List<Alimento> lista;
        AlimentoDAO foodDAO = new AlimentoDAO();
        lista = foodDAO.read(100,1);
        int tamamnho = 9;
        assertEquals("Lista está vazia, deveria ter Alimentos nela",tamamnho,lista.size());
    }

    @Test
    public void deveRetornarListaVazia(){
        List<Alimento> lista;
        AlimentoDAO foodDAO = new AlimentoDAO();
        lista = foodDAO.read(1,1);
        assertEquals("retornou erro!!",0,lista.size());

    }
}
