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
import vandin.nossocasanossobar.pojo.Cliente;

/**
 * Created by Vandin on 01/11/2016.
 */

public class ClienteDao extends Dao {

    //Atributo para armazenar o objeto instanciado
    private static ClienteDao mInstance = null;

    //Método Construtor
    private ClienteDao(Context ctx, String tabela, String[] colunas) {
        super(ctx, tabela, colunas);
    }

    //Método SINGLETON
    public static ClienteDao getInstance(Context ctx) {
        if (mInstance == null) {
            String[] colunas = new String[] {
                    ClienteUri._ID,
                    ClienteUri._NOME,
                    ClienteUri._CPF,
                    ClienteUri._CELULAR
            };
            mInstance = new ClienteDao(ctx, "clientes", colunas);
        }
        return mInstance;
    }

    //Fechar conexão e eliminar objeto
    public void fecharConexao() {
        super.fecharConexao();
        mInstance = null;
    }

    //Formata os valores para manipular banco de dados
    private ContentValues getCampos(Cliente objeto) {
        ContentValues cv = new ContentValues();
        cv.put(ClienteUri._NOME, objeto.getNome());
        cv.put(ClienteUri._CPF, objeto.getCpf());
        cv.put(ClienteUri._CELULAR, objeto.getCelular());

        return cv;
    }

    //Inclusão
    public long incluir(Cliente objeto) {
        long id = 0;
        ContentValues cv = getCampos(objeto);
        id = inserir(cv);
        return id;
    }

    //Atualizar
    public long atualizar(Cliente objeto, long id) {
        ContentValues cv = getCampos(objeto);
        if (id > 0) { //Atualizar
            String _id = String.valueOf(id);
            String where = ClienteUri._ID + "=?";
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
        deletar("id_cliente = ?", id);
    }

    /**
     * Método para listar todos registros
     */
    public List<Cliente> getTodosRegistros() {
        List<Cliente> listagem = null;
        try {
            Cursor c = db.rawQuery("SELECT * FROM clientes", null);
            if (c.moveToFirst()) {
                listagem = new ArrayList<Cliente>();

                //Capturam os indices das colunas no SQLite retornadas pelo Cursor
                int idx_idCliente   = c.getColumnIndex(ClienteUri._ID);
                int idx_nome        = c.getColumnIndex(ClienteUri._NOME);
                int idx_cpf       = c.getColumnIndex(ClienteUri._CPF);
                int idx_celular    = c.getColumnIndex(ClienteUri._CELULAR);

                do {
                    Cliente cliente = new Cliente();
                    cliente.setId_cliente(c.getLong(idx_idCliente));
                    cliente.setNome(c.getString(idx_nome));
                    cliente.setCpf(c.getString(idx_cpf));
                    cliente.setCelular(c.getString(idx_celular));
                    listagem.add(cliente);

                } while (c.moveToNext());
            }

        } catch (Exception e) {
            Log.e("Erro", "Erro ao listar os clientes: " + e.getMessage());
        }
        return listagem;
    }

    /**
     * Classe serve como CONTENT PROVIDER
     */
    public static final class ClienteUri implements BaseColumns {

        /**
         * Método Construtor
         */
        public ClienteUri(){}

        public static final String AUTHORITY = "vandin.nossocasanossobar.provider/clientes";
        public static final Uri CONTEXT_URI = Uri.parse("content://" + AUTHORITY);
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.clientes";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.clientes";

        public static final String _ID = "id_cliente";
        public static final String _NOME = "nome";
        public static final String _CPF = "cpf";
        public static final String _CELULAR = "celular";

        public static Uri getUriId(long id) {
            Uri uri = ContentUris.withAppendedId(CONTEXT_URI, id);
            return uri;
        }


    }
}
