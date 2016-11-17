package br.com.gustavo.agendashow.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.com.gustavo.agendashow.R;
import br.com.gustavo.agendashow.adapter.SpinnerCidadeAdapter;
import br.com.gustavo.agendashow.adapter.SpinnerMusicaAdapter;
import br.com.gustavo.agendashow.model.AgendaDao;
import br.com.gustavo.agendashow.model.CidadeDao;
import br.com.gustavo.agendashow.model.MusicaDao;
import br.com.gustavo.agendashow.pojo.Agenda;
import br.com.gustavo.agendashow.pojo.Cidade;
import br.com.gustavo.agendashow.pojo.Musica;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class CadAgendaActivity extends AppCompatActivity {

    private Spinner SpMusica, SpCidade;
    private Button btnCadastrar, btnEditar, btnVoltar;
    private Agenda agenda;
    private AgendaDao agendaDao;
    private Musica musica;
    private MusicaDao musicaDao;
    private Cidade cidade;
    private CidadeDao cidadeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_agenda);

        SpMusica = (Spinner)findViewById(R.id.SpMusica);
        SpCidade = (Spinner)findViewById(R.id.SpCidade);
        btnCadastrar = (Button)findViewById(R.id.btnCadastrar);
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnVoltar = (Button)findViewById(R.id.btnVoltar);

        agenda = new Agenda();
        agendaDao = AgendaDao.getInstance(this.getApplicationContext());

        Intent intent = getIntent();
        final long id_agenda = intent.getLongExtra("id_agenda", -1);

        musicaDao = MusicaDao.getInstance(this);
        final List<Musica> musicaCadastrada = musicaDao.getTodosRegistros();
        if(musicaCadastrada == null){
            Toast.makeText(CadAgendaActivity.this, "Não há musica cadastrada!", Toast.LENGTH_SHORT).show();
        }else {
            SpMusica.setAdapter(new SpinnerMusicaAdapter(this, musicaCadastrada));
        }

        cidadeDao = CidadeDao.getInstance(this);
        final List<Cidade> cidadeCadastrada = cidadeDao.getTodosRegistros();
        if(cidadeCadastrada == null){
            Toast.makeText(CadAgendaActivity.this, "Não há cidade cadastrada!", Toast.LENGTH_SHORT).show();
        }else {
            SpCidade.setAdapter(new SpinnerCidadeAdapter(this, cidadeCadastrada));
        }

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musica = (Musica) SpMusica.getSelectedItem();
                cidade = (Cidade) SpCidade.getSelectedItem();
                agenda.setMusica(musica.getNome());
                agenda.setCidade(cidade.getNome());

                long id = agendaDao.incluir(agenda);

                Toast.makeText(CadAgendaActivity.this, "Cadastro nº "+id+" inserido com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CadAgendaActivity.this, ListaAgendaActivity.class);
                startActivity(intent);
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musica = (Musica) SpMusica.getSelectedItem();
                cidade = (Cidade) SpCidade.getSelectedItem();
                agenda.setMusica(musica.getNome());
                agenda.setCidade(cidade.getNome());
                agenda.setMusica(SpMusica.getSelectedItem().toString());
                agenda.setCidade(SpCidade.getSelectedItem().toString());

                long id = agendaDao.atualizar(agenda, id_agenda);

                Toast.makeText(CadAgendaActivity.this,
                        "Cadastro nº "+id+" editado com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CadAgendaActivity.this, ListaAgendaActivity.class);
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadAgendaActivity.this, ListaAgendaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
