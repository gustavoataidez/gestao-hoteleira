package dto;

public class QuartoDeluxe extends Quarto {
    private boolean temJacuzzi;

    public QuartoDeluxe(int idHotel, String nome, int camas, int valorDia, boolean temJacuzzi) {
        super(idHotel, nome, camas, valorDia,true,temJacuzzi);
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
