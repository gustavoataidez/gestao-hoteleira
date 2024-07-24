package dto;

public class QuartoPresidencial extends Quarto {
    private boolean temSalaDeEstar;

    public QuartoPresidencial(int idHotel, String nome, int camas, int valorDia, boolean temSalaDeEstar, boolean jacuzzi) {
        super(idHotel, nome, camas, valorDia, true, temSalaDeEstar);
        this.temSalaDeEstar = temSalaDeEstar;
    }

    public boolean isTemSalaDeEstar() {
        return temSalaDeEstar;
    }

    public void setTemSalaDeEstar(boolean temSalaDeEstar) {
        this.temSalaDeEstar = temSalaDeEstar;
    }



    @Override
    public String toString() {
        return super.toString() + " | Presidencial: " + (temSalaDeEstar ? "Com Sala de Estar" : "Sem Sala de Estar") + " | ";
    }
}
