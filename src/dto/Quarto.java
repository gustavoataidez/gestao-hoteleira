package dto;

public abstract class Quarto {
    protected int codigo;
    protected int hotel;
    protected String nome;
    protected int camas;
    protected int valor_dia;
    
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

class QuartoStandard extends Quarto {
    public QuartoStandard(int codigo, int hotel, String nome, int camas, int valor_dia) {
        super(codigo, hotel, nome, camas, valor_dia);
    }
}

class QuartoDeluxe extends Quarto {
    private boolean temJacuzzi;

    public QuartoDeluxe(int codigo, int hotel, String nome, int camas, int valor_dia, boolean temJacuzzi) {
        super(codigo, hotel, nome, camas, valor_dia);
        this.temJacuzzi = temJacuzzi;
    }

    public boolean isTemJacuzzi() {
        return temJacuzzi;
    }

    public void setTemJacuzzi(boolean temJacuzzi) {
        this.temJacuzzi = temJacuzzi;
    }

    @Override
    public String toString() {
        return super.toString() + " | Deluxe: " + (temJacuzzi ? "Com Jacuzzi" : "Sem Jacuzzi");
    }
}

class QuartoPresidencial extends Quarto {
    private boolean temSalaDeEstar;
    private boolean temPiscinaPrivativa;

    public QuartoPresidencial(int codigo, int hotel, String nome, int camas, int valor_dia, boolean temSalaDeEstar, boolean temPiscinaPrivativa) {
        super(codigo, hotel, nome, camas, valor_dia);
        this.temSalaDeEstar = temSalaDeEstar;
        this.temPiscinaPrivativa = temPiscinaPrivativa;
    }

    public boolean isTemSalaDeEstar() {
        return temSalaDeEstar;
    }

    public void setTemSalaDeEstar(boolean temSalaDeEstar) {
        this.temSalaDeEstar = temSalaDeEstar;
    }

    public boolean isTemPiscinaPrivativa() {
        return temPiscinaPrivativa;
    }

    public void setTemPiscinaPrivativa(boolean temPiscinaPrivativa) {
        this.temPiscinaPrivativa = temPiscinaPrivativa;
    }

    @Override
    public String toString() {
        return super.toString() + " | Presidencial: " + (temSalaDeEstar ? "Com Sala de Estar" : "Sem Sala de Estar") + " | " + (temPiscinaPrivativa ? "Com Piscina Privativa" : "Sem Piscina Privativa");
    }
}
