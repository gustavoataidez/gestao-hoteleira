import java.util.Scanner;

public class Quarto {

    MenuHotel menu = new MenuHotel();

    // Método para listar os quartos e interagir com o quarto selecionado
    public void listarQuartos() {
        Scanner input = new Scanner(System.in);

        // Assume que há 10 quartos em cada hotel
        final int NUM_QUARTOS = 10;

        // Mostra os quartos disponíveis
        System.out.println("===== Quartos do Hotel =====");
        for (int i = 1; i <= NUM_QUARTOS; i++) {
            System.out.println("Quarto " + i);
        }

        // Solicita ao usuário para escolher um quarto
        System.out.print("Escolha o quarto (1-" + NUM_QUARTOS + ") ou 0 para voltar ao menu: ");
        int escolhaQuarto = input.nextInt();

        if (escolhaQuarto >= 1 && escolhaQuarto <= NUM_QUARTOS) {
            // Quarto selecionado
            System.out.println("Quarto " + escolhaQuarto + " selecionado.");

            // Opções para o quarto selecionado
            String[] opcoesQuarto = {"Reservar", "Ver detalhes do quarto", "Voltar"};
            int escolhaOpcaoQuarto = menu.menuOpcoes(opcoesQuarto);

            // Realiza a ação escolhida pelo usuário
            switch (escolhaOpcaoQuarto) {
                case 1:
                    reservarQuarto(escolhaQuarto);
                    break;
                case 2:
                    verDetalhesQuarto(escolhaQuarto);
                    break;
                case 3:
                    listarQuartos();
                    // Voltar ao menu de quartos
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        } else if (escolhaQuarto == 0) {
            // Voltar ao menu principal
            return;
        } else {
            System.out.println("Opção inválida!");
        }
    }

    // Método para reservar um quarto
    private void reservarQuarto(int numQuarto) {
        System.out.println("Reservando o quarto " + numQuarto);
        // Implemente a lógica para reservar o quarto aqui
    }

    // Método para ver detalhes do quarto
    private void verDetalhesQuarto(int numQuarto) {
        System.out.println("Detalhes do quarto " + numQuarto);
        // Implemente a lógica para ver detalhes do quarto aqui
    }

}

/*public class Room {

    private String mainNome;

    //Declaração do construtor
    public Room() {
        mainNome = "e";
    }

    //Definir método
    public void setName(String aName) {
        mainNome = aName;
    }

    //Obter método
    public String getName() {
        return mainNome;
    }

}
*/