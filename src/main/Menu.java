package main;

import java.sql.Date;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dao.HotelDAO;
import dao.QuartoDAO;
import dao.ReservaDAO;
import dto.Hotel;
import dto.Reserva;

public class Menu {

    Scanner input = new Scanner(System.in);
    ReservaDAO reservaDAO = new ReservaDAO();

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
            System.out.println("* 6. Adicionar Reserva                               *");
            System.out.println("* 7. Listar Reservas                                 *");
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

                case 3:
                    adicionarHotel();
                    break;
                    
                case 4:
                atualizarHotel();
                break;

                case 5:
                    deletarHotel();
                    break;   

                case 6:
                    adicionarReserva();
                    break;
                    
                case 7:
                    listarReservas();
                    break;
                case 9:
                    System.out.println("Obrigado!");
                    break;
                default:
                    System.out.println("Entrada inválida! Por favor insira uma das opções a seguir: ");
            }
        } while (escolha != 1 && escolha != 2 && escolha != 9);

}


private void adicionarReserva() {
    Reserva reserva = new Reserva();
    input.nextLine(); // Limpa o buffer do scanner

    System.out.println("Digite o ID do hotel: ");
    reserva.setHotel(input.nextInt());

    System.out.println("Digite o ID do quarto: ");
    reserva.setQuarto(input.nextInt());

    input.nextLine(); // Limpa o buffer do scanner
    System.out.println("Digite o nome do cliente: ");
    reserva.setCliente(input.nextLine());

    System.out.println("Digite a data de entrada (AAAA-MM-DD): ");
    reserva.setRes_data_entrada(Date.valueOf(input.nextLine()));

    System.out.println("Digite a data de saída (AAAA-MM-DD): ");
    reserva.setRes_data_saida(Date.valueOf(input.nextLine()));

    reservaDAO.adicionarReserva(reserva);
}

private void listarReservas() {
System.out.println("Digite o ID do hotel: ");
int hotelId = input.nextInt();

 List<Reserva> reservas = reservaDAO.listarReservasPorHotel(hotelId);

if (reservas.isEmpty()) {
    System.out.println("Nenhuma reserva encontrada para o ID de hotel fornecido.");
} else {
    for (Reserva reserva : reservas) {
        System.out.println("Reserva [ID: " + " | Cliente: " + reserva.getCliente() + " | Telefone: "  + " | Entrada: " + reserva.getRes_data_entrada() + " | Saída: " + reserva.getRes_data_saida() + "]");
    }
}
}

private void adicionarHotel() {
    Hotel novoHotel = new Hotel();
    input.nextLine(); // Limpa o buffer do scanner

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

    // Entrada de estrelas com verificação de erro
    boolean entradaValida = false;
    while (!entradaValida) {
        System.out.println("Digite as estrelas do hotel: ");
        if (input.hasNextInt()) {
            novoHotel.setEstrelas(input.nextInt());
            entradaValida = true;
        } else {
            System.out.println("Entrada inválida! Por favor, insira um valor numérico para as estrelas.");
            input.next(); // Descarta a entrada inválida
        }
    }
    input.nextLine(); // Limpa o buffer do scanner

    System.out.println("Digite a observacao do hotel: ");
    novoHotel.setObservacao(input.nextLine());

    System.out.println("Digite o status do hotel: ");
    novoHotel.setStatus(input.nextLine());

    hotel.adicionarHotel(novoHotel); // Chama o método do DAO para adicionar o hotel ao banco de dados
}

