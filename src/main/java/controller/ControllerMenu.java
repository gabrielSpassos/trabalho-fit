package controller;

import dao.UsuarioDAO;
import model.Usuario;
import model.MinhaException;
import services.Calculadora;

import java.util.ArrayList;
import java.util.List;

public class ControllerMenu {

    //variaveis para printar colorido no console
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_PURPLE = "\u001B[35m";

    UsuarioDAO pessoaDAO = new UsuarioDAO();
    Usuario pessoa = new Usuario();

    public Usuario loginUsuario(String nomeLogin){
        Usuario user = null;
        try{
            List<Usuario> listaUsuario = new ArrayList<>();
            listaUsuario = pessoaDAO.read();
            for(int i = 0;i<listaUsuario.size();i++){
                if(listaUsuario.get(i).getNome().equalsIgnoreCase(nomeLogin)){
                    System.out.println(ANSI_PURPLE + "Login com sucesso"+ ANSI_RESET);
                    return listaUsuario.get(i);
                }
            }

            if (user == null){
                throw new MinhaException("usuário nulo");
            }

            return user;
        }catch (MinhaException e){
            throw new MinhaException("Login Invalido");
        }

    }


    public String createUsuario(String nome,double peso, double altura){
        Usuario pessoa = new Usuario();
        try {
            pessoa.setNome(nome);
            pessoa.setPeso(peso);
            pessoa.setAltura(altura);
            String mensagem = pessoaDAO.create(pessoa);
            return mensagem;
        }catch (MinhaException e){
            throw e;
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

        throw new MinhaException("Usuário não cadastrado!");
    }

    public String updateUsuario(double peso , double altura, int idAtualizacao){

        pessoa.setPeso(peso);
        pessoa.setAltura(altura);
        try{
            String mensagem = pessoaDAO.update(pessoa,idAtualizacao);
            return mensagem;
        }catch(MinhaException e){
            throw e;
        }


    }

    public String deleteUsuario(int id){
        try{
            String mensagem = pessoaDAO.delete(id);
            return mensagem;
        }catch (MinhaException e){
            throw e;
        }

    }

    public double pegaImcUsuario (Usuario user){
        Calculadora calculadora = new Calculadora();
        double retorno = calculadora.calculaIMC(user.getPeso(),user.getAltura());
        return retorno;
    }


}
