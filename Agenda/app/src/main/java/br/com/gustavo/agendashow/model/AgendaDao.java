package br.com.gustavo.agendashow.model;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.gustavo.agendashow.dao.Dao;
import br.com.gustavo.agendashow.pojo.Agenda;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class AgendaDao extends Dao{
    private static AgendaDao mInstance = null;

    private AgendaDao(Context ctx, String tabela, String[] colunas) {
        super(ctx, tabela, colunas);
    }

    public static AgendaDao getInstance(Context ctx) {
        if (mInstance == null) {
            String[] colunas = new String[] {
                    AgendaUri._ID,
                    AgendaUri._MUSICA,
                    AgendaUri._CIDADE
            };
            mInstance = new AgendaDao(ctx, "agenda", colunas);
        }
        return mInstance;
    }

    public void fecharConexao() {
        super.fecharConexao();
        mInstance = null;
    }

    private ContentValues getCampos(Agenda objeto) {
        ContentValues cv = new ContentValues();
        cv.put(AgendaUri._MUSICA, objeto.getMusica());
        cv.put(AgendaUri._CIDADE, objeto.getCidade());

        return cv;
    }

    public long incluir(Agenda objeto) {
        long id = 0;
        ContentValues cv = getCampos(objeto);
        id = inserir(cv);
        return id;
    }

    public long atualizar(Agenda objeto, long id) {
        ContentValues cv = getCampos(objeto);
        if (id > 0) { //Atualizar
            String _id = String.valueOf(id);
            String where = AgendaUri._ID + "=?";
            String[] whereArgs = new String[] {
                    _id
            };
            atualizar(cv, where, whereArgs);
        }
        else {
            id = incluir(objeto);
        }
        return id;
    }

    public void deletar(String[] id) {
        deletar("id_pedido = ?", id);
    }

    public List<Agenda> getTodosRegistros() {
        List<Agenda> listagem = null;
        try {
            Cursor c = db.rawQuery("SELECT * FROM agenda", null);
            if (c.moveToFirst()) {
                listagem = new ArrayList<Agenda>();

                //Capturam os indices das colunas no SQLite retornadas pelo Cursor
                int idx_idAgenda    = c.getColumnIndex(AgendaUri._ID);
                int idx_musica      = c.getColumnIndex(AgendaUri._MUSICA);
                int idx_cidade      = c.getColumnIndex(AgendaUri._CIDADE);

                do {
                    Agenda agenda = new Agenda();
                    agenda.setId_agenda(c.getLong(idx_idAgenda));
                    agenda.setMusica(c.getString(idx_musica));
                    agenda.setCidade(c.getString(idx_cidade));
                    listagem.add(agenda);

                } while (c.moveToNext());
            }

        } catch (Exception e) {
            Log.e("Erro", "Erro ao listar a agenda: " + e.getMessage());
        }
        return listagem;
    }

    /**
     * Classe serve como CONTENT PROVIDER
     */
    public static final class AgendaUri implements BaseColumns {

        /**
         * Método Construtor
         */
        public AgendaUri(){}

        public static final String AUTHORITY = "br.com.gustavo.provider/pedidos";
        public static final Uri CONTEXT_URI = Uri.parse("content://" + AUTHORITY);
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.agenda";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.agenda";

        public static final String _ID       =   "id_pedido";
        public static final String _MUSICA   =   "musica";
        public static final String _CIDADE   =   "cidade";

        public static Uri getUriId(long id) {
            Uri uri = ContentUris.withAppendedId(CONTEXT_URI, id);
            return uri;
        }


    }
}
