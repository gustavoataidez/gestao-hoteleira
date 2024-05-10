import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.HotelDAO;
import dao.ReservaDAO;

public class Menu {

    Scanner input = new Scanner(System.in);

    Quarto[] meuHotel = new Quarto[11];

    static int escolha;
    static String nomeQuarto;
    static int numQuarto = 1;
    static String resposta;

    Queue queueObj = new Queue(); //Criando um objeto da classe Queue

    Hotel hotel = new Hotel();
    

    public void menu() {
        System.out.println("=====================================================");
        System.out.println("|          Sistema de Gerenciamento de Hotel        |");
        System.out.println("|---------------------------------------------------|");
        System.out.println("| 1. Visualizar todos os hoteis                     |");
        System.out.println("| 2. Fazer Reserva                                  |");
        System.out.println("| 8. Sair do Programa                               |");
        System.out.println("=====================================================");
        System.out.println(" ");
        
    do {
        System.out.print("Escolha: ");
        while (!input.hasNextInt()) { // Verifica se a entrada do usuário não é um número inteiro
            System.out.println("Entrada inválida! Por favor, insira um valor válido.");
            System.out.print("Escolha: ");
            input.next(); // Descarta a entrada inválida
        }
        escolha = input.nextInt();

        switch (escolha) {
            case 1:
                new HotelDAO().mostrarHoteis();
                menu(); //Método de menu de chamada */;
                return;
            case 2:
                new ReservaDAO().cadastrarReserva();
                menu();
                return;
            case 8:
                System.out.println("Obrigado!");
                break;
            default:
                System.out.println("Entrada inválida! Por favor insira uma das opções a seguir: 1 ou 2");
        }
    } while (escolha != 1 && escolha != 2 && escolha != 8);

}


    public void initialize() {
        for (int x = 1; x < 11; x++) {
            meuHotel[x] = new Quarto(); //Criando 10 objetos de ambiente
        }
    }

    /*public void visualizarQuartos() {
        for (int x = 1; x < 11; x++) {
            if (!meuHotel[x].getName().equals("e")) {
                System.out.println("Hotel N°. " + x + " ocupado por " + meuHotel[x].getName());
            } else {
                System.out.println("Hotel N°. " + x + " está vazio.");
            }

        }
        menu();
    }*/

}

