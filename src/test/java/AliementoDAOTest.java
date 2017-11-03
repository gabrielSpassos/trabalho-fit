import dao.AlimentoDAO;
import model.Alimento;
import model.MinhaException;
import org.junit.Test;


import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;


public class AliementoDAOTest {


    @Test
    public void deveRetornar26CaloriasDoBanco(){
        double esperado = 26.00;
        AlimentoDAO foodDAO = new AlimentoDAO();
        double retorno = foodDAO.getCaloriasByNome("Água de coco verde");
        assertEquals("Deu erro ao pegar do bando", esperado,retorno);
    }


    @Test
    public void deveRetornarExceptionDoBanco(){

        AlimentoDAO foodDAO = new AlimentoDAO();
        try{
            foodDAO.getCaloriasByNome("dshajhds");
        }catch (MinhaException e){
            assertTrue(true);
        }

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
