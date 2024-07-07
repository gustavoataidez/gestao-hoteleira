package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dto.Quarto;

public class QuartoDAO extends BaseDAO { // Herda de BaseDAO

    // Método para listar os quartos por ID do hotel
    public List<Quarto> listarQuartosPorHotel(int intHotel) {
        String sql = "SELECT * FROM quarto WHERE qua_hot = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Quarto> listaQuartos = new ArrayList<>();

        try {
            Connection conn = getConnection(); // Usa o método da classe base
            ps = conn.prepareStatement(sql); // Usa o método da classe base
            ps.setInt(1, intHotel);
            rs = ps.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("qua_id");
                int numero = rs.getInt("qua_hot");
                String nome = rs.getString("qua_nome");
                int camas = rs.getInt("qua_camas");
                int valor_dia = rs.getInt("qua_valor_dia");
                Quarto quarto = new Quarto(codigo, numero, nome, camas, valor_dia);
                listaQuartos.add(quarto);
            }
            return listaQuartos;
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            // Lidar com a exceção aqui
        }

        listaQuartos.toString();
        return null;
    }
}
