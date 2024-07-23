package dto;

public class Pessoa {
    private String user;
    private String nome;
    private String tel;

    // Getters e Setters
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Pessoa [user=" + user + ", nome=" + nome + ", tel=" + tel + "]";
    }
}
