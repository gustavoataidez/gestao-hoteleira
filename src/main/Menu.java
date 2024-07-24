package main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import conexao.Conexao;
import dao.FuncionarioDAO;
import dao.HotelDAO;
import dao.PessoaFisicaDAO;
import dao.PessoaJuridicaDAO;
import dao.QuartoDAO;
import dao.ReservaDAO;
import dto.Funcionario;
import dto.Hotel;
import dto.PessoaFisica;
import dto.PessoaJuridica;
import dto.Quarto;
import dto.QuartoDeluxe;
import dto.QuartoPresidencial;
import dto.QuartoStandard;
import dto.Reserva;

public class Menu {

    Scanner input = new Scanner(System.in);
    ReservaDAO reservaDAO = new ReservaDAO();
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

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
            System.out.println("* 1. Hoteis                                          *");
            System.out.println("* 2. Quartos                                         *");
            System.out.println("* 3. Reserva                                         *");
            System.out.println("* 4. Pessoas                                         *");
            System.out.println("* 5. Sair do Programa                                *");
            System.out.println("*----------------------------------------------------*");
            System.out.println("");
            System.out.println("Escolha uma das opções acima.");
    
            System.out.println();
            System.out.print("Escolha: ");
    
            while (!input.hasNextInt()) { 
                System.out.println("Entrada inválida! Por favor, insira um valor válido.");
                System.out.print("Escolha: ");
                input.next(); 
            }
            escolha = input.nextInt();
    
            switch (escolha) {
                case 1:
                    menuHoteis();
                    break;
                case 2:
                    menuQuartos();
                    break;
                case 3:
                    menuReserva();
                    break;
                case 4:
                    menuClientes();
                    break;
                case 5:
                    System.out.println("Obrigado!");
                    break;
                default:
                    System.out.println("Entrada inválida! Por favor insira uma das opções a seguir: ");
            }
            
         } while (escolha != 5);
        
    }


private void cadastrarCliente() {
    String siglaPessoa = "";
    boolean entradaValida = false;

    System.out.println("Digite 'PF' se pessoa física, ou 'PJ' se empresa: ");

    while (!entradaValida) {
        siglaPessoa = input.nextLine().trim(); 

        if (siglaPessoa.equalsIgnoreCase("PF")) {
            adicionarPessoaFisica();
            entradaValida = true;
        } else if (siglaPessoa.equalsIgnoreCase("PJ")) {
            adicionarPessoaJuridica();
            entradaValida = true;
        } else if (!siglaPessoa.isEmpty()) {
            System.out.println("Entrada inválida! Digite apenas 'PF' se pessoa física, ou 'PJ' se empresa.");
        }
    }
}


private void adicionarPessoaFisica(){
    PessoaFisica pf = new PessoaFisica();

    while (true) {
        System.out.println("User: ");
        String user = input.nextLine().trim();

        // Verificar se o usuário já existe na tabela Pessoa
        if (!PessoaJuridicaDAO.existeUsuario(user)) {
            pf.setUser(user); // Define o usuário válido no objeto PessoaJuridica
            break; // Sai do loop quando um usuário válido for fornecido
        } else {
            System.out.println("Erro: Esse usuário já existe. Tente novamente.");
        }
    }

    System.out.println("User: ");
    pf.setUser(input.nextLine());

    System.out.println("Nome: ");
    pf.setNome(input.nextLine());

    System.out.println("Telefone: ");
    pf.setTel(input.nextLine());

    System.out.println("Sexo (S/M/O): ");
    pf.setSexo(input.nextLine());

    System.out.println("CPF: ");
    pf.setCpf(input.nextInt());

    PessoaFisicaDAO.cadastrarPessoaFisica(pf);
}

private void adicionarPessoaJuridica(){
    PessoaJuridica pj = new PessoaJuridica();

    while (true) {
        System.out.println("User: ");
        String user = input.nextLine().trim();

        // Verificar se o usuário já existe na tabela Pessoa
        if (!PessoaJuridicaDAO.existeUsuario(user)) {
            pj.setUser(user); // Define o usuário válido no objeto PessoaJuridica
            break; // Sai do loop quando um usuário válido for fornecido
        } else {
            System.out.println("Erro: Esse usuário já existe. Tente novamente.");
        }
    }

    System.out.println("Nome: ");
    pj.setNome(input.nextLine());

    System.out.println("Telefone: ");
    pj.setTel(input.nextLine());

    System.out.println("Razao Social: ");
    pj.setRazao(input.nextLine());

    System.out.println("Cnpj: ");
    pj.setCnpj(input.nextInt());

    PessoaJuridicaDAO.cadastrarPessoaJuridica(pj);
}

