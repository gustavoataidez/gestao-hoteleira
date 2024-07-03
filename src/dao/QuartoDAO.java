package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import conexao.Conexao;

import entity.Quarto;

public class QuartoDAO {
    

    // Método para listar os quartos por ID do hotel
    public List<entity.Quarto> listarQuartosPorHotel(int intHotel) {
        String sql = "SELECT * FROM quarto WHERE qua_hot = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<entity.Quarto> listaQuartos = new ArrayList<entity.Quarto>();

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, intHotel);
            rs = ps.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("qua_id");
                int numero = rs.getInt("qua_hot");
                String nome = rs.getString("qua_nome");
                int camas = rs.getInt("qua_camas");
                int valor_dia = rs.getInt("qua_valor_dia");
                entity.Quarto quarto = new Quarto(codigo, numero, nome, camas,valor_dia);
                listaQuartos.add(quarto);
            }
            return listaQuartos;
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            // Lidar com a exceção aqui
        }
        System.out.println(" ");

        listaQuartos.toString();
        return null;
    }
}
