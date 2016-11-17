package br.com.gustavo.agendashow.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.gustavo.agendashow.R;
import br.com.gustavo.agendashow.model.MusicaDao;
import br.com.gustavo.agendashow.pojo.Musica;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class CadMusicaActivity extends AppCompatActivity {

    private EditText txtNome, txtComentario;
    private Button btnCadastrar, btnEditar, btnVoltar;
    private Musica musica;
    private MusicaDao musicaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_musica);

        txtNome = (EditText)findViewById(R.id.txtNome);
        txtComentario = (EditText)findViewById(R.id.txtComentario);
        btnCadastrar = (Button)findViewById(R.id.btnCadastrar);
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnVoltar = (Button)findViewById(R.id.btnVoltar);

        musica = new Musica();
        musicaDao = MusicaDao.getInstance(this.getApplicationContext());

        Intent intent = getIntent();
        final long id_musica = intent.getLongExtra("id_musica", -1);
        String nome_musica = intent.getStringExtra("nome_musica");
        String comentario_musica = intent.getStringExtra("comentario_musica");

        txtComentario.setText(comentario_musica);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                musica.setNome(txtNome.getText().toString());
                musica.setComentario(txtComentario.getText().toString());

                long id = musicaDao.incluir(musica);

                Toast.makeText(CadMusicaActivity.this,
                        "ID "+id+" inserido!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CadMusicaActivity.this, ListaMusicaActivity.class);
                startActivity(intent);
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                musica.setNome(txtNome.getText().toString());
                musica.setComentario(txtComentario.getText().toString());

                long id = musicaDao.atualizar(musica, id_musica);

                Toast.makeText(CadMusicaActivity.this,
                        "ID "+id+" editado!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CadMusicaActivity.this, ListaMusicaActivity.class);
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadMusicaActivity.this, ListaMusicaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
