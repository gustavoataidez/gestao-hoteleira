package entity;

public class Quarto {
    private int codigo;
    private int hotel;
    private String nome;
    private int camas;
    private int valor_dia;
    
    public Quarto(int codigo, int hotel, String nome, int camas, int valor_dia) {
        this.codigo = codigo;
        this.hotel = hotel;
        this.nome = nome;
        this.camas = camas;
        this.valor_dia = valor_dia;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public int getHotel() {
        return hotel;
    }
    public void setHotel(int hotel) {
        this.hotel = hotel;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getCamas() {
        return camas;
    }
    public void setCamas(int camas) {
        this.camas = camas;
    }
    public int getValor_dia() {
        return valor_dia;
    }
    public void setValor_dia(int valor_dia) {
        this.valor_dia = valor_dia;
    }
    
    @Override
    public String toString() {
        return "QUARTO [id:" + codigo + " | " + nome + " | " + camas + " camas | valor da diária: R$" + valor_dia + ",00 ]";
    }
}
