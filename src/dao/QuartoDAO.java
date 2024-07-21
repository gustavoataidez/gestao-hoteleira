package dao;


import dto.Quarto;
import dto.QuartoDeluxe;
import dto.QuartoPresidencial;
import dto.QuartoStandard;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class QuartoDAO extends BaseDAO { 
    public List<Quarto> listarQuartosPorHotel(int intHotel) throws SQLException {
        String sql = "SELECT * FROM quarto WHERE qua_hot = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        List<Quarto> listaQuartos = new ArrayList<>();

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
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
        } catch (SQLException e) {
            System.out.println("Erro ao listar quartos: " + e.getMessage());
            throw e; // Propaga a exceção para ser tratada no nível superior
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar ResultSet: " + e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar PreparedStatement: " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar Connection: " + e.getMessage());
                }
            }
        }

        return listaQuartos;
    }
}

/* 
public class QuartoDAO extends BaseDAO { 
    public List<Quarto> listarQuartosPorHotel(int intHotel) {
        String sql = "SELECT * FROM quarto WHERE qua_hot = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Quarto> listaQuartos = new ArrayList<>();

        try {
            Connection conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, intHotel);
            rs = ps.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("qua_id");
                int numero = rs.getInt("qua_hot");
                String nome = rs.getString("qua_nome");
                int camas = rs.getInt("qua_camas");
                int valor_dia = rs.getInt("qua_valor_dia");
                
                dto.Quarto quarto = new Quarto(codigo, numero, nome, camas,valor_dia);
                listaQuartos.add(quarto);
            }
            return listaQuartos;
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            // Lidar com a exceção aqui
        }

        return listaQuartos;
    }
}
*/
