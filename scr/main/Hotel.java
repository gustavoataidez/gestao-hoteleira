import java.util.Scanner;

public class Hotel {

    /*Hotel hotel = new Hotel();*/
    Quarto quarto = new Quarto();

    public void menuHotel() {
        MenuHotel menu = new MenuHotel(); // Crie uma instância de MenuHotel
        String[] opcoesHoteis = {"Hotel A", "Hotel B", "Hotel C"};
        int escolhaHotel = menu.menuOpcoes(opcoesHoteis); // Use a instância para chamar o método

        // Exibe o menu de opções do hotel escolhido
        String[] opcoesHotel = {"Ver quartos", "Informações do hotel", "Voltar"};
        int escolhaOpcaoHotel = menu.menuOpcoes(opcoesHotel); // Use a instância para chamar o método

        // Trata a escolha do usuário
        switch (escolhaOpcaoHotel) {
            case 1:
                // Lógica para visualizar os quartos do hotel
                System.out.println("Visualizando quartos do " + opcoesHoteis[escolhaHotel - 1]);
                quarto.listarQuartos();
            case 2:
                // Lógica para exibir informações do hotel
                System.out.println("Informações do " + opcoesHoteis[escolhaHotel - 1]);
                break;
            case 3:
                // Retornar ao menu principal
                menuHotel();
            default:
                System.out.println("Opção inválida!");
        }
    }
}

class MenuHotel {
    Scanner input = new Scanner(System.in);

    public int menuOpcoes(String[] opcoes) {
        int escolha;
        do {
            System.out.println("Escolha uma das opções abaixo:");
            for (int i = 0; i < opcoes.length; i++) {
                System.out.println((i + 1) + ". " + opcoes[i]);
            }
            System.out.print("Opção: ");

            while (!input.hasNextInt()) {
                System.out.println("Entrada inválida! Por favor, insira um número válido.");
                System.out.print("Opção: ");
                input.next();
            }
            escolha = input.nextInt();

            if (escolha < 1 || escolha > opcoes.length) {
                System.out.println("Opção inválida! Por favor, escolha um número dentro do intervalo válido.");
            }
        } while (escolha < 1 || escolha > opcoes.length);

        return escolha;
    }
}
