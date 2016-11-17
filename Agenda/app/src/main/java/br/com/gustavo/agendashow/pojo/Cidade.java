package br.com.gustavo.agendashow.pojo;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class Cidade {
        private long id_cidade;
        private String nome;
        private String bairro;

        public Cidade(){}

    public long getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(long id_cidade) {
        this.id_cidade = id_cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
