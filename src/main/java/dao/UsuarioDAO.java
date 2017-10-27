package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import db.ConnectionFactory;
import model.MinhaException;

public class UsuarioDAO{

    public void create(Usuario u){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt  = con.prepareStatement("insert into usuario (nome, peso, altura) values (?,?,?)");
            stmt.setString(1,u.getNome());
            stmt.setDouble(2,u.getPeso());
            stmt.setDouble(3,u.getAltura());
            stmt.executeUpdate();

            System.out.println("Salvo com Sucesso");
        } catch (SQLException e) {
            throw new MinhaException("Erro, não foi possível cadastrar usuário");
        }finally {
            ConnectionFactory.closeConnection(con,stmt);
        }
    }


   public List<Usuario> read(){

       Connection connection = ConnectionFactory.getConnection();
       PreparedStatement preparedStatement = null;
       ResultSet rs = null;

       List<Usuario> usuarios = new ArrayList<Usuario>();
       try{
           preparedStatement = connection.prepareStatement("select * from usuario");
           rs = preparedStatement.executeQuery();

           while (rs.next()){
               Usuario user = new Usuario();
               user.setId(rs.getInt("id"));
               user.setNome(rs.getString("nome"));
               user.setPeso(rs.getDouble("peso"));
               user.setAltura(rs.getDouble("altura"));
               usuarios.add(user);
           }
       } catch (SQLException e) {
           System.out.println("Erro ao listar usuários: " + e);
       }finally {
           ConnectionFactory.closeConnection(connection,preparedStatement,rs);
       }

       return usuarios;
   }

   public int getIdbyName(String nome) throws MinhaException {


       Connection connection = ConnectionFactory.getConnection();
       PreparedStatement preparedStatement = null;
       ResultSet rs = null;

       try{
           preparedStatement = connection.prepareStatement("select id from usuario where nome = ?");
           preparedStatement.setString(1,nome);
           rs = preparedStatement.executeQuery();
           rs.next();
           int id = rs.getInt("id");
           return id;

       } catch (SQLException e) {
           throw new MinhaException("Erro, não foi possível encontrar um ID pelo nome passado");

       }finally {
           ConnectionFactory.closeConnection(connection,preparedStatement,rs);
       }

   }

   public List<Usuario> getNomeFromUsuario(){

       Connection connection = ConnectionFactory.getConnection();
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       List<Usuario> listaNomesUsuario = new ArrayList<Usuario>();
       try{
           preparedStatement = connection.prepareStatement("SELECT nome FROM usuario");
           resultSet = preparedStatement.executeQuery();

           while (resultSet.next()){
               Usuario user = new Usuario();
               user.setNome(resultSet.getString("nome"));
               listaNomesUsuario.add(user);
           }
           return listaNomesUsuario;

       }catch (SQLException e){
           throw new MinhaException("Erro ao listas nomes");
       }finally {
           ConnectionFactory.closeConnection(connection,preparedStatement,resultSet);
       }

   }

    public void update(Usuario u, int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt  = con.prepareStatement("UPDATE usuario SET peso = ?, altura = ? WHERE id = ?");
            stmt.setDouble(1,u.getPeso());
            stmt.setDouble(2,u.getAltura());
            stmt.setInt(3,id);
            stmt.executeUpdate();

            System.out.println("Atualizado com Sucesso");
        } catch (SQLException e) {
            throw new MinhaException("Erro, não foi possível encontrar o usuário");
        }finally {
            ConnectionFactory.closeConnection(con,stmt);
        }
    }

    public void delete (int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt  = con.prepareStatement("DELETE FROM usuario WHERE id = ?");
            stmt.setInt(1,id);
            stmt.executeUpdate();
            System.out.println("Excluido com Sucesso");
        } catch (SQLException e) {
            throw new MinhaException("Erro, não foi possível deletar usuário");
        }finally {
            ConnectionFactory.closeConnection(con,stmt);
        }
    }

}
