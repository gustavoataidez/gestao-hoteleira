package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HotelDAO extends BaseDAO { // Herda de BaseDAO
    public void mostrarHoteis() {
        String sql = "SELECT * FROM HOTEL";
        PreparedStatement ps = null;

        try {
            Connection conn = getConnection(); // Usa o método da classe base
            ps = conn.prepareStatement(sql); // Usa o método da classe base
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                String codigo = resultado.getString("hot_id");
                String nome = resultado.getString("hot_nome");
                System.out.println("Hotel " + codigo + ": " + nome);
            }

            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            // Lidar com a exceção aqui
        }
    }
}
