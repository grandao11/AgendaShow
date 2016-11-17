package br.com.gustavo.agendashow.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static SQLiteHelper mInstance = null;

    private SQLiteHelper(Context ctx, String nomeBanco,
                         int versaoBanco) {
        super(ctx, nomeBanco, null, versaoBanco);
    }

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
            db.execSQL("CREATE TABLE IF NOT EXISTS cidade (" +
                    "id_cidade INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "bairro VARCHAR(30));");

            db.execSQL("CREATE TABLE IF NOT EXISTS musica (" +
                    "id_musica INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome VARCHAR(50) NOT NULL, " +
                    "comentario VARCHAR(100));");

            db.execSQL("CREATE TABLE IF NOT EXISTS agenda (" +
                    "id_agenda INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "cidade VARCHAR(100) NOT NULL, " +
                    "musica VARCHAR(50));");

        } catch (Exception e) {
            Log.e("Erro", "Erro ao criar tabelas " +
                    "CIDADE, MUSICA, E AGENDA: "+e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAnterior,
                          int versaoAtual) {
    }
}
