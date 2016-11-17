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
import br.com.gustavo.agendashow.pojo.Musica;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class MusicaDao extends Dao {

    private static MusicaDao mInstance = null;

    private MusicaDao(Context ctx, String tabela, String[] colunas) {
        super(ctx, tabela, colunas);
    }

    public static MusicaDao getInstance(Context ctx) {
        if (mInstance == null) {
            String[] colunas = new String[] {
                    MusicaUri._ID,
                    MusicaUri._NOME,
                    MusicaUri._COMENTARIO
            };
            mInstance = new MusicaDao(ctx, "musica", colunas);
        }
        return mInstance;
    }

    public void fecharConexao() {
        super.fecharConexao();
        mInstance = null;
    }

    private ContentValues getCampos(Musica objeto) {
        ContentValues cv = new ContentValues();
        cv.put(MusicaUri._NOME, objeto.getNome());
        cv.put(MusicaUri._COMENTARIO, objeto.getComentario());

        return cv;
    }

    public long incluir(Musica objeto) {
        long id = 0;
        ContentValues cv = getCampos(objeto);
        id = inserir(cv);
        return id;
    }

    public long atualizar(Musica objeto, long id) {
        ContentValues cv = getCampos(objeto);
        if (id > 0) {
            String _id = String.valueOf(id);
            String where = MusicaUri._ID + "=?";
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
        deletar("id_musica = ?", id);
    }

    public List<Musica> getTodosRegistros() {
        List<Musica> listagem = null;
        try {
            Cursor c = db.rawQuery("SELECT * FROM musica", null);
            if (c.moveToFirst()) {
                listagem = new ArrayList<Musica>();

                int idx_idMusica     = c.getColumnIndex(MusicaUri._ID);
                int idx_nome         = c.getColumnIndex(MusicaUri._NOME);
                int idx_comentario   = c.getColumnIndex(MusicaUri._COMENTARIO);

                do {
                    Musica musica = new Musica();
                    musica.setId_musica(c.getLong(idx_idMusica));
                    musica.setNome(c.getString(idx_nome));
                    musica.setComentario(c.getString(idx_comentario));
                    listagem.add(musica);

                } while (c.moveToNext());
            }

        } catch (Exception e) {
            Log.e("Erro", "Erro ao listar musica: " + e.getMessage());
        }
        return listagem;
    }

    public static final classMusicaUri implements BaseColumns

    {

        public MusicaUri(){}

        public static final String AUTHORITY = "br.com.gustavo.agendashow.provider/musica";
        public static final Uri CONTEXT_URI = Uri.parse("content://" + AUTHORITY);
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.musica";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.musica";

        public static final String _ID = "id_musica";
        public static final String _NOME = "nome";
        public static final String _COMENTARIO = "comentario";

    public static Uri getUriId(long id) {
        Uri uri = ContentUris.withAppendedId(CONTEXT_URI, id);
        return uri;
    }


}
}
