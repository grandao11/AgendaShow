package br.com.gustavo.agendashow.model;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class CidadeDao extends Dao {

    private static CidadeDao mInstance = null;

    private CidadeDao(Context ctx, String tabela, String[] colunas) {
        super(ctx, tabela, colunas);
    }

    public static CidadeDao getInstance(Context ctx) {
        if (mInstance == null) {
            String[] colunas = new String[] {
                    CidadeUri._ID,
                    CidadeUri._NOME,
                    CidadeUri._BAIRRO
            };
            mInstance = new CidadeDao(ctx, "Cidade", colunas);
        }
        return mInstance;
    }

    public void fecharConexao() {
        super.fecharConexao();
        mInstance = null;
    }

    private ContentValues getCampos(Cidade objeto) {
        ContentValues cv = new ContentValues();
        cv.put(CidadeUri._NOME, objeto.getNome());
        cv.put(CidadeUri._BAIRRO, objeto.getBairro());

        return cv;
    }

    public long incluir(Cidade objeto) {
        long id = 0;
        ContentValues cv = getCampos(objeto);
        id = inserir(cv);
        return id;
    }

    public long atualizar(Cidade objeto, long id) {
        ContentValues cv = getCampos(objeto);
        if (id > 0) {
            String _id = String.valueOf(id);
            String where = CidadeUri._ID + "=?";
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
        deletar("id_cidade = ?", id);
    }

    public List<Cidade> getTodosRegistros() {
        List<Cidade> listagem = null;
        try {
            Cursor c = db.rawQuery("SELECT * FROM cidade", null);
            if (c.moveToFirst()) {
                listagem = new ArrayList<Cidade>();

                int idx_idCidade    = c.getColumnIndex(CidadeUri._ID);
                int idx_nome        = c.getColumnIndex(CidadeUri._NOME);
                int idx_bairro      = c.getColumnIndex(CidadeUri._BAIRRO);

                do {
                    Cidade cidade = new Cidade();
                    cidade.setId_cidade(c.getLong(idx_idCidade));
                    cidade.setNome(c.getString(idx_nome));
                    cidade.setBairro(c.getString(idx_bairro));
                    listagem.add(cidade);

                } while (c.moveToNext());
            }

        } catch (Exception e) {
            Log.e("Erro", "Erro ao listar cidade: " + e.getMessage());
        }
        return listagem;
    }

    public static final class CidadeUri implements BaseColumns {

        public CidadeUri(){}

        public static final String AUTHORITY = "vandin.nossocasanossobar.provider/cidade";
        public static final Uri CONTEXT_URI = Uri.parse("content://" + AUTHORITY);
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.cidade";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.cidade";

        public static final String _ID     = "id_cidade";
        public static final String _NOME   = "nome";
        public static final String _BAIRRO = "bairro";

        public static Uri getUriId(long id) {
            Uri uri = ContentUris.withAppendedId(CONTEXT_URI, id);
            return uri;
        }


    }
}
