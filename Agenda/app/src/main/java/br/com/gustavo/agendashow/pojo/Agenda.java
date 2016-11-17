package br.com.gustavo.agendashow.pojo;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class Agenda {
    private long id_agenda;
    private String musica;
    private String cidade;

    public Agenda(){}

    public long getId_agenda() {
        return id_agenda;
    }

    public void setId_agenda(long id_agenda) {
        this.id_agenda = id_agenda;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
