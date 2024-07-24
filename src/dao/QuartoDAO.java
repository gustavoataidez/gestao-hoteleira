package dao;

import dto.Quarto;
import dto.QuartoDeluxe;
import dto.QuartoPresidencial;
import dto.QuartoStandard;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;

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
                int id = rs.getInt("qua_id");
                int numero = rs.getInt("qua_hot");
                String nome = rs.getString("qua_nome");
                int camas = rs.getInt("qua_camas");
                int valor_dia = rs.getInt("qua_valor_dia");
                
                boolean jacuzzi = false; // Valor padrão
                boolean salaDeEstar = false; // Valor padrão
                
                dto.Quarto quarto = new Quarto(numero, nome, camas,valor_dia, jacuzzi, salaDeEstar);
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
    public void adicionarQuarto(Quarto quarto) {
        String sql = "INSERT INTO quarto (qua_hot, qua_nome, qua_camas, qua_valor_dia) VALUES (?, ?, ?, ?)";

        try (Connection connection = Conexao.getConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ///preparedStatement.setInt(1, quarto.getId());
            preparedStatement.setInt(1, quarto.getHotel());
            preparedStatement.setString(2, quarto.getNome());
            preparedStatement.setInt(3, quarto.getCamas());
            preparedStatement.setInt(4, quarto.getValor_dia());

            preparedStatement.executeUpdate();

           
        } catch (Exception e) {
            System.out.println("Erro ao adicionar quarto: " + e.getMessage());
        }
    }
}

