package br.com.gustavo.agendashow.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.gustavo.agendashow.R;
import br.com.gustavo.agendashow.adapter.ListaCidadeAdapter;
import br.com.gustavo.agendashow.model.CidadeDao;
import br.com.gustavo.agendashow.pojo.Cidade;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class ListaCidadeActivity extends AppCompatActivity {

    private Button btnCadastrar, btnVoltar;
    private ListView listaCidade;
    private Cidade cidade;
    private CidadeDao cidadeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cidade);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        listaCidade = (ListView) findViewById(R.id.listaCidade);

        cidadeDao = CidadeDao.getInstance(this);
        List<Cidade> cidadeCadastrada = cidadeDao.getTodosRegistros();

        if(cidadeCadastrada == null){
            Toast.makeText(ListaCidadeActivity.this, "Erro ao cadastrado!", Toast.LENGTH_SHORT).show();
        }else {
            listaCidade.setAdapter(new ListaCidadeAdapter(this, cidadeCadastrada));
        }

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaCidadeActivity.this, CadCidadeActivity.class);
                startActivity(intent);
            }
        });

        listaCidade.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Cidade cidade = (Cidade) parent.getItemAtPosition(position);
                String id_cidade = Long.toString(cidade.getId_cidade());
                String[] strArray = new String[] {id_cidade};
                cidadeDao.deletar(strArray);
                Toast.makeText(ListaCidadeActivity.this, "Excluído com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListaCidadeActivity.this, ListaCidadeActivity.class);
                finish();
                startActivity(getIntent());
                return true;
            }
        });

        listaCidade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cidade cidade = (Cidade) parent.getItemAtPosition(position);
                String nome_cidade = cidade.getNome();
                String bairro_cidade = cidade.getBairro();
                Intent intent = new Intent(ListaCidadeActivity.this, CadCidadeActivity.class);
                intent.putExtra("id_cidade", cidade.getId_cidade());
                intent.putExtra("nome_cidade", nome_cidade);
                intent.putExtra("bairro_cidade", bairro_cidade);
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaCidadeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}