package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import dto.Hotel;

public class HotelDAO extends BaseDAO {
    public void mostrarHoteis() {
        String sql = "SELECT * FROM HOTEL";
        PreparedStatement ps = null;

        try {
            Connection conn = getConnection();
            ps = conn.prepareStatement(sql);
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

        }
    }


    public Hotel obterHotelPorId(int id) {
        Hotel hotel = null;
        String query = "SELECT * FROM Hotel WHERE hot_id = ?";
    
        try (Connection connection = Conexao.getConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                hotel = new Hotel();
                hotel.setId(resultSet.getInt("hot_id"));
                hotel.setNome(resultSet.getString("hot_nome"));
                hotel.setEndereco(resultSet.getString("hot_end"));
                hotel.setBairro(resultSet.getString("hot_bairro"));
                hotel.setCidade(resultSet.getString("hot_cid"));
                hotel.setEstado(resultSet.getString("hot_estado"));
                hotel.setTelefone(resultSet.getString("hot_tel"));
                hotel.setEstrelas(resultSet.getInt("hot_estrelas"));
                hotel.setObservacao(resultSet.getString("hot_obs"));
                hotel.setSite(resultSet.getString("hot_site"));
                hotel.setStatus(resultSet.getString("hot_status"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter hotel: " + e.getMessage());
        }
    
        return hotel;
    }
     
    public void adicionarHotel(Hotel hotel) {
        String sql = "INSERT INTO HOTEL (hot_nome, hot_end, hot_bairro, hot_cid, hot_estado, hot_tel, hot_estrelas, hot_obs, hot_site, hot_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, hotel.getNome());
            ps.setString(2, hotel.getEndereco());
            ps.setString(3, hotel.getBairro());
            ps.setString(4, hotel.getCidade());
            ps.setString(5, hotel.getEstado());
            ps.setString(6, hotel.getTelefone());
            ps.setInt(7, hotel.getEstrelas());
            ps.setString(8, hotel.getObservacao());
            ps.setString(9, hotel.getSite());
            ps.setString(10, hotel.getStatus());


            ps.executeUpdate();
            System.out.println("Hotel adicionado com sucesso!");

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }


    public boolean hotelExiste(int id) {
        String sql = "SELECT COUNT(*) FROM HOTEL WHERE hot_id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar a existência do hotel: " + e.getMessage());
        }
        return false;
    }
    

    public void atualizarHotel(int id, Hotel hotel) {
        StringBuilder sql = new StringBuilder("UPDATE HOTEL SET ");
        List<Object> params = new ArrayList<>();
    
        if (hotel.getNome() != null && !hotel.getNome().isEmpty()) {
            sql.append("hot_nome = ?, ");
            params.add(hotel.getNome());
        }
        if (hotel.getEndereco() != null && !hotel.getEndereco().isEmpty()) {
            sql.append("hot_end = ?, ");
            params.add(hotel.getEndereco());
        }
        if (hotel.getBairro() != null && !hotel.getBairro().isEmpty()) {
            sql.append("hot_bairro = ?, ");
            params.add(hotel.getBairro());
        }
        if (hotel.getCidade() != null && !hotel.getCidade().isEmpty()) {
            sql.append("hot_cid = ?, ");
            params.add(hotel.getCidade());
        }
        if (hotel.getEstado() != null && !hotel.getEstado().isEmpty()) {
            sql.append("hot_estado = ?, ");
            params.add(hotel.getEstado());
        }
        if (hotel.getTelefone() != null && !hotel.getTelefone().isEmpty()) {
            sql.append("hot_tel = ?, ");
            params.add(hotel.getTelefone());
        }
        if (hotel.getEstrelas() >= 1 && hotel.getEstrelas() <= 5) {
            sql.append("hot_estrelas = ?, ");
            params.add(hotel.getEstrelas());
        }
        if (hotel.getObservacao() != null && !hotel.getObservacao().isEmpty()) {
            sql.append("hot_obs = ?, ");
            params.add(hotel.getObservacao());
        }
        if (hotel.getSite() != null && !hotel.getSite().isEmpty()) {
            sql.append("hot_site = ?, ");
            params.add(hotel.getSite());
        }
        if (hotel.getStatus() != null && !hotel.getStatus().isEmpty()) {
            sql.append("hot_status = ?, ");
            params.add(hotel.getStatus());
        }
    
        // Verifica se há parâmetros para atualizar
        if (params.isEmpty()) {
            System.out.println("Nenhum campo para atualizar.");
            return;
        }
    
        // Remove a última vírgula e espaço
        sql.setLength(sql.length() - 2);
    
        sql.append(" WHERE hot_id = ?");
        params.add(id);
    
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
    
            // Define os parâmetros no PreparedStatement
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
    
            // Executa a atualização
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Hotel atualizado com sucesso!");
            } else {
                System.out.println("Nenhum hotel foi atualizado. Verifique o ID fornecido.");
            }
    
        } catch (SQLException e) {
            System.out.println("ERRO ao atualizar hotel: " + e.getMessage());
        }
    }
    
    
    public void deletarHotel(int id) {
        if (!hotelExiste(id)) {
            System.out.println("Hotel com ID " + id + " não encontrado.");
            return;
        }
    
        String sql = "DELETE FROM HOTEL WHERE hot_id = ?";
    
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
    
            ps.setInt(1, id);
    
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Hotel deletado com sucesso!");
            } else {
                System.out.println("Nenhum hotel foi deletado. Verifique o ID fornecido.");
            }
    
        } catch (SQLException e) {
            System.out.println("ERRO ao deletar hotel: " + e.getMessage());
        }
    }
    
}
