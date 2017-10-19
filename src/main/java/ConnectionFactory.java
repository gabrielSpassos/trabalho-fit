//Classes necessárias para uso de Banco de dados //
import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

//Início da classe de conexão//

public class ConnectionFactory {

    public static String status = "Não conectou...";

    public ConnectionFactory() {

    }

    //Método de Conexão//

    public static java.sql.Connection getConexaoMySQL() {

        Connection connection = null;
        try {

            // Carregando o JDBC Driver padrão

            String driverName = "com.mysql.jdbc.Driver";

            Class.forName(driverName);
            // Configurando a nossa conexão com um banco de dados//

            String serverName = "localhost";
            String mydatabase = "appfit";
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "root";
            String password = "Inter#2104";

            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {

                status = ("STATUS--->Conectado com sucesso!");

            } else {

                status = ("STATUS--->Não foi possivel realizar conexão");

            }
            return connection;

        } catch (ClassNotFoundException e) {  //Driver não encontrado

            System.out.println("O driver expecificado nao foi encontrado.");

            return null;

        } catch (SQLException e) {

            System.out.println("Nao foi possivel conectar ao Banco de Dados.");

            return null;

        }






    }

    public static String statusConection() {

        return status;

    }

    public static boolean FecharConexao() {

        try {

            ConnectionFactory.getConexaoMySQL().close();

            return true;

        } catch (SQLException e) {

            return false;
        }
    }

    public static java.sql.Connection ReiniciarConexao() {

        FecharConexao();
        return ConnectionFactory.getConexaoMySQL();
    }
}
