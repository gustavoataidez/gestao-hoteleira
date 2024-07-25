package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import conexao.Conexao;

public class FuncionarioDAO extends BaseDAO{

    public void cadastrarFuncionario(dto.Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (fun_usuario, fun_nome, fun_cpf, fun_sexo, fun_cargo, fun_salario) VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection conn = Conexao.getConexao();
        PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, funcionario.getUser());
            ps.setString(2, funcionario.getNome());
            ps.setInt(3, funcionario.getCpf());
            ps.setString(4, funcionario.getSexo());
            ps.setString(5, funcionario.getCargo());
            ps.setInt(6, funcionario.getSalario());

            ps.executeUpdate();
            System.out.println("Funcionario cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

}
