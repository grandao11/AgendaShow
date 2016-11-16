package vandin.nossocasanossobar.pojo;

/**
 * Created by Vandin on 01/11/2016.
 */

public class Cliente {

    //Atributos da Classe
    private long id_cliente;
    private String nome;
    private String cpf;
    private String celular;

    //Método Construtor
    public Cliente(){}

    //Encapsulamento
    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
