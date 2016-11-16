package vandin.nossocasanossobar.pojo;

/**
 * Created by Vandin on 03/11/2016.
 */

public class Bebida {

    //Atributos da Classe
    private long id_bebida;
    private String nome;
    private String preco;

    //Método Construtor
    public Bebida(){}

    //Encapsulamento
    public long getId_bebida() {
        return id_bebida;
    }

    public void setId_bebida(long id_bebida) {
        this.id_bebida = id_bebida;
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