private int getValorDiaria(int quartoId) {
    String sql = "SELECT qua_valor_dia FROM quarto WHERE qua_id = ?";
    try (Connection conn = Conexao.getConexao();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, quartoId);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("qua_valor_dia");
            }
        }
    } catch (Exception e) {
        System.out.println("ERRO ao buscar o valor da diária: " + e.getMessage());
        e.printStackTrace();
    }
    return 0;
}

public void adicionarReserva() {
    SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");

    System.out.print("Digite o ID do hotel: ");
    int hotelId = input.nextInt();

    System.out.print("Digite o ID do quarto: ");
    int quartoId = input.nextInt();

    System.out.print("Digite o user do cliente: ");
    String clienteNome = input.next();

    if (!isClienteCadastrado(clienteNome)) {
        System.out.println("Erro: Cliente não cadastrado.");
        return;
    }

    Date dataEntrada = null;
    Date dataSaida = null;

    while (dataEntrada == null) {
        System.out.print("Digite a data de entrada (DD-MM-YYYY): ");
        String dataEntradaStr = input.next();
        try {
            java.util.Date utilDateEntrada = inputFormat.parse(dataEntradaStr);
            dataEntrada = Date.valueOf(dbFormat.format(utilDateEntrada));
        } catch (ParseException e) {
            System.out.println("Data de entrada inválida. Por favor, use o formato DD-MM-YYYY.");
        }
    }

    while (dataSaida == null) {
        System.out.print("Digite a data de saída (DD-MM-YYYY): ");
        String dataSaidaStr = input.next();
        try {
            java.util.Date utilDateSaida = inputFormat.parse(dataSaidaStr);
            dataSaida = Date.valueOf(dbFormat.format(utilDateSaida));
        } catch (ParseException e) {
            System.out.println("Data de saída inválida. Por favor, use o formato DD-MM-YYYY.");
        }
    }

    // Verifica se o quarto está disponível
    if (!reservaDAO.isQuartoDisponivel(hotelId, quartoId, dataEntrada, dataSaida)) {
        System.out.println("Erro: O quarto não está disponível para o período solicitado.");
        return;
    }

    
    // Calcular o valor total da diária
    int valorDiaria = getValorDiaria(quartoId);
    long diffInMillies = Math.abs(dataSaida.getTime() - dataEntrada.getTime());
    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    double valorTotal = diff * valorDiaria;
    System.out.println("O valor total da sua estadia é: R$ " + valorTotal);

    // Se o quarto estiver disponível, adicione a reserva
    Reserva reserva = new Reserva();
    reserva.setHotel(hotelId);
    reserva.setQuarto(quartoId);
    reserva.setCliente(clienteNome);
    reserva.setRes_data_entrada(dataEntrada);
    reserva.setRes_data_saida(dataSaida);

    reservaDAO.adicionarReserva(reserva);
}

private boolean isClienteCadastrado(String clienteNome) {
    String sql = "SELECT COUNT(*) FROM pessoa WHERE usuario = ?";
    try (Connection conn = Conexao.getConexao();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, clienteNome);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        }
    } catch (Exception e) {
        System.out.println("ERRO ao verificar cliente: " + e.getMessage());
        e.printStackTrace();
    }
    return false;
}

