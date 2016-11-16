package vandin.nossocasanossobar.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import vandin.nossocasanossobar.R;
import vandin.nossocasanossobar.adapter.ListaBebidaAdapter;
import vandin.nossocasanossobar.model.BebidaDao;
import vandin.nossocasanossobar.pojo.Bebida;

public class ListaBebidaActivity extends AppCompatActivity {

    private Button btCadastrar, btVoltar;
    private ListView listaBebidas;
    private Bebida bebida;
    private BebidaDao bebidaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bebida);

        //Capturo os elementos
        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        btVoltar = (Button) findViewById(R.id.btVoltar);
        listaBebidas = (ListView) findViewById(R.id.listaBebidas);

        //Montar a Listagem
        bebidaDao = BebidaDao.getInstance(this);
        List<Bebida> bebidasCadastradas = bebidaDao.getTodosRegistros();

        if(bebidasCadastradas == null){
            Toast.makeText(ListaBebidaActivity.this, "Nenhum item foi cadastrado!", Toast.LENGTH_SHORT).show();
        }else {
            listaBebidas.setAdapter(new ListaBebidaAdapter(this, bebidasCadastradas));
        }

        //Evento de Clique
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaBebidaActivity.this, CadBebidaActivity.class);
                startActivity(intent);
            }
        });

        listaBebidas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Bebida bebida = (Bebida) parent.getItemAtPosition(position);
                String id_bebida = Long.toString(bebida.getId_bebida());
                String[] strArray = new String[] {id_bebida};
                bebidaDao.deletar(strArray);
                Toast.makeText(ListaBebidaActivity.this, "Cadastro excluído com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListaBebidaActivity.this, ListaBebidaActivity.class);
                finish();
                startActivity(getIntent());
                return true;
            }
        });

        listaBebidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bebida bebida = (Bebida) parent.getItemAtPosition(position);
                String nome_bebida = bebida.getNome();
                String preco_bebida = bebida.getPreco();
                Intent intent = new Intent(ListaBebidaActivity.this, CadBebidaActivity.class);
                intent.putExtra("id_bebida", bebida.getId_bebida());
                intent.putExtra("nome_bebida", nome_bebida);
                intent.putExtra("preco_bebida", preco_bebida);
                startActivity(intent);
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaBebidaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
