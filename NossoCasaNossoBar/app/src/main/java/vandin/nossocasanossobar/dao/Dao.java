package vandin.nossocasanossobar.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Vandin on 01/11/2016.
 */

public class Dao {
    //Declaração dos Atributos
    protected String BANCO = "nossacasanossobarNEW";
    protected int VERSAO = 1;
    protected String TABELA;
    protected String[] COLUNAS;

    protected SQLiteDatabase db;
    protected SQLiteHelper dbHelper;

    /**
     * Método Construtor
     */
    protected Dao(Context ctx, String tabela,
                  String[] colunas) {
        try {
            dbHelper = SQLiteHelper.getInstance(ctx,
                    BANCO, VERSAO);
            db = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            Log.e("Erro", "Erro ao estabelecer " +
                    "comunicação com DB: "+e.getMessage());
        }
        TABELA = tabela;
        COLUNAS = colunas;
    }

    /**
     * Fecha a conexão com banco de dados
     */
    public void fecharConexao() {
        if (db != null)
            db.close();

        if (dbHelper != null)
            dbHelper.close();
    }

    /**
     * Inserir dados
     */
    public long inserir(ContentValues valores) {
        long id;
        try {
            id = db.insert(TABELA, null, valores);

        } catch (Exception e) {
            id = 0;
            Log.e("Erro", "Erro ao inserir: "+e.getMessage());
        }
        return id;
    }

    /**
     * Atualização de Dados
     */
    public int atualizar(ContentValues valores, String where,
                         String[] whereArgs) {
        int count;
        try {
            count = db.update(TABELA, valores, where, whereArgs);

        } catch (Exception e) {
            count = 0;
            Log.e("Erro", "Erro ao atualizar: "+e.getMessage());
        }
        return count;
    }

    /**
     * Excluir um determinado registro do banco
     */
    protected int deletar(String where, String[] whereArgs) {
        int count;
        try {
            count = db.delete(TABELA, where, whereArgs);

        } catch (Exception e) {
            count = 0;
            Log.e("Erro", "Erro ao tentar apagar dados: " + e.getMessage());
        }
        return count;
    }

    /**
     * Limpar a tabela
     */
    protected  int clearTable() {
        int count;
        try {
            count = db.delete(TABELA, null, null);

        } catch (Exception e) {
            count = 0;
            Log.e("Erro", "Ero ao tentar limpar tabela: " + e.getMessage());
        }
        return count;
    }

    /**
     * Retornam todos registros da tabela
     */
    protected Cursor getRegistros() {
        try {
            return db.query(TABELA, COLUNAS, null, null, null, null, null, null);

        } catch (Exception e) {
            Log.e("Erro", "Erro ao listar dados da tabela: "+e.getMessage());
            return  null;
        }
    }

    /**
     * Quantidade de registros da tabela
     */
    protected int getQtdeRegistros(String id) {
        int qtde;
        try {
            Cursor c = db.rawQuery("SELECT COUNT("+id+") as total FROM " + TABELA, null);

            if (!c.moveToNext() || c.getCount() < 1)
                qtde = 0;

            else
                qtde = c.getInt(c.getColumnIndex("total"));

            c.close();

        } catch (Exception e) {
            Log.e("Erro", "Erro ao tentar contar os registros da tabela: "+e.getMessage());
            qtde = 0;
        }
        return qtde;
    }
}
