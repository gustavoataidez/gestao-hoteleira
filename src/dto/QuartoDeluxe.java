package dto;

public class QuartoDeluxe extends Quarto {
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
