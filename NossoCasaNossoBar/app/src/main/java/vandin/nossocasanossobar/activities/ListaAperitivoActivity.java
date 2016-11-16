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
import vandin.nossocasanossobar.adapter.ListaAperitivoAdapter;
import vandin.nossocasanossobar.model.AperitivoDao;
import vandin.nossocasanossobar.pojo.Aperitivo;

public class ListaAperitivoActivity extends AppCompatActivity {

    private Button btCadastrar, btVoltar;
    private ListView listaAperitivos;
    private Aperitivo aperitivo;
    private AperitivoDao aperitivoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aperitivo);

        //Capturo os elementos
        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        btVoltar = (Button) findViewById(R.id.btVoltar);
        listaAperitivos = (ListView) findViewById(R.id.listaAperitivos);

        //Montar a Listagem
        aperitivoDao = AperitivoDao.getInstance(this);
        List<Aperitivo> aperitivoCadastrados = aperitivoDao.getTodosRegistros();

        if(aperitivoCadastrados == null){
            Toast.makeText(ListaAperitivoActivity.this, "Nenhum item foi cadastrado!", Toast.LENGTH_SHORT).show();
        }else {
            listaAperitivos.setAdapter(new ListaAperitivoAdapter(this, aperitivoCadastrados));
        }

        //Evento de Clique
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaAperitivoActivity.this, CadAperitivoActivity.class);
                startActivity(intent);
            }
        });

        listaAperitivos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Aperitivo aperitivo = (Aperitivo) parent.getItemAtPosition(position);
                String id_aperitivo = Long.toString(aperitivo.getId_aperitivo());
                String[] strArray = new String[] {id_aperitivo};
                aperitivoDao.deletar(strArray);
                Toast.makeText(ListaAperitivoActivity.this, "Cadastro excluído com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListaAperitivoActivity.this, ListaAperitivoActivity.class);
                finish();
                startActivity(getIntent());
                return true;
            }
        });

        listaAperitivos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aperitivo aperitivo = (Aperitivo) parent.getItemAtPosition(position);
                String nome_aperitivo = aperitivo.getNome();
                String preco_aperitivo = aperitivo.getPreco();
                Intent intent = new Intent(ListaAperitivoActivity.this, CadAperitivoActivity.class);
                intent.putExtra("id_aperitivo", aperitivo.getId_aperitivo());
                intent.putExtra("nome_aperitivo", nome_aperitivo);
                intent.putExtra("preco_aperitivo", preco_aperitivo);
                startActivity(intent);
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaAperitivoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
