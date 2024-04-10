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
        System.out.println("*          Sistema de Gerenciamento de Hotel         *");
        System.out.println("-                                                    *");
        System.out.println("* V. Visualizar todos os quartos                     *");
        System.out.println("* Q. Sair do Programa                                *");
        System.out.println("-                                                    *");
        System.out.println("");
        System.out.println("Escolha uma das opções acima. (Ex: Digite 'V' para ver todos os quartos)");

        do {
            System.out.println();
            System.out.print("Escolha: ");
            escolha = input.next();
            String selecao = escolha.toLowerCase(); 

            switch (selecao) {

                case "v":
                    visualizarQuartos();
                    break;
                    
                case "q":
                    System.out.println("Obrigado!");
                    break;

                default:
                    System.out.println("Entrada inválida! Por favor insira uma destas letras: V,Q");
            }
        }
        while (!(escolha.equalsIgnoreCase("v") || escolha.equalsIgnoreCase("q"))); 
    }

    public void initialize() {
        for (int x = 1; x < 11; x++) {
            meuHotel[x] = new Room(); //Criando 10 objetos de ambiente
        }
    }

    public void visualizarQuartos() {
        for (int x = 1; x < 11; x++) {
            if (!meuHotel[x].getName().equals("e")) {
                System.out.println("Quarto N°. " + x + " ocupado por " + meuHotel[x].getName());
            } else {
                System.out.println("Quarto N°. " + x + " está vazio.");
            }

        }
        menu();
    }

}

