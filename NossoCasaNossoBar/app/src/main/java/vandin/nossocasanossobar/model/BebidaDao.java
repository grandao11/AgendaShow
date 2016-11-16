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
import vandin.nossocasanossobar.pojo.Bebida;

/**
 * Created by Vandin on 03/11/2016.
 */

public class BebidaDao extends Dao {

    //Atributo para armazenar o objeto instanciado
    private static BebidaDao mInstance = null;

    //Método Construtor
    private BebidaDao(Context ctx, String tabela, String[] colunas) {
        super(ctx, tabela, colunas);
    }

    //Método SINGLETON
    public static BebidaDao getInstance(Context ctx) {
        if (mInstance == null) {
            String[] colunas = new String[] {
                    BebidaUri._ID,
                    BebidaUri._NOME,
                    BebidaUri._PRECO
            };
            mInstance = new BebidaDao(ctx, "bebidas", colunas);
        }
        return mInstance;
    }

    //Fechar conexão e eliminar objeto
    public void fecharConexao() {
        super.fecharConexao();
        mInstance = null;
    }

    //Formata os valores para manipular banco de dados
    private ContentValues getCampos(Bebida objeto) {
        ContentValues cv = new ContentValues();
        cv.put(BebidaUri._NOME, objeto.getNome());
        cv.put(BebidaUri._PRECO, objeto.getPreco());

        return cv;
    }

    //Inclusão
    public long incluir(Bebida objeto) {
        long id = 0;
        ContentValues cv = getCampos(objeto);
        id = inserir(cv);
        return id;
    }

    //Atualizar
    public long atualizar(Bebida objeto, long id) {
        ContentValues cv = getCampos(objeto);
        if (id > 0) { //Atualizar
            String _id = String.valueOf(id);
            String where = BebidaUri._ID + "=?";
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
        deletar("id_bebida = ?", id);
    }

    /**
     * Método para listar todos registros
     */
    public List<Bebida> getTodosRegistros() {
        List<Bebida> listagem = null;
        try {
            Cursor c = db.rawQuery("SELECT * FROM bebidas", null);
            if (c.moveToFirst()) {
                listagem = new ArrayList<Bebida>();

                //Capturam os indices das colunas no SQLite retornadas pelo Cursor
                int idx_idBebida   = c.getColumnIndex(BebidaUri._ID);
                int idx_nome        = c.getColumnIndex(BebidaUri._NOME);
                int idx_preco       = c.getColumnIndex(BebidaUri._PRECO);

                do {
                    Bebida bebida = new Bebida();
                    bebida.setId_bebida(c.getLong(idx_idBebida));
                    bebida.setNome(c.getString(idx_nome));
                    bebida.setPreco(c.getString(idx_preco));
                    listagem.add(bebida);

                } while (c.moveToNext());
            }

        } catch (Exception e) {
            Log.e("Erro", "Erro ao listar os bebidas: " + e.getMessage());
        }
        return listagem;
    }

    /**
     * Classe serve como CONTENT PROVIDER
     */
    public static final class BebidaUri implements BaseColumns {

        /**
         * Método Construtor
         */
        public BebidaUri(){}

        public static final String AUTHORITY = "vandin.nossocasanossobar.provider/bebidas";
        public static final Uri CONTEXT_URI = Uri.parse("content://" + AUTHORITY);
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.bebidas";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.bebidas";

        public static final String _ID = "id_bebida";
        public static final String _NOME = "nome";
        public static final String _PRECO = "preco";

        public static Uri getUriId(long id) {
            Uri uri = ContentUris.withAppendedId(CONTEXT_URI, id);
            return uri;
        }


    }
}
