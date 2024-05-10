package conexao; 

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection getConexao(){
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/hoteis",
                "postgres",
                "1234"
            );
            System.out.println("Conectado com o banco.");
            return conn;

        } catch (Exception e) {
            System.out.println("Erro ao se conectar com o banco: " + e.getMessage());
            return null;
        }
         
    }
    
}
