import com.mysql.jdbc.integration.c3p0.MysqlConnectionTester;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {



        ConnectionFactory.getConexaoMySQL();
        System.out.println(ConnectionFactory.statusConection());


        Usuario pessoa = new Usuario();
        pessoa.setNome("Gabriel");
        pessoa.setPeso(83.2);
        pessoa.setAltura(1.84);

        Dieta diet = new Dieta(pessoa);
        System.out.println("Resposta: "+diet.decideDieta());

        UsuarioDAO uD = new UsuarioDAO();
        uD.pegaAlimento();




    }
}
