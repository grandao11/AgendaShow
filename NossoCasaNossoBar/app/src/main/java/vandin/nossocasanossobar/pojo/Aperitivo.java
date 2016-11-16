package vandin.nossocasanossobar.pojo;

/**
 * Created by Vandin on 03/11/2016.
 */

public class Aperitivo {

    //Atributos da Classe
    private long id_aperitivo;
    private String nome;
    private String preco;

    //Método Construtor
    public Aperitivo(){}

    public long getId_aperitivo() {
        return id_aperitivo;
    }

    public void setId_aperitivo(long id_aperitivo) {
        this.id_aperitivo = id_aperitivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
