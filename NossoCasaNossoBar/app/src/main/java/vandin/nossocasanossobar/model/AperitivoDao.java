package vandin.nossocasanossobar.model;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vandin.nossocasanossobar.dao.Dao;
import vandin.nossocasanossobar.pojo.Aperitivo;

/**
 * Created by Vandin on 03/11/2016.
 */

public class AperitivoDao extends Dao {

    //Atributo para armazenar o objeto instanciado
    private static AperitivoDao mInstance = null;

    //Método Construtor
    private AperitivoDao(Context ctx, String tabela, String[] colunas) {
        super(ctx, tabela, colunas);
    }

    //Método SINGLETON
    public static AperitivoDao getInstance(Context ctx) {
        if (mInstance == null) {
            String[] colunas = new String[] {
                    AperitivoUri._ID,
                    AperitivoUri._NOME,
                    AperitivoUri._PRECO
            };
            mInstance = new AperitivoDao(ctx, "aperitivos", colunas);
        }
        return mInstance;
    }

    //Fechar conexão e eliminar objeto
    public void fecharConexao() {
        super.fecharConexao();
        mInstance = null;
    }

    //Formata os valores para manipular banco de dados
    private ContentValues getCampos(Aperitivo objeto) {
        ContentValues cv = new ContentValues();
        cv.put(AperitivoUri._NOME, objeto.getNome());
        cv.put(AperitivoUri._PRECO, objeto.getPreco());

        return cv;
    }

    //Inclusão
    public long incluir(Aperitivo objeto) {
        long id = 0;
        ContentValues cv = getCampos(objeto);
        id = inserir(cv);
        return id;
    }

    //Atualizar
    public long atualizar(Aperitivo objeto, long id) {
        ContentValues cv = getCampos(objeto);
        if (id > 0) { //Atualizar
            String _id = String.valueOf(id);
            String where = AperitivoUri._ID + "=?";
            String[] whereArgs = new String[] {
                    _id
            };
            atualizar(cv, where, whereArgs);
        }
        else { //Incluir
            id = incluir(objeto);
        }
        return id;
    }

    //Deletar
    public void deletar(String[] id) {
        deletar("id_aperitivo = ?", id);
    }

    /**
     * Método para listar todos registros
     */
    public List<Aperitivo> getTodosRegistros() {
        List<Aperitivo> listagem = null;
        try {
            Cursor c = db.rawQuery("SELECT * FROM aperitivos", null);
            if (c.moveToFirst()) {
                listagem = new ArrayList<Aperitivo>();

                //Capturam os indices das colunas no SQLite retornadas pelo Cursor
                int idx_idAperitivo   = c.getColumnIndex(AperitivoUri._ID);
                int idx_nome        = c.getColumnIndex(AperitivoUri._NOME);
                int idx_preco       = c.getColumnIndex(AperitivoUri._PRECO);

                do {
                    Aperitivo aperitivo = new Aperitivo();
                    aperitivo.setId_aperitivo(c.getLong(idx_idAperitivo));
                    aperitivo.setNome(c.getString(idx_nome));
                    aperitivo.setPreco(c.getString(idx_preco));
                    listagem.add(aperitivo);

                } while (c.moveToNext());
            }

        } catch (Exception e) {
            Log.e("Erro", "Erro ao listar os aperitivos: " + e.getMessage());
        }
        return listagem;
    }

    /**
     * Classe serve como CONTENT PROVIDER
     */
    public static final class AperitivoUri implements BaseColumns {

        /**
         * Método Construtor
         */
        public AperitivoUri(){}

        public static final String AUTHORITY = "vandin.nossocasanossobar.provider/aperitivos";
        public static final Uri CONTEXT_URI = Uri.parse("content://" + AUTHORITY);
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.aperitivos";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.aperitivos";

        public static final String _ID = "id_aperitivo";
        public static final String _NOME = "nome";
        public static final String _PRECO = "preco";

        public static Uri getUriId(long id) {
            Uri uri = ContentUris.withAppendedId(CONTEXT_URI, id);
            return uri;
        }


    }
}
