package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexao.Conexao;

public class HotelDAO {
    public void mostrarHoteis(){
        String sql = "SELECT * FROM HOTEL";

        PreparedStatement ps = null;

        try {
                ps = Conexao.getConexao().prepareStatement(sql);
                ResultSet resultado = ps.executeQuery();
                while(resultado.next()){
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
