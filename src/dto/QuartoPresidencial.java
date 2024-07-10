package dto;

public class QuartoPresidencial extends Quarto {
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
