package entity;

public class Quarto {
    private int codigo;
    private int hotel;
    private String nome;
    private int camas;
    private int valor_dia;
    private boolean reservado;

    public Quarto(int codigo, int hotel, String nome, int camas, int valor_dia, boolean reservado) {
        this.codigo = codigo;
        this.hotel = hotel;
        this.nome = nome;
        this.camas = camas;
        this.valor_dia = valor_dia;
        this.reservado = reservado;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getHotel() {
        return this.hotel;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCamas() {
        return this.camas;
    }

    public void setCamas(int camas) {
        this.camas = camas;
    }

    public int getValor_dia() {
        return this.valor_dia;
    }

    public void setValor_dia(int valor_dia) {
        this.valor_dia = valor_dia;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    @Override
    public String toString() {
        return "QUARTO [id:" + this.codigo + " | " + this.nome + " | " + this.camas + " camas | valor da diária: R$" + this.valor_dia + ",00 | Reservado: " + this.reservado + " ]";
    }
}
