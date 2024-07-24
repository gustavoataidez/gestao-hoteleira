package dto;

public class Quarto {
    protected int id;
    protected int hotel;
    protected String nome;
    protected int camas;
    protected int valor_dia;
    protected boolean jacuzzi;
    protected boolean salaDeEstar;
    
    public Quarto( int hotel, String nome, int camas, int valor_dia, boolean jacuzzi, boolean salaDeEstar) {
        this.hotel = hotel;
        this.nome = nome;
        this.camas = camas;
        this.valor_dia = valor_dia;
    }

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
    public boolean isJacuzzi() {
        return jacuzzi;
    }

    public void setJacuzzi(boolean jacuzzi) {
        this.jacuzzi = jacuzzi;
    }

    public boolean isSalaEstar() {
        return salaDeEstar;
    }

    public void setsalaDeEstar(boolean salaDeEstar) {
        this.salaDeEstar = salaDeEstar;
    }
    
    @Override
    public String toString() {
        return "QUARTO [id:" + id + " | " + nome + " | " + camas + " camas | valor da diária: R$" + valor_dia + ",00 ]";
    }
}
