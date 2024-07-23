package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import conexao.Conexao;

public class PessoaFisicaDAO {
     public static void cadastrarPessoaFisica(dto.PessoaFisica pf) {
        String sql = "INSERT INTO pessoa_fisica (pf_usuario, pf_nome, pf_cpf, pf_sexo) VALUES (?, ?, ?, ?)";

        try(Connection conn = Conexao.getConexao();
        PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pf.getUser());
            ps.setString(2, pf.getNome());
            ps.setInt(3, pf.getCpf());
            ps.setString(4, pf.getSexo());

            ps.executeUpdate();
            System.out.println("Pessoa cadastrada com sucesso!");

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }
}
