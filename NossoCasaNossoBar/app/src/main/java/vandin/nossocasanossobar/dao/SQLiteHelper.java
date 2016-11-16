package vandin.nossocasanossobar.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Vandin on 01/11/2016.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    //Declaração de Atributos
    private static SQLiteHelper mInstance = null;

    /**
     * Método Construtor
     */
    private SQLiteHelper(Context ctx, String nomeBanco,
                         int versaoBanco) {
        super(ctx, nomeBanco, null, versaoBanco);
    }

    /**
     * Método - Design Pattern - Singleton
     */
    public static SQLiteHelper getInstance(
            Context ctx, String nomeBanco, int versaoBanco){
        if (mInstance == null) {
            try {
                mInstance = new SQLiteHelper(ctx, nomeBanco, versaoBanco);

            } catch (Exception e) {
                Log.e("Erro", "Erro ao criar objeto de " +
                        " conexão: "+e.getMessage());
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS clientes (" +
                    "id_cliente INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "cpf VARCHAR(15) NOT NULL, " +
                    "celular VARCHAR(20));");

            db.execSQL("CREATE TABLE IF NOT EXISTS bebidas (" +
                    "id_bebida INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome VARCHAR(50) NOT NULL, " +
                    "preco VARCHAR(50));");

            db.execSQL("CREATE TABLE IF NOT EXISTS aperitivos (" +
                    "id_aperitivo INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome VARCHAR(50) NOT NULL, " +
                    "preco VARCHAR(50));");

            db.execSQL("CREATE TABLE IF NOT EXISTS pedidos (" +
                    "id_pedido INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "cliente VARCHAR(100) NOT NULL, " +
                    "bebida VARCHAR(50), " +
                    "aperitivo VARCHAR(50));");

        } catch (Exception e) {
            Log.e("Erro", "Erro ao criar tabelas " +
                    "CLIENTES, BEBIDAS, APERITIVOS E PEDIDOS: "+e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAnterior,
                          int versaoAtual) {
        //Aqui vamos colocar scripts de atualização da base
    }
}
