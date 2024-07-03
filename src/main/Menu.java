package main;

import java.util.Scanner;

import dao.HotelDAO;
import dto.QuartoDTO;

public class Menu {

    Scanner input = new Scanner(System.in);

    static int escolha;
    static String nomeQuarto;
    static int numQuarto = 1;
    static String resposta;

    HotelDAO hotel = new HotelDAO();
    QuartoDTO quartoDTO = new QuartoDTO();

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
        while (!input.hasNextInt()) { // Verifica se a entrada do usuário não é um número inteiro
            System.out.println("Entrada inválida! Por favor, insira um valor válido.");
            System.out.print("Escolha: ");
            input.next(); // Descarta a entrada inválida
        }
        escolha = input.nextInt();

        switch (escolha) {
            case 1:
                /*visualizarQuartos();*/
                hotel.mostrarHoteis();
                break;
            case 2:
                quartoDTO.mostrarQuartos();
                break;
            case 9:
                System.out.println("Obrigado!");
                break;
            default:
                System.out.println("Entrada inválida! Por favor insira uma das opções a seguir: 1 ou 2");
        }
    } while (escolha != 1 && escolha != 2 && escolha != 9);

}

}

