import model.MinhaException;
import org.junit.Test;
import dao.UsuarioDAO;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class UsuarioDAOTest {


    @Test
    public void pegarIdPorNomeInvalido(){

        UsuarioDAO pessoaDAO = new UsuarioDAO();
        try {
            pessoaDAO.getIdbyName("testeInvalido");
        }catch (MinhaException e){
            assertTrue(true);
        }
    }



    @Test
    public void retornaId1(){
        int esperado = 1;
        UsuarioDAO pessoaDAO = new UsuarioDAO();
        int retorno = pessoaDAO.getIdbyName("Gabriel Santos dos Passos");
        assertEquals(esperado,retorno);
    }

    @Test
    public void retornaQuantidadeDeListaDeNomes(){
        Usuario user = new Usuario();
        user.setNome("Gabriel Santos dos Passos");
        Usuario user2 = new Usuario();
        user2.setNome("Rodrigo Dourado");
        Usuario user3 = new Usuario();
        user3.setNome("Ronaldo");
        Usuario user4 = new Usuario();
        user4.setNome("Natalia");
        List<Usuario> listaNomesEsperados = new ArrayList<Usuario>();
        listaNomesEsperados.add(0,user);
        listaNomesEsperados.add(1,user2);
        listaNomesEsperados.add(2,user3);
        listaNomesEsperados.add(3,user4);
        int esperado = listaNomesEsperados.size();
        List<Usuario> listaNomesRetorno = new ArrayList<Usuario>();
        UsuarioDAO pessoaDAO = new UsuarioDAO();
        listaNomesRetorno = pessoaDAO.getNomeFromUsuario();
        int retorno = listaNomesRetorno.size();
        assertEquals(esperado,retorno);

    }

    @Test
    public void retornaListaDeNomes(){
        Usuario user = new Usuario();
        user.setNome("Gabriel Santos dos Passos");
        Usuario user2 = new Usuario();
        user2.setNome("Rodrigo Dourado");
        Usuario user3 = new Usuario();
        user3.setNome("Natalia");
        Usuario user4 = new Usuario();
        user4.setNome("Ronaldo");
        List<Usuario> listaNomesEsperados = new ArrayList<Usuario>();
        listaNomesEsperados.add(0,user);
        listaNomesEsperados.add(1,user2);
        listaNomesEsperados.add(2,user3);
        listaNomesEsperados.add(3,user4);
        List<Usuario> listaNomesRetorno = new ArrayList<Usuario>();
        UsuarioDAO pessoaDAO = new UsuarioDAO();
        listaNomesRetorno = pessoaDAO.getNomeFromUsuario();
        assertEquals(listaNomesRetorno,listaNomesRetorno);

    }
}
