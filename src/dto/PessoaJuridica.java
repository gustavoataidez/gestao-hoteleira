package dto;

public class PessoaJuridica extends Pessoa {
    private int cnpj;
    private String razao;

    public int getCnpj() {
        return cnpj;
    }
    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }
    public String getRazao() {
        return razao;
    }
    public void setRazao(String razao) {
        this.razao = razao;
    }
    @Override
    public String toString() {
        return "Emppresa [user=" + getUser() + ", nome=" + getNome() + ", tel=" + getTel() + ", cnpj=" + cnpj + ", razao=" + razao + "]";
    }
    
}
