package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;

public class PessoaFisicaDAO extends PessoaDAO{
     public static void cadastrarPessoaFisica(dto.PessoaFisica pf) {
        if (existeUsuario(pf.getUser())) {
            System.out.println("Erro: Usuário já cadastrado.");
            return; // Impede o cadastro se o usuário já existir
        }

        String sqlPessoa = "INSERT INTO pessoa (usuario, nome, telefone) VALUES (?, ?, ?)";

        String sqlPessoaFisica = "INSERT INTO pessoa_fisica (pf_usuario, pf_nome, pf_cpf, pf_sexo) VALUES (?, ?, ?, ?)";

        try(Connection conn = Conexao.getConexao()){

            conn.setAutoCommit(false);

            // Inserir na tabela Pessoa
            try (PreparedStatement psPessoa = conn.prepareStatement(sqlPessoa)) {
            psPessoa.setString(1, pf.getUser());
            psPessoa.setString(2, pf.getNome());
            psPessoa.setString(3, pf.getTel());
            psPessoa.executeUpdate();
        }

        try (PreparedStatement psPessoaFisica = conn.prepareStatement(sqlPessoaFisica)) {
            psPessoaFisica.setString(1, pf.getUser());
            psPessoaFisica.setString(2, pf.getNome());
            psPessoaFisica.setInt(3, pf.getCpf());
            psPessoaFisica.setString(4, pf.getSexo());
            psPessoaFisica.executeUpdate();
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
