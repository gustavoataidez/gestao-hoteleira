package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dto.Quarto;
import dto.QuartoStandard;
import dto.QuartoDeluxe;
import dto.QuartoPresidencial;

public class QuartoDAO extends BaseDAO { 
    public List<Quarto> listarQuartosPorHotel(int intHotel) {
        String sql = "SELECT * FROM quarto WHERE qua_hot = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Quarto> listaQuartos = new ArrayList<>();

        try {
            Connection conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, intHotel);
            rs = ps.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("qua_id");
                int numero = rs.getInt("qua_hot");
                String nome = rs.getString("qua_nome");
                int camas = rs.getInt("qua_camas");
                int valor_dia = rs.getInt("qua_valor_dia");
                String tipo = rs.getString("qua_tipo");
                
                Quarto quarto = null;
                switch (tipo) {
                    case "Standard":
                        quarto = new QuartoStandard(codigo, numero, nome, camas, valor_dia);
                        break;
                    case "Deluxe":
                        boolean temJacuzzi = rs.getBoolean("qua_jacuzzi");
                        quarto = new QuartoDeluxe(codigo, numero, nome, camas, valor_dia, temJacuzzi);
                        break;
                    case "Presidencial":
                        boolean temSalaDeEstar = rs.getBoolean("qua_sala_de_estar");
                        boolean temPiscinaPrivativa = rs.getBoolean("qua_piscina_privativa");
                        quarto = new QuartoPresidencial(codigo, numero, nome, camas, valor_dia, temSalaDeEstar, temPiscinaPrivativa);
                        break;
                }
                
                if (quarto != null) {
                    listaQuartos.add(quarto);
                }
            }
            return listaQuartos;
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            // Lidar com a exceção aqui
        }

        return listaQuartos;
    }
}
