package dto;

import java.sql.Date;

public class Reserva {
    private Integer hotel;
    private Integer quarto;
    private String cliente;
    private Date res_data_entrada;
    private Date res_data_saida;
    private Character res_status;
    
    public Integer getHotel() {
        return hotel;
    }
    public void setHotel(Integer hotel) {
        this.hotel = hotel;
    }
    public Integer getQuarto() {
        return quarto;
    }
    public void setQuarto(Integer quarto) {
        this.quarto = quarto;
    }
    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public Date getRes_data_entrada() {
        return res_data_entrada;
    }
    public Date setRes_data_entrada(Date date) {
        return this.res_data_entrada = date;
    }
    public Date getRes_data_saida() {
        return res_data_saida;
    }
    public Date setRes_data_saida(Date date) {
        return this.res_data_saida = date;
    }
    public Character getRes_status() {
        return res_status;
    }
    public void setRes_status(Character res_status) {
        this.res_status = res_status;
    }

}
