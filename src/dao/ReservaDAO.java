package dao;

import dto.Reserva;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO extends BaseDAO {

    public void adicionarReserva(Reserva reserva) {
        String sql = "INSERT INTO reserva (res_hot, res_qua, res_cli, res_data_entrada, res_data_saida) VALUES (?, ?, ?, ?, ?)";
        
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
        String sql = "SELECT * FROM reserva WHERE res_hot = ?";
        List<Reserva> reservas = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, hotelId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setHotel(rs.getInt("res_hot"));
                reserva.setQuarto(rs.getInt("res_qua"));
                reserva.setCliente(rs.getString("res_cli"));
                reserva.setRes_data_entrada(rs.getDate("res_data_entrada"));
                reserva.setRes_data_saida(rs.getDate("res_data_saida"));

                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar reservas: " + e.getMessage());
        }
        return reservas;
    }
}
