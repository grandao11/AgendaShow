package br.com.gustavo.agendashow.pojo;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class Musica {

        private long id_musica;
        private String nome;
        private String comentario;

        public Musica(){}

    public long getId_musica() {
        return id_musica;
    }

    public void setId_musica(long id_musica) {
        this.id_musica = id_musica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