private void atualizarHotel() {
    
    System.out.println("Digite o ID do hotel a ser atualizado: ");
    int id;
    try {
        id = input.nextInt();
        input.nextLine();
    } catch (InputMismatchException e) {
        System.out.println("ID inválido! Por favor, insira um número.");
        input.nextLine();  // Limpa o buffer
        return;
    }

    HotelDAO hotelDAO = new HotelDAO();

    // Verifica se o hotel com o ID fornecido existe
    if (!hotelDAO.hotelExiste(id)) {
        System.out.println("Hotel com ID " + id + " não encontrado.");
        return;
    }

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
                String nome = input.nextLine();
                if (!nome.isEmpty()) {
                    hotelAtualizado.setNome(nome);
                    System.out.println("Nome atualizado com sucesso!");
                }
                break;
            case 2:
                System.out.println("Digite o novo endereco do hotel: ");
                String endereco = input.nextLine();
                if (!endereco.isEmpty()) {
                    hotelAtualizado.setEndereco(endereco);
                    System.out.println("Endereço atualizado com sucesso!");
                }
                break;
            case 3:
                System.out.println("Digite o novo bairro do hotel: ");
                String bairro = input.nextLine();
                if (!bairro.isEmpty()) {
                    hotelAtualizado.setBairro(bairro);
                    System.out.println("Bairro atualizado com sucesso!");
                }
                break;
            case 4:
                System.out.println("Digite a nova cidade do hotel: ");
                String cidade = input.nextLine();
                if (!cidade.isEmpty()) {
                    hotelAtualizado.setCidade(cidade);
                    System.out.println("Cidade atualizada com sucesso!");
                }
                break;
            case 5:
                System.out.println("Digite o novo estado do hotel: ");
                String estado = input.nextLine();
                if (!estado.isEmpty()) {
                    hotelAtualizado.setEstado(estado);
                    System.out.println("Estado atualizado com sucesso!");
                }
                break;
            case 6:
                System.out.println("Digite o novo telefone do hotel: ");
                String telefone = input.nextLine();
                if (!telefone.isEmpty()) {
                    hotelAtualizado.setTelefone(telefone);
                    System.out.println("Telefone atualizado com sucesso!");
                }
                break;
            case 7:
                try {
                    System.out.println("Digite as novas estrelas do hotel: ");
                    int estrelas = input.nextInt();
                    if (estrelas >= 1 && estrelas <= 5) {
                        hotelAtualizado.setEstrelas(estrelas);
                        System.out.println("Estrelas atualizadas com sucesso!");
                    } else {
                        System.out.println("Número de estrelas inválido! Por favor, insira um valor entre 1 e 5.");
                    }
                    input.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Por favor, insira um número.");
                    input.nextLine();  // Limpa o buffer
                }
                break;
            case 8:
                System.out.println("Digite a nova observação do hotel: ");
                String observacao = input.nextLine();
                if (!observacao.isEmpty()) {
                    hotelAtualizado.setObservacao(observacao);
                    System.out.println("Observação atualizada com sucesso!");
                }
                break;
            case 9:
                System.out.println("Digite o novo site do hotel: ");
                String site = input.nextLine();
                if (!site.isEmpty()) {
                    hotelAtualizado.setSite(site);
                    System.out.println("Site atualizado com sucesso!");
                }
                break;
            case 10:
                System.out.println("Digite o novo status do hotel: ");
                String status = input.nextLine();
                if (!status.isEmpty()) {
                    hotelAtualizado.setStatus(status);
                    System.out.println("Status atualizado com sucesso!");
                }
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

    try {
        hotel.atualizarHotel(id, hotelAtualizado);
        System.out.println("Hotel atualizado com sucesso!");
    } catch (Exception e) {
        System.out.println("Erro ao atualizar o hotel: " + e.getMessage());
    }
}

private void deletarHotel() {
    System.out.println("Digite o ID do hotel a ser deletado: ");
    int id;
    try {
        id = input.nextInt();
        input.nextLine(); // Limpa o buffer
    } catch (InputMismatchException e) {
        System.out.println("ID inválido! Por favor, insira um número.");
        input.nextLine(); // Limpa o buffer
        return;
    }

    HotelDAO hotelDAO = new HotelDAO();  // Instancia a classe HotelDAO

    if (!hotelDAO.hotelExiste(id)) {
        System.out.println("Hotel com ID " + id + " não encontrado.");
        return;
    }

    hotelDAO.deletarHotel(id); // Chama o método através da instância hotel
}

}
