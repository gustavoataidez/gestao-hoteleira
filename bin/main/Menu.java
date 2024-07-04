package main;

import dao.HotelDAO;
import entity.Hotel;
import entity.Quarto;

import java.util.Scanner;

public class Menu {
    Scanner input;
    static int escolha;
    static String nomeQuarto;
    static int numQuarto = 1;
    static String resposta;
    HotelDAO hotelDAO;

    public Menu() {
        this.input = new Scanner(System.in);
        this.hotelDAO = new HotelDAO();
    }

    public void menu() {
        System.out.println("*----------------------------------------------------*");
        System.out.println("*          Sistema de Gerenciamento de Hoteis        *");
        System.out.println("*                                                    *");
        System.out.println("* 1. Visualizar todos os hoteis                      *");
        System.out.println("* 2. Listar Quartos                                  *");
        System.out.println("* 9. Sair do Programa                                *");
        System.out.println("*----------------------------------------------------*");
        System.out.println("");
        System.out.println("Escolha uma das opções acima. (Ex: Digite '1' para visualizar todos os hoteis)");

        do {
            System.out.println();
            System.out.print("Escolha: ");

            while (!this.input.hasNextInt()) {
                System.out.println("Entrada inválida! Por favor, insira um valor válido.");
                System.out.print("Escolha: ");
                this.input.next();
            }

            escolha = this.input.nextInt();
            switch (escolha) {
                case 1:
                    this.hotelDAO.mostrarHoteis();
                    break;
                case 2:
                    listarQuartosDeHotel();
                    break;
                case 9:
                    System.out.println("Obrigado!");
                    break;
                default:
                    System.out.println("Entrada inválida! Por favor insira uma das opções a seguir: 1 ou 2");
            }
        } while (escolha != 1 && escolha != 2 && escolha != 9);
    }

    public void listarQuartosDeHotel() {
        System.out.print("Digite o nome do hotel: ");
        String nomeHotel = input.next();
        Hotel hotel = hotelDAO.buscarHotel(nomeHotel);

        if (hotel != null) {
            System.out.println("===== Quartos do " + hotel.getNome() + " =====");
            for (Quarto quarto : hotel.getQuartos()) {
                System.out.println("Quarto número: " + quarto.getCodigo() + ", Reservado: " + quarto.isReservado());
            }
        } else {
            System.out.println("Hotel não encontrado.");
        }
    }
}
