import com.sun.istack.internal.logging.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

public class UsuarioDAO extends javax.swing.JFrame{



    public void pegaAlimento () throws SQLException {
        Connection con = new ConnectionFactory().getConexaoMySQL();


        try{
            PreparedStatement stmt = con.prepareStatement("select * from almoco");
            ResultSet rs = stmt.executeQuery();
            rs.first();
            
        }catch(SQLException ex){
            System.out.println("RUIM no userDAO");
        }
            /*
           public Cad_Cidades() {
            initComponents();
            Conexao con_cidade = new Conexao();
            try {
                PreparedStatement stmt = con_cidade.connection.prepareStatement("select * from cidades");
                ResultSet rs = stmt.executeQuery();
                rs.first();
                tf_codigo.setText(con_cidade.ResultSet.getString("codigo"));
            } catch (Exception e) {
            }
        }
        */

    }
}
