import com.sun.xml.internal.org.jvnet.mimepull.MIMEParsingException;

import java.util.ArrayList;
import java.util.List;

public class ControllerMenu {

    UsuarioDAO pessoaDAO = new UsuarioDAO();
    Usuario pessoa = new Usuario();

    public void createUsuario(String nome,double peso, double altura){
        Usuario pessoa = new Usuario();
        try {
            pessoa.setNome(nome);
            pessoa.setPeso(peso);
            pessoa.setAltura(altura);
            pessoaDAO.create(pessoa);
        }catch (MinhaException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Usuario> listUsuario(){

        List<Usuario> listaUsuarios = new ArrayList<Usuario>();

        try{
            listaUsuarios = pessoaDAO.read();

        }catch (MinhaException e){
            System.out.println(e.getMessage());
        }

        return listaUsuarios; //preciso falar para ver

    }

    public boolean validaNomePassadoComNomesBanco(String nomePassado){
        List<Usuario> listaNomes = new ArrayList<>();
        try {
            listaNomes = pessoaDAO.getNomeFromUsuario();
            for(int i = 0;i<listaNomes.size();i++){
                if(listaNomes.get(i).getNome().equalsIgnoreCase(nomePassado)){
                    return true;
                }
            }
            return false;
        }catch (MinhaException e){
            throw new MinhaException("Erro ao comparar lista de nomes com o nome passado");
        }
    }

    public int pegarIdPorNome(String nomeParametro){

        if(validaNomePassadoComNomesBanco(nomeParametro)) {
            try {
                int idAtualizacao = pessoaDAO.getIdbyName(nomeParametro);
                return idAtualizacao;
            } catch (MinhaException e) {
                System.out.println(e.getMessage());
            }
        }

        throw new MinhaException("Erro ao pegar o id pelo nome");
    }

    public void updateUsuario(double peso , double altura, int idAtualizacao){

        pessoa.setPeso(peso);
        pessoa.setAltura(altura);
        try{
            pessoaDAO.update(pessoa,idAtualizacao);
        }catch(MinhaException e){
            System.out.println(e.getMessage());
        }


    }

    public void deleteUsuario(int id){
        try{
            pessoaDAO.delete(id);
        }catch (MinhaException e){
            System.out.println(e.getMessage());
        }

    }


}
