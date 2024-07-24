package dto;

public class Funcionario extends PessoaFisica {
    private String cargo;
    private int salario;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario [user=" + getUser() + ", nome=" + getNome() + ", tel=" + getTel() + ", cargo=" + cargo + ", salario=" + salario + "]";
    }
}
