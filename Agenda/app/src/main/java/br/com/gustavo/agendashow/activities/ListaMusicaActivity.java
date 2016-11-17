package br.com.gustavo.agendashow.activities;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class ListaMusicaActivity extends AppCompatActivity {

    private Button btnCadastrar, btnVoltar;
    private ListView listaBebidas;
    private Musica musica;
    private MusicaDao musicaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_musica);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        listaMusica = (ListView) findViewById(R.id.listaMusica);

        musicaDao = MusicaDao.getInstance(this);
        List<Musica> musicaCadastrada = musicaDao.getTodosRegistros();

        if(musicaCadastrada == null){
            Toast.makeText(ListaMusicaActivity.this, "Nenhum item foi cadastrado!", Toast.LENGTH_SHORT).show();
        }else {
            listaMusica.setAdapter(new ListaMusicaAdapter(this, musicaCadastrada));
        }

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaMusicaActivity.this, CadMusicaActivity.class);
                startActivity(intent);
            }
        });

        listaMusica.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Musica musica = (Musica) parent.getItemAtPosition(position);
                String id_musica = Long.toString(musica.getId_musica());
                String[] strArray = new String[] {id_musica};
                musicaDao.deletar(strArray);
                Toast.makeText(ListaMusicaActivity.this, "Cadastro excluído!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListaMusicaActivity.this, ListaMusicaActivity.class);
                finish();
                startActivity(getIntent());
                return true;
            }
        });

        listaMusica.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Musica musica = (Musica) parent.getItemAtPosition(position);
                String nome_musica = musica.getNome();
                String preco_musica = musica.getPreco();
                Intent intent = new Intent(ListaMusicaActivity.this, CadMusicaActivity.class);
                intent.putExtra("id_musica", musica.getId_musica());
                intent.putExtra("nome_musica", nome_musica);
                intent.putExtra("comentario_musica", comentario_musica);
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaMusicaActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
