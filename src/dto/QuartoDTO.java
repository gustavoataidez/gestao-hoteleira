package dto;

import java.util.List;
import java.util.Scanner;

import dao.QuartoDAO;

public class QuartoDTO {
    
    public void mostrarQuartos(){

        System.out.println("Digite o ID do hotel: ");
        try (Scanner input = new Scanner(System.in)) {
            int escolhaHotel = input.nextInt();
            dao.QuartoDAO quartoDAO = new QuartoDAO();
            List<entity.Quarto> quartos = quartoDAO.listarQuartosPorHotel(escolhaHotel);
            for (entity.Quarto quarto : quartos) {
                System.out.println(quarto);
            }
        }
    }
    
}
