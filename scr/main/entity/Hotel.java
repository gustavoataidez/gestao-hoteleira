package entity;

public class Hotel {
    private String nome;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone;
    private Integer estrelas;
    private Integer vlr_quarto1;
    private Integer vlr_quarto2;
    private Integer vlr_quarto3;
    private String observacao;
    private String site;
    private Character status;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public Integer getEstrelas() {
        return estrelas;
    }
    public void setEstrelas(Integer estrelas) {
        this.estrelas = estrelas;
    }
    public Integer getVlr_quarto1() {
        return vlr_quarto1;
    }
    public void setVlr_quarto1(Integer vlr_quarto1) {
        this.vlr_quarto1 = vlr_quarto1;
    }
    public Integer getVlr_quarto2() {
        return vlr_quarto2;
    }
    public void setVlr_quarto2(Integer vlr_quarto2) {
        this.vlr_quarto2 = vlr_quarto2;
    }
    public Integer getVlr_quarto3() {
        return vlr_quarto3;
    }
    public void setVlr_quarto3(Integer vlr_quarto3) {
        this.vlr_quarto3 = vlr_quarto3;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    public String getSite() {
        return site;
    }
    public void setSite(String site) {
        this.site = site;
    }
    public Character getStatus() {
        return status;
    }
    public void setStatus(Character status) {
        this.status = status;
    }
    
}
