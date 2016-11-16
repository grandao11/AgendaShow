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
import vandin.nossocasanossobar.pojo.Pedido;

/**
 * Created by Vandin on 02/11/2016.
 */

public class PedidoDao extends Dao {

    //Atributo para armazenar o objeto instanciado
    private static PedidoDao mInstance = null;

    //Método Construtor
    private PedidoDao(Context ctx, String tabela, String[] colunas) {
        super(ctx, tabela, colunas);
    }

    //Método SINGLETON
    public static PedidoDao getInstance(Context ctx) {
        if (mInstance == null) {
            String[] colunas = new String[] {
                    PedidoUri._ID,
                    PedidoUri._CLIENTE,
                    PedidoUri._BEBIDA,
                    PedidoUri._APERITIVO
            };
            mInstance = new PedidoDao(ctx, "pedidos", colunas);
        }
        return mInstance;
    }

    //Fechar conexão e eliminar objeto
    public void fecharConexao() {
        super.fecharConexao();
        mInstance = null;
    }

    //Formata os valores para manipular banco de dados
    private ContentValues getCampos(Pedido objeto) {
        ContentValues cv = new ContentValues();
        cv.put(PedidoUri._CLIENTE, objeto.getCliente());
        cv.put(PedidoUri._BEBIDA, objeto.getBebida());
        cv.put(PedidoUri._APERITIVO, objeto.getAperitivo());

        return cv;
    }

    //Inclusão
    public long incluir(Pedido objeto) {
        long id = 0;
        ContentValues cv = getCampos(objeto);
        id = inserir(cv);
        return id;
    }

    //Atualizar
    public long atualizar(Pedido objeto, long id) {
        ContentValues cv = getCampos(objeto);
        if (id > 0) { //Atualizar
            String _id = String.valueOf(id);
            String where = PedidoUri._ID + "=?";
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
        deletar("id_pedido = ?", id);
    }

    /**
     * Método para listar todos registros
     */
    public List<Pedido> getTodosRegistros() {
        List<Pedido> listagem = null;
        try {
            Cursor c = db.rawQuery("SELECT * FROM pedidos", null);
            if (c.moveToFirst()) {
                listagem = new ArrayList<Pedido>();

                //Capturam os indices das colunas no SQLite retornadas pelo Cursor
                int idx_idPedido   = c.getColumnIndex(PedidoUri._ID);
                int idx_cliente        = c.getColumnIndex(PedidoUri._CLIENTE);
                int idx_bebida       = c.getColumnIndex(PedidoUri._BEBIDA);
                int idx_aperitivo    = c.getColumnIndex(PedidoUri._APERITIVO);

                do {
                    Pedido pedido = new Pedido();
                    pedido.setId_pedido(c.getLong(idx_idPedido));
                    pedido.setCliente(c.getString(idx_cliente));
                    pedido.setBebida(c.getString(idx_bebida));
                    pedido.setAperitivo(c.getString(idx_aperitivo));
                    listagem.add(pedido);

                } while (c.moveToNext());
            }

        } catch (Exception e) {
            Log.e("Erro", "Erro ao listar os pedidos: " + e.getMessage());
        }
        return listagem;
    }

    /**
     * Classe serve como CONTENT PROVIDER
     */
    public static final class PedidoUri implements BaseColumns {

        /**
         * Método Construtor
         */
        public PedidoUri(){}

        public static final String AUTHORITY = "vandin.nossocasanossobar.provider/pedidos";
        public static final Uri CONTEXT_URI = Uri.parse("content://" + AUTHORITY);
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.pedidos";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.pedidos";

        public static final String _ID = "id_pedido";
        public static final String _CLIENTE = "cliente";
        public static final String _BEBIDA = "bebida";
        public static final String _APERITIVO = "aperitivo";

        public static Uri getUriId(long id) {
            Uri uri = ContentUris.withAppendedId(CONTEXT_URI, id);
            return uri;
        }


    }
}
