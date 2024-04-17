import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    Scanner input = new Scanner(System.in);

    Room[] meuHotel = new Room[11];

    static String escolha;
    static String nomeQuarto;
    static int numQuarto = 1;
    static String resposta;

    Queue queueObj = new Queue(); //Criando um objeto da classe Queue

    public void menu() {
        System.out.println("*          Sistema de Gerenciamento de Hoteis       *");
        System.out.println("-                                                    *");
        System.out.println("* 1. Visualizar todos os hoteis                     *");
        System.out.println("* 2. Sair do Programa                                *");
        System.out.println("-                                                    *");
        System.out.println("");
        System.out.println("Escolha uma das opções acima. (Ex: Digite '1' para visualizar todos os hoteis)");

        do {
            System.out.println();
            System.out.print("Escolha: ");
            escolha = input.next();
            String selecao = escolha.toLowerCase(); 

            switch (selecao) {

                case "1":
                    visualizarQuartos();
                    break;
                    
                case "2":
                    System.out.println("Obrigado!");
                    break;

                default:
                    System.out.println("Entrada inválida! Por favor insira uma das opções a seguir: 1 ou 2");
            }
        }
        while (!(escolha.equalsIgnoreCase("1") || escolha.equalsIgnoreCase("2"))); 
    }

    public void initialize() {
        for (int x = 1; x < 11; x++) {
            meuHotel[x] = new Room(); //Criando 10 objetos de ambiente
        }
    }

    public void visualizarQuartos() {
        for (int x = 1; x < 11; x++) {
            if (!meuHotel[x].getName().equals("e")) {
                System.out.println("Hotel N°. " + x + " ocupado por " + meuHotel[x].getName());
            } else {
                System.out.println("Hotel N°. " + x + " está vazio.");
            }

        }
        menu();
    }

}

