package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import conexao.Conexao;

public class FuncionarioDAO extends BaseDAO{

    public void cadastrarFuncionario(dto.Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (fun_user, fun_nome, fun_tel, fun_cpf, fun_sexo, fun_cargo, fun_salario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = Conexao.getConexao();
        PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, funcionario.getUser());
            ps.setString(2, funcionario.getNome());
            ps.setString(3, funcionario.getTel());
            ps.setInt(4, funcionario.getCpf());
            ps.setString(5, funcionario.getSexo());
            ps.setString(6, funcionario.getCargo());
            ps.setInt(7, funcionario.getSalario());

            ps.executeUpdate();
            System.out.println("Funcionario cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

}
