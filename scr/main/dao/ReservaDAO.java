package dao;

import java.sql.PreparedStatement;
import java.util.Scanner;
import conexao.Conexao;


public class ReservaDAO {
    Scanner input = new Scanner(System.in);

    public void cadastrarReserva(){
        try {
        System.out.println("Digite o ID do hotel: ");
        int res_hot = input.nextInt();

        System.out.println("Digite o ID do quarto: ");
        int res_qua = input.nextInt();

        System.out.println("Digite o usuário do cliente: ");
        String res_cli = input.next();

        System.out.println("Digite a data de entrada (formato: YYYY-MM-DD): ");
        String res_data_entrada = input.next();

        System.out.println("Digite a data de saída (formato: YYYY-MM-DD): ");
        String res_data_saida = input.next();

        String sql = "INSERT INTO reserva (res_hot, res_qua, res_cli, res_data_entrada, res_data_saida) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = null;
            ps = Conexao.getConexao().prepareStatement(sql);

            ps.setInt(1, res_hot);
            ps.setInt(2, res_qua);
            ps.setString(3, res_cli);
            ps.setDate(4, java.sql.Date.valueOf(res_data_entrada));
            ps.setDate(5, java.sql.Date.valueOf(res_data_saida));

            // Executar a declaração SQL
            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Inserção bem-sucedida na tabela reserva.");
            } else {
                System.out.println("Falha ao inserir na tabela reserva.");
            }

        } catch (Exception e) {
            System.out.println("ERRO COM O BANCO: " + e.getMessage());
            // Lidar com a exceção aqui
        }
        System.out.println(" ");
        
    } catch (Exception e) {
        System.out.println("Erro ao ler entrada do usuário: " + e.getMessage());
    }
    }
}