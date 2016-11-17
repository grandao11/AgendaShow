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
import br.com.gustavo.agendashow.adapter.ListaAgendaAdapter;
import br.com.gustavo.agendashow.model.AgendaDao;
import br.com.gustavo.agendashow.pojo.Agenda;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class ListaAgendaActivity extends AppCompatActivity{
    private Button btnCadastrar, btnVoltar;
    private ListView listaAgenda;
    private Agenda agenda;
    private AgendaDao agendaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_agenda);

        btnCadastrar = (Button) findViewById(R.id.btCadastrar);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        listaAgenda = (ListView) findViewById(R.id.listaAgenda);

        agendaDao = AgendaDao.getInstance(this);
        List<Agenda> agendaCadastrada = agendaDao.getTodosRegistros();

        if(agendaCadastrada == null){
            Toast.makeText(ListaAgendaActivity.this, "Nenhum item foi cadastrado!", Toast.LENGTH_SHORT).show();
        }else{
            listaAgenda.setAdapter(new ListaAgendaAdapter(this, agendaCadastrada));
        }

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaAgendaActivity.this, CadAgendaActivity.class);
                startActivity(intent);
            }
        });

        listaAgenda.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Agenda pedido = (Agenda) parent.getItemAtPosition(position);
                String id_pedido = Long.toString(pedido.getId_agenda());
                String[] strArray = new String[] {id_pedido};
                agendaDao.deletar(strArray);
                Toast.makeText(ListaAgendaActivity.this, "Cadastro excluído!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListaAgendaActivity.this, ListaAgendaActivity.class);
                finish();
                startActivity(getIntent());
                return true;
            }
        });

        listaAgenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Agenda agenda = (Agenda) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListaAgendaActivity.this, CadAgendaActivity.class);
                intent.putExtra("id_pedido", agenda.getId_agenda());
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaAgendaActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