private void listarReservas() {
System.out.println("Digite o ID do hotel: ");
int hotelId = input.nextInt();

 List<Reserva> reservas = reservaDAO.listarReservasPorHotel(hotelId);

if (reservas.isEmpty()) {
    System.out.println("Nenhuma reserva encontrada para o ID de hotel fornecido.");
} else {
    for (Reserva reserva : reservas) {
        System.out.println("Reserva [ID: " + reserva.getId() + " | Usuário: " + reserva.getCliente() + " | Entrada: " + reserva.getRes_data_entrada() + " | Saída: " + reserva.getRes_data_saida() + "]");
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

    System.out.println("Digite o estado do hotel: (Sigla)");
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

    System.out.println("Digite o status do hotel:(Ativo  - S | Não Ativo - N) ");
    novoHotel.setStatus(input.nextLine().trim().toUpperCase());

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
        input.nextLine(); 
    } catch (InputMismatchException e) {
        System.out.println("ID inválido! Por favor, insira um número.");
        input.nextLine(); 
        return;
    }

    HotelDAO hotelDAO = new HotelDAO();  // Instancia a classe HotelDAO

    if (!hotelDAO.hotelExiste(id)) {
        System.out.println("Hotel com ID " + id + " não encontrado.");
        return;
    }

    hotelDAO.deletarHotel(id); // Chama o método através da instância hotel
}
private void adicionarQuarto() {
    System.out.println("Digite o ID do hotel ao qual deseja adicionar um quarto: ");
    int hotelId = input.nextInt();
    input.nextLine(); 

    HotelDAO hotelDAO = new HotelDAO(); // Criação da instância de HotelDAO
    Hotel hotel = hotelDAO.obterHotelPorId(hotelId); // Chamada do método através da instância

    if (hotel == null) {
        System.out.println("Hotel não encontrado.");
        return;
    }

    System.out.println("Digite o número de camas:");
    int camas = input.nextInt();
    input.nextLine(); 

    System.out.println("Digite o valor diário:");
    int valorDia = input.nextInt();
    input.nextLine(); 

    System.out.println("O quarto tem sala de estar? (s/n)");
    boolean temSalaDeEstar = input.nextLine().equalsIgnoreCase("s");

    System.out.println("O quarto tem jacuzzi? (s/n)");
    boolean temJacuzzi = input.nextLine().equalsIgnoreCase("s");

    
    Quarto quarto;

    String nomeQuarto;
    if (temSalaDeEstar || temJacuzzi) {
        nomeQuarto = "Quarto Presidencial";
        quarto = new QuartoPresidencial(hotel.getId(), nomeQuarto, camas, valorDia, temJacuzzi, temSalaDeEstar);
        System.out.println("Quarto Presidencial adicionado com sucesso!");
    } else if (temJacuzzi) {
        nomeQuarto = "Quarto Deluxe";
        quarto = new QuartoDeluxe(hotel.getId(), nomeQuarto, camas, valorDia, temJacuzzi);
        System.out.println("Quarto Deluxe adicionado com sucesso!");
    } else {
        nomeQuarto = "Quarto Standard";
        quarto = new QuartoStandard(hotel.getId(), nomeQuarto, camas, valorDia);
        System.out.println("Quarto Standard adicionado com sucesso!");
    }

    // Atualize o quarto com o nome definido
    quarto.setNome(nomeQuarto);

    // O ID do quarto será preenchido automaticamente no banco de dados, então não é necessário definir o ID aqui

    QuartoDAO quartoDAO = new QuartoDAO();
    quartoDAO.adicionarQuarto(quarto);
}

private void deletarQuarto() {
    System.out.println("Deletar quarto existente");
    System.out.println("Digite o ID do hotel: ");
    int hotelId = input.nextInt();
    input.nextLine(); 

    System.out.println("Digite o ID do quarto: ");
    int id = input.nextInt();
    input.nextLine(); 


    
    HotelDAO hotelDAO = new HotelDAO();
    QuartoDAO quartoDAO = new QuartoDAO();


    Hotel hotel = hotelDAO.obterHotelPorId(hotelId);

    if (hotel == null) {
        System.out.println("Hotel não encontrado.");
        return;
    }

    
    quartoDAO.deletarQuarto(id, hotel);
}
private void menuHoteis() {
    int escolhaHotel;
    do {
        System.out.println("*----------------------------------------------------*");
        System.out.println("*                   Menu Hoteis                      *");
        System.out.println("*                                                    *");
        System.out.println("* 1. Visualizar todos os hoteis                      *");
        System.out.println("* 2. Adicionar Hotel                                 *");
        System.out.println("* 3. Atualizar Hotel                                 *");
        System.out.println("* 4. Deletar Hotel                                   *");
        System.out.println("* 5. Voltar ao Menu Principal                        *");
        System.out.println("*----------------------------------------------------*");
        System.out.println("");
        System.out.println("Escolha uma das opções acima.");

        System.out.print("Escolha: ");
        while (!input.hasNextInt()) { 
            System.out.println("Entrada inválida! Por favor, insira um valor válido.");
            System.out.print("Escolha: ");
            input.next(); 
        }
        escolhaHotel = input.nextInt();

        switch (escolhaHotel) {
            case 1:
                hotel.mostrarHoteis();
                break;
            case 2:
                adicionarHotel();
                break;
            case 3:
                atualizarHotel();
                break;
            case 4:
                deletarHotel();
                break;
            case 5:
                System.out.println("Voltando ao Menu Principal...");
                break;
            default:
                System.out.println("Entrada inválida! Por favor insira uma das opções a seguir: ");
        }
    } while (escolhaHotel != 5);
}

private void menuQuartos() {
    int escolhaQuarto;
    do {
        System.out.println("*----------------------------------------------------*");
        System.out.println("*                   Menu Quartos                     *");
        System.out.println("*                                                    *");
        System.out.println("* 1. Listar Quartos                                  *");
        System.out.println("* 2. Adicionar Quarto                                *");
        System.out.println("* 3. Atualizar Quarto                                *");
        System.out.println("* 4. Deletar Quarto                                  *");
        System.out.println("* 5. Voltar ao Menu Principal                        *");
        System.out.println("*----------------------------------------------------*");
        System.out.println("");
        System.out.println("Escolha uma das opções acima.");

        System.out.print("Escolha: ");
        while (!input.hasNextInt()) { 
            System.out.println("Entrada inválida! Por favor, insira um valor válido.");
            System.out.print("Escolha: ");
            input.next(); 
        }
        escolhaQuarto = input.nextInt();

        switch (escolhaQuarto) {
            case 1:
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
            case 2:
                adicionarQuarto();
                break;
            case 3:
                //atualizarQuarto();
                break;
            case 4:
                deletarQuarto();
                break;
            case 5:
                System.out.println("Voltando ao Menu Principal...");
                break;
            default:
                System.out.println("Entrada inválida! Por favor insira uma das opções a seguir: ");
        }
    } while (escolhaQuarto != 5);
}

private void menuReserva() {
    int escolhaReserva;
    do {
        System.out.println("*----------------------------------------------------*");
        System.out.println("*                   Menu Reserva                     *");
        System.out.println("*                                                    *");
        System.out.println("* 1. Adicionar Reserva                               *");
        System.out.println("* 2. Listar Reservas                                 *");
        System.out.println("* 3. Voltar ao Menu Principal                        *");
        System.out.println("*----------------------------------------------------*");
        System.out.println("");
        System.out.println("Escolha uma das opções acima.");

        System.out.print("Escolha: ");
        while (!input.hasNextInt()) { 
            System.out.println("Entrada inválida! Por favor, insira um valor válido.");
            System.out.print("Escolha: ");
            input.next(); 
        }
        escolhaReserva = input.nextInt();

        switch (escolhaReserva) {
            case 1:
                adicionarReserva();
                break;
            case 2:
                listarReservas();
                break;
            case 3:
                System.out.println("Voltando ao Menu Principal...");
                break;
            default:
                System.out.println("Entrada inválida! Por favor insira uma das opções a seguir: ");
        }
    } while (escolhaReserva != 3);
}

private void menuClientes() {
    int escolhaCliente;
    do {
        System.out.println("*----------------------------------------------------*");
        System.out.println("*                   Menu Clientes                    *");
        System.out.println("*                                                    *");
        System.out.println("* 1. Adicionar Cliente                               *");
        System.out.println("* 2. Adicionar Funcionário                           *");
        System.out.println("* 3. Voltar ao Menu Principal                        *");
        System.out.println("*----------------------------------------------------*");
        System.out.println("");
        System.out.println("Escolha uma das opções acima.");

        System.out.print("Escolha: ");
        while (!input.hasNextInt()) { 
            System.out.println("Entrada inválida! Por favor, insira um valor válido.");
            System.out.print("Escolha: ");
            input.next(); 
        }
        escolhaCliente = input.nextInt();

        switch (escolhaCliente) {
            case 1:
                cadastrarCliente();
                break;
            case 2:
                adicionarFuncionario();
                break;
            case 3:
                System.out.println("Voltando ao Menu Principal...");
                break;
            default:
                System.out.println("Entrada inválida! Por favor insira uma das opções a seguir: ");
        }
    } while (escolhaCliente != 2);
}


private void adicionarFuncionario(){
    Funcionario funcionario = new Funcionario();

    System.out.println("User: ");
    funcionario.setUser(input.nextLine());

    System.out.println("Nome: ");
    funcionario.setNome(input.nextLine());

    System.out.println("Telefone: ");
    funcionario.setTel(input.nextLine());

    System.out.println("CPF: ");
    funcionario.setCpf(input.nextInt());

    System.out.println("Sexo (S/M/O): ");
    funcionario.setSexo(input.nextLine());

    System.out.println("Cargo: ");
    funcionario.setCargo(input.nextLine());

    System.out.println("Salário: ");
    funcionario.setSalario(input.nextInt());

    funcionarioDAO.cadastrarFuncionario(funcionario);
}


}

