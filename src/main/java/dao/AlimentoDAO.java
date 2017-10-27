package dao;

import model.Alimento;
import model.MinhaException;
import db.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlimentoDAO {

    public List<Alimento> read(double caloriasLimite, int tipoDeRefeicao){

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        List<Alimento> alimentos = new ArrayList<Alimento>();

        try{
            preparedStatement = connection.prepareStatement("SELECT alimentos.nome, alimentos.calorias FROM refeicao JOIN alimentos ON refeicao.id_Alimento = alimentos.id WHERE calorias<? and refeicao.id_horario = ?;");
            preparedStatement.setDouble(1,caloriasLimite);
            preparedStatement.setInt(2,tipoDeRefeicao);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                Alimento food = new Alimento();
                food.setNome(rs.getString("alimentos.nome"));
                food.setCalorias(rs.getDouble("alimentos.calorias"));
                alimentos.add(food);
            }

        }catch (SQLException e) {
            throw new MinhaException("Erro, não foi possível listar os alimentos pelo nome passado");
        }finally {
            ConnectionFactory.closeConnection(connection,preparedStatement,rs);
        }

        return alimentos;
    }

    public double getCaloriasByNome(String nome){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try{
            preparedStatement = connection.prepareStatement("select calorias from alimentos where nome = ?");
            preparedStatement.setString(1,nome);
            rs = preparedStatement.executeQuery();
            rs.next();
            double calorias = rs.getDouble("calorias");
            return calorias;

        } catch (SQLException e) {
            throw new MinhaException("Erro ao pegar as calorias pelo Nome");

        }finally {
            ConnectionFactory.closeConnection(connection,preparedStatement,rs);
        }
    }
}

