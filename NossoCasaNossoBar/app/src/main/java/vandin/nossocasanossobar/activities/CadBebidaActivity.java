package vandin.nossocasanossobar.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;
import vandin.nossocasanossobar.R;
import vandin.nossocasanossobar.model.BebidaDao;
import vandin.nossocasanossobar.pojo.Bebida;

public class CadBebidaActivity extends AppCompatActivity {

    private EditText txtNome, txtPreco;
    private Button btCadastrar, btEditar, btVoltar;
    private Bebida bebida;
    private BebidaDao bebidaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_bebida);

        //Capturar os elementos da tela
        txtNome = (EditText)findViewById(R.id.txtNome);
        txtPreco = (EditText)findViewById(R.id.txtPreco);
        btCadastrar = (Button)findViewById(R.id.btCadastrar);
        btEditar = (Button) findViewById(R.id.btEditar);
        btVoltar = (Button)findViewById(R.id.btVoltar);

        MaskEditTextChangedListener maskPreco = new MaskEditTextChangedListener("##.##", txtPreco);
        txtPreco.addTextChangedListener(maskPreco);

        bebida = new Bebida();
        bebidaDao = BebidaDao.getInstance(this.getApplicationContext());

        Intent it = getIntent();
        final long id_bebida = it.getLongExtra("id_bebida", -1);
        String nome_bebida = it.getStringExtra("nome_bebida");
        String preco_bebida = it.getStringExtra("preco_bebida");

        txtNome.setText(nome_bebida);
        txtPreco.setText(preco_bebida);

        //Cria o evento de click do botão salvar
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bebida.setNome(txtNome.getText().toString());
                bebida.setPreco(txtPreco.getText().toString());

                long id = bebidaDao.incluir(bebida);

                Toast.makeText(CadBebidaActivity.this,
                        "Cadastro nº "+id+" inserido com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CadBebidaActivity.this, ListaBebidaActivity.class);
                startActivity(intent);
            }
        });

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bebida.setNome(txtNome.getText().toString());
                bebida.setPreco(txtPreco.getText().toString());

                long id = bebidaDao.atualizar(bebida, id_bebida);

                Toast.makeText(CadBebidaActivity.this,
                        "Cadastro nº "+id+" editado com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CadBebidaActivity.this, ListaClienteActivity.class);
                startActivity(intent);
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadBebidaActivity.this, ListaBebidaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
