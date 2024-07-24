package dto;

import java.sql.Date;

public class Reserva {
    private int id; // Adicionado para armazenar o ID
    private int hotel;
    private int quarto;
    private String cliente;
    private String telefone; // Adicionado para armazenar o telefone
    private Date res_data_entrada;
    private Date res_data_saida;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel() {
        return hotel;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
    }

    public int getQuarto() {
        return quarto;
    }

    public void setQuarto(int quarto) {
        this.quarto = quarto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getRes_data_entrada() {
        return res_data_entrada;
    }

    public void setRes_data_entrada(Date res_data_entrada) {
        this.res_data_entrada = res_data_entrada;
    }

    public Date getRes_data_saida() {
        return res_data_saida;
    }

    public void setRes_data_saida(Date res_data_saida) {
        this.res_data_saida = res_data_saida;
    }
}