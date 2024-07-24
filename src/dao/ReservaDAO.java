package dao;

import dto.Reserva;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO extends BaseDAO {

    public boolean isQuartoDisponivel(int hotelId, int quartoId, Date dataEntrada, Date dataSaida) {
        String sql = "SELECT COUNT(*) FROM reserva WHERE res_hot = ? AND res_qua = ? " +
                     "AND (? BETWEEN res_data_entrada AND res_data_saida OR ? BETWEEN res_data_entrada AND res_data_saida " +
                     "OR res_data_entrada BETWEEN ? AND ? OR res_data_saida BETWEEN ? AND ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, hotelId);
            ps.setInt(2, quartoId);
            ps.setDate(3, dataEntrada);
            ps.setDate(4, dataSaida);
            ps.setDate(5, dataEntrada);
            ps.setDate(6, dataSaida);
            ps.setDate(7, dataEntrada);
            ps.setDate(8, dataSaida);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0; // Se o resultado for 0, não há reservas conflitantes
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar disponibilidade do quarto: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Método para adicionar reserva, incluindo verificação de disponibilidade
    public void adicionarReserva(Reserva reserva) {
        if (!isQuartoDisponivel(reserva.getHotel(), reserva.getQuarto(), reserva.getRes_data_entrada(), reserva.getRes_data_saida())) {
            System.out.println("Erro: Quarto não disponível para o período solicitado.");
            return;
        }

        String sql = "INSERT INTO reserva (res_hot, res_qua, res_pessoa, res_data_entrada, res_data_saida) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reserva.getHotel());
            ps.setInt(2, reserva.getQuarto());
            ps.setString(3, reserva.getCliente());
            ps.setDate(4, reserva.getRes_data_entrada());
            ps.setDate(5, reserva.getRes_data_saida());

            ps.executeUpdate();
            System.out.println("Reserva adicionada com sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Reserva> listarReservasPorHotel(int hotelId) {
        String sql = "SELECT r.res_id, p.nome AS nome, p.telefone AS telefone, r.res_data_entrada, r.res_data_saida " +
                     "FROM reserva r " +
                     "JOIN pessoa p ON r.res_pessoa = p.usuario " +
                     "WHERE r.res_hot = ?";
        List<Reserva> reservas = new ArrayList<>();
    
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, hotelId);
            ResultSet rs = ps.executeQuery();
    
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(rs.getInt("res_id")); // Agora deve corresponder ao método setId na classe Reserva
                reserva.setCliente(rs.getString("nome"));
                reserva.setTelefone(rs.getString("telefone")); // Agora deve corresponder ao método setTelefone na classe Reserva
                reserva.setRes_data_entrada(rs.getDate("res_data_entrada"));
                reserva.setRes_data_saida(rs.getDate("res_data_saida"));
    
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar reservas: " + e.getMessage());
        }
        return reservas;
    }
    public boolean existeReserva(int hotelId, int quartoId, Date dataEntrada) {
        String sql = "SELECT COUNT(*) FROM reserva WHERE res_hot = ? AND res_qua = ? AND res_data_entrada = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, hotelId);
            ps.setInt(2, quartoId);
            ps.setDate(3, dataEntrada);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar existência de reserva: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
