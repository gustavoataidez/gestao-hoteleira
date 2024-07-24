package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;

public class PessoaJuridicaDAO extends PessoaDAO {
    public static void cadastrarPessoaJuridica(dto.PessoaJuridica pj) {
        if (existeUsuario(pj.getUser())) {
            System.out.println("Erro: Usuário já cadastrado.");
            return; // Impede o cadastro se o usuário já existir
        }

    // SQL para inserir na tabela Pessoa
    String sqlPessoa = "INSERT INTO pessoa (usuario, nome, telefone) VALUES (?, ?, ?)";
    // SQL para inserir na tabela pessoa_juridica
    String sqlPessoaJuridica = "INSERT INTO pessoa_juridica (pj_usuario, pj_nome, pj_cnpj, pj_razao) VALUES (?, ?, ?, ?)";

    try (Connection conn = Conexao.getConexao()) {
        // Iniciar a transação
        conn.setAutoCommit(false);

        // Inserir na tabela Pessoa
        try (PreparedStatement psPessoa = conn.prepareStatement(sqlPessoa)) {
            psPessoa.setString(1, pj.getUser());
            psPessoa.setString(2, pj.getNome());
            psPessoa.setString(3, pj.getTel());
            psPessoa.executeUpdate();
        }

        // Inserir na tabela pessoa_juridica
        try (PreparedStatement psPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica)) {
            psPessoaJuridica.setString(1, pj.getUser());
            psPessoaJuridica.setString(2, pj.getNome());
            psPessoaJuridica.setInt(3, pj.getCnpj());
            psPessoaJuridica.setString(4, pj.getRazao());
            psPessoaJuridica.executeUpdate();
        }

        // Confirmar a transação
        conn.commit();
        System.out.println("Empresa cadastrada com sucesso!");

    } catch (Exception e) {
        System.out.println("ERRO: " + e.getMessage());
        e.printStackTrace();
        try (Connection conn = Conexao.getConexao()) {
            conn.rollback();
        } catch (SQLException rollbackEx) {
            System.out.println("Erro ao realizar rollback: " + rollbackEx.getMessage());
        }
    }
}

}
