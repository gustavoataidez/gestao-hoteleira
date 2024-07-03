package entity;

public class Reserva {
    private Integer hotel;
    private Integer quarto;
    private String cliente;
    private String res_data_entrada;
    private String res_data_saida;
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
    public String getRes_data_entrada() {
        return res_data_entrada;
    }
    public void setRes_data_entrada(String res_data_entrada) {
        this.res_data_entrada = res_data_entrada;
    }
    public String getRes_data_saida() {
        return res_data_saida;
    }
    public void setRes_data_saida(String res_data_saida) {
        this.res_data_saida = res_data_saida;
    }
    public Character getRes_status() {
        return res_status;
    }
    public void setRes_status(Character res_status) {
        this.res_status = res_status;
    }

}
