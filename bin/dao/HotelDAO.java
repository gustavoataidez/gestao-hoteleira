package dao;

import conexao.Conexao;
import entity.Hotel;
import entity.Quarto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    public HotelDAO() {}

    public void mostrarHoteis() {
        String sql = "SELECT * FROM HOTEL";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                String codigo = resultado.getString("hot_id");
                String nome = resultado.getString("hot_nome");
                System.out.println("Hotel " + codigo + ": " + nome);
            }

            ps.execute();
            ps.close();
        } catch (Exception var6) {
            System.out.println("ERRO: " + var6.getMessage());
        }
    }

    public Hotel buscarHotel(String nomeHotel) {
        String sql = "SELECT * FROM HOTEL WHERE hot_nome = ?";
        PreparedStatement ps = null;
        Hotel hotel = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nomeHotel);
            ResultSet resultado = ps.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("hot_nome");
                String endereco = resultado.getString("hot_endereco");
                String cidade = resultado.getString("hot_cidade");
                String estado = resultado.getString("hot_estado");

                hotel = new Hotel(nome, endereco, cidade, estado);
                hotel.setQuartos(buscarQuartosDoHotel(resultado.getInt("hot_id")));
            }

            ps.close();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        return hotel;
    }

    private List<Quarto> buscarQuartosDoHotel(int hotelId) {
        String sql = "SELECT * FROM QUARTO WHERE hotel_id = ?";
        PreparedStatement ps = null;
        List<Quarto> quartos = new ArrayList<>();

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, hotelId);
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                int codigo = resultado.getInt("qua_id");
                String nome = resultado.getString("qua_nome");
                int camas = resultado.getInt("qua_camas");
                int valor_dia = resultado.getInt("qua_valor_dia");
                boolean reservado = resultado.getBoolean("qua_reservado");

                Quarto quarto = new Quarto(codigo, hotelId, nome, camas, valor_dia, reservado);
                quartos.add(quarto);
            }

            ps.close();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        return quartos;
    }
}
