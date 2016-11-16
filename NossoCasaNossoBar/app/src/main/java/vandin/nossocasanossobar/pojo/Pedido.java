package vandin.nossocasanossobar.pojo;

/**
 * Created by Vandin on 02/11/2016.
 */

public class Pedido {

    //Atributos da Classe
    private long id_pedido;
    private String cliente;
    private String bebida;
    private String aperitivo;

    //Método Construtor
    public Pedido(){}

    //Encapsulamento
    public long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getAperitivo() {
        return aperitivo;
    }

    public void setAperitivo(String aperitivo) {
        this.aperitivo = aperitivo;
    }
}
