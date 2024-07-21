package main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.HotelDAO;
import dao.QuartoDAO;
import dto.Hotel;

public class Menu {

    Scanner input = new Scanner(System.in);

    static int escolha;
    static String nomeQuarto;
    static int numQuarto = 1;
    static String resposta;

    HotelDAO hotel = new HotelDAO();

    
    public void menu() {

        do {
            
            System.out.println("*----------------------------------------------------*");
            System.out.println("*          Sistema de Gerenciamento de Hoteis        *");
            System.out.println("*                                                    *");
            System.out.println("* 1. Visualizar todos os hoteis                      *");
            System.out.println("* 2. Listar Quartos                                  *");
            System.out.println("* 3. Adicionar Hotel                                 *");
            System.out.println("* 4. Atualizar Hotel                                 *");
            System.out.println("* 5. Deletar Hotel                                   *");
            System.out.println("* 9. Sair do Programa                                *");
            System.out.println("*----------------------------------------------------*");
            System.out.println("");
            System.out.println("Escolha uma das opções acima. (Ex: Digite '1' para visualizar todos os hoteis)");

            
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
                    
                    System.out.println("Digite o ID do hotel: ");
                    try {
                        if (!input.hasNextInt()) {
                        throw new IllegalArgumentException("ID do hotel deve ser um número inteiro.");
                    }
                    int escolhaHotel = input.nextInt();

                    QuartoDAO quartoDAO = new QuartoDAO();
                    List<dto.Quarto> quartos = quartoDAO.listarQuartosPorHotel(escolhaHotel);

                    if (quartos.isEmpty()) {
                    System.out.println("Nenhum quarto encontrado para o ID de hotel fornecido.");
                    } else {
                    for (dto.Quarto quarto : quartos) {
                        System.out.println(quarto);
                    }
                    System.out.println("\n");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage());
                    input.nextLine(); // Limpa o buffer
                } catch (SQLException e) {
                    System.out.println("Erro ao acessar a base de dados: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
                }
                break;

                    /*
                    System.out.println("Digite o ID do hotel: ");
                    try (Scanner input = new Scanner(System.in)) {
                        int escolhaHotel = input.nextInt();
                        dao.QuartoDAO quartoDAO = new QuartoDAO();
                        List<dto.Quarto> quartos = quartoDAO.listarQuartosPorHotel(escolhaHotel);
                        for (dto.Quarto quarto : quartos) {
                            System.out.println(quarto);
                        }
                        System.out.println("\n");
                    }
                    
                    break;
                     */
                case 3:
                    adicionarHotel();
                    break;
                    
                case 4:
                atualizarHotel();
                break;

                case 5:
                    deletarHotel();
                    break;   

                case 9:
                    System.out.println("Obrigado!");
                    break;
                default:
                    System.out.println("Entrada inválida! Por favor insira uma das opções a seguir: ");
            }
        } while (escolha != 1 && escolha != 2 && escolha != 9);

}


private void adicionarHotel() {
    Hotel novoHotel = new Hotel();
    input.nextLine();
    System.out.println("Digite o nome do hotel: ");
        novoHotel.setNome(input.nextLine());

        System.out.println("Digite o endereco do hotel: ");
        novoHotel.setEndereco(input.nextLine());
        
        System.out.println("Digite o bairro do hotel: ");
        novoHotel.setBairro(input.nextLine());

        System.out.println("Digite a cidade do hotel: ");
        novoHotel.setCidade(input.nextLine());

        System.out.println("Digite o estado do hotel: ");
        novoHotel.setEstado(input.nextLine());

        System.out.println("Digite o site do hotel: ");
        novoHotel.setSite(input.nextLine());

        System.out.println("Digite o telefone do hotel: ");
        novoHotel.setTelefone(input.nextLine());

        System.out.println("Digite as estrelas do hotel: ");
        novoHotel.setEstrelas(input.nextInt());
        input.nextLine();

        System.out.println("Digite a observacao do hotel: ");
        novoHotel.setObservacao(input.nextLine());

        System.out.println("Digite o status do hotel: ");
        novoHotel.setStatus(input.nextLine());

        hotel.adicionarHotel(novoHotel);
}

private void atualizarHotel() {
    System.out.println("Digite o ID do hotel a ser atualizado: ");
    int id = input.nextInt();
    input.nextLine();

    boolean atualizarOutroCampo = true;

    Hotel hotelAtualizado = new Hotel();
    hotelAtualizado.setId(id);

    while (atualizarOutroCampo) {
        System.out.println("*----------------------------------------------------*");
        System.out.println("*            Escolha o campo a ser atualizado        *");
        System.out.println("*                                                    *");
        System.out.println("* 1. Nome                                            *");
        System.out.println("* 2. Endereço                                        *");
        System.out.println("* 3. Bairro                                          *");
        System.out.println("* 4. Cidade                                          *");
        System.out.println("* 5. Estado                                          *");
        System.out.println("* 6. Telefone                                        *");
        System.out.println("* 7. Estrelas                                        *");
        System.out.println("* 8. Observação                                      *");
        System.out.println("* 9. Site                                            *");
        System.out.println("* 10. Status                                         *");
        System.out.println("* 11. Voltar ao menu principal                       *");
        System.out.println("*----------------------------------------------------*");
        System.out.println("");
        System.out.println("Escolha uma das opções acima.");

        System.out.print("Escolha: ");
        while (!input.hasNextInt()) {
            System.out.println("Entrada inválida! Por favor, insira um valor válido.");
            System.out.print("Escolha: ");
            input.next();
        }
        int campoEscolhido = input.nextInt();
        input.nextLine();

        switch (campoEscolhido) {
            case 1:
                System.out.println("Digite o novo nome do hotel: ");
                hotelAtualizado.setNome(input.nextLine());
                break;
            case 2:
                System.out.println("Digite o novo endereco do hotel: ");
                hotelAtualizado.setEndereco(input.nextLine());
                break;
            case 3:
                System.out.println("Digite o novo bairro do hotel: ");
                hotelAtualizado.setBairro(input.nextLine());
                break;
            case 4:
                System.out.println("Digite a nova cidade do hotel: ");
                hotelAtualizado.setCidade(input.nextLine());
                break;
            case 5:
                System.out.println("Digite o novo estado do hotel: ");
                hotelAtualizado.setEstado(input.nextLine());
                break;
            case 6:
                System.out.println("Digite o novo telefone do hotel: ");
                hotelAtualizado.setTelefone(input.nextLine());
                break;
            case 7:
                System.out.println("Digite as novas estrelas do hotel: ");
                hotelAtualizado.setEstrelas(input.nextInt());
                input.nextLine();
                break;
            case 8:
                System.out.println("Digite a nova observação do hotel: ");
                hotelAtualizado.setObservacao(input.nextLine());
                break;
            case 9:
                System.out.println("Digite o novo site do hotel: ");
                hotelAtualizado.setSite(input.nextLine());
                break;
            case 10:
                System.out.println("Digite o novo status do hotel: ");
                hotelAtualizado.setStatus(input.nextLine());
                break;
            case 11:
                atualizarOutroCampo = false;
                break;
            default:
                System.out.println("Entrada inválida! Por favor, escolha uma opção válida.");
        }
        
        if (atualizarOutroCampo) {
            System.out.println("Deseja atualizar outro campo? (s/n)");
            String resposta = input.nextLine();
            if (resposta.equalsIgnoreCase("n")) {
                atualizarOutroCampo = false;
            }
        }
    }

    hotel.atualizarHotel(id, hotelAtualizado);
}

private void deletarHotel() {
    System.out.println("Digite o ID do hotel a ser deletado: ");
    int id = input.nextInt();

    hotel.deletarHotel(id); // Chama o método através da instância hotel

}
}
