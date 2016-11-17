package br.com.gustavo.agendashow.activities;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class CadCidadeActivity extends AppCompatActivity {

    private EditText txtNome, txtBairro;
    private Button btnCadastrar, btnEditar, btnVoltar;
    private Cidade cidade;
    private CidadeDao cidadeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cidade);

        txtNome = (EditText)findViewById(R.id.txtNome);
        txtBairro = (EditText)findViewById(R.id.txtBairro);
        btCadastrar = (Button)findViewById(R.id.btCadastrar);
        btEditar = (Button) findViewById(R.id.btEditar);
        btVoltar = (Button)findViewById(R.id.btVoltar);

        cidade = new Cidade();
        cidade = CidadeDao.getInstance(this.getApplicationContext());

        Intent intent = getIntent();
        final long id_cidade = it.getLongExtra("id_cidade", -1);
        String nome_cidade = it.getStringExtra("nome_cidade");
        String bairro_cidade = it.getStringExtra("bairro_cidade");

        txtNome.setText(nome_cidade);
        txtBairro.setText(bairro_cidade);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cidade.setNome(txtNome.getText().toString());
                cidade.setBairro(txtBairro.getText().toString());

                long id = cidadeDao.incluir(cidade);

                Toast.makeText(CadCidadeActivity.this,
                        "ID "+id+" inserido!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CadCidadeActivity.this, ListaCidadeActivity.class);
                startActivity(intent);
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cidade.setNome(txtNome.getText().toString());
                cidade.setBairro(txtBairro.getText().toString());

                long id = cidadeDao.atualizar(cidade, id_cidade);

                Toast.makeText(CadCidadeActivity.this,
                        "ID "+id+" editado!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CadCidadeActivity.this, ListaCidadeActivity.class);
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadCidadeActivity.this, ListaCidadeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
