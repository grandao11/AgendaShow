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
import vandin.nossocasanossobar.model.AperitivoDao;
import vandin.nossocasanossobar.pojo.Aperitivo;

public class CadAperitivoActivity extends AppCompatActivity {

    private EditText txtNome, txtPreco;
    private Button btCadastrar, btEditar, btVoltar;
    private Aperitivo aperitivo;
    private AperitivoDao aperitivoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_aperitivo);

        //Capturar os elementos da tela
        txtNome = (EditText)findViewById(R.id.txtNome);
        txtPreco = (EditText)findViewById(R.id.txtPreco);
        btCadastrar = (Button)findViewById(R.id.btCadastrar);
        btEditar = (Button) findViewById(R.id.btEditar);
        btVoltar = (Button)findViewById(R.id.btVoltar);

        MaskEditTextChangedListener maskPreco = new MaskEditTextChangedListener("##.##", txtPreco);
        txtPreco.addTextChangedListener(maskPreco);

        aperitivo = new Aperitivo();
        aperitivoDao = AperitivoDao.getInstance(this.getApplicationContext());

        Intent it = getIntent();
        final long id_aperitivo = it.getLongExtra("id_aperitivo", -1);
        String nome_aperitivo = it.getStringExtra("nome_aperitivo");
        String preco_aperitivo = it.getStringExtra("preco_aperitivo");

        txtNome.setText(nome_aperitivo);
        txtPreco.setText(preco_aperitivo);

        //Cria o evento de click do botão salvar
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aperitivo.setNome(txtNome.getText().toString());
                aperitivo.setPreco(txtPreco.getText().toString());

                long id = aperitivoDao.incluir(aperitivo);

                Toast.makeText(CadAperitivoActivity.this,
                        "Cadastro nº "+id+" inserido com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CadAperitivoActivity.this, ListaAperitivoActivity.class);
                startActivity(intent);
            }
        });

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aperitivo.setNome(txtNome.getText().toString());
                aperitivo.setPreco(txtPreco.getText().toString());

                long id = aperitivoDao.atualizar(aperitivo, id_aperitivo);

                Toast.makeText(CadAperitivoActivity.this,
                        "Cadastro nº "+id+" editado com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CadAperitivoActivity.this, ListaAperitivoActivity.class);
                startActivity(intent);
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadAperitivoActivity.this, ListaAperitivoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
