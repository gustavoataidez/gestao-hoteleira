package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexao.Conexao;

public class PessoaDAO {
    public static boolean existeUsuario(String user) {
    String sql = "SELECT 1 FROM Pessoa WHERE usuario = ?";
    
    try (Connection conn = Conexao.getConexao();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, user);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next(); // Retorna true se encontrar algum registro, false caso contrário
        }
    } catch (Exception e) {
        System.out.println("ERRO: " + e.getMessage());
        e.printStackTrace();
    }
    return false;
}

}
