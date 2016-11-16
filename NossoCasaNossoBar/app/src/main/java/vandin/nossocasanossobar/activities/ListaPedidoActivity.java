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
import vandin.nossocasanossobar.adapter.ListaPedidoAdapter;
import vandin.nossocasanossobar.model.PedidoDao;
import vandin.nossocasanossobar.pojo.Pedido;

public class ListaPedidoActivity extends AppCompatActivity {

    private Button btCadastrar, btVoltar;
    private ListView listaPedidos;
    private Pedido pedido;
    private PedidoDao pedidoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedido);

        //Capturo os elementos
        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        btVoltar = (Button) findViewById(R.id.btVoltar);
        listaPedidos = (ListView) findViewById(R.id.listaPedidos);

        //Montar a Listagem
        pedidoDao = PedidoDao.getInstance(this);
        List<Pedido> pedidosCadastrados = pedidoDao.getTodosRegistros();

        if(pedidosCadastrados == null){
            Toast.makeText(ListaPedidoActivity.this, "Nenhum item foi cadastrado!", Toast.LENGTH_SHORT).show();
        }else{
            listaPedidos.setAdapter(new ListaPedidoAdapter(this, pedidosCadastrados));
        }

        //Evento de Clique
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaPedidoActivity.this, CadPedidoActivity.class);
                startActivity(intent);
            }
        });

        listaPedidos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Pedido pedido = (Pedido) parent.getItemAtPosition(position);
                String id_pedido = Long.toString(pedido.getId_pedido());
                String[] strArray = new String[] {id_pedido};
                pedidoDao.deletar(strArray);
                Toast.makeText(ListaPedidoActivity.this, "Cadastro excluído com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListaPedidoActivity.this, ListaPedidoActivity.class);
                finish();
                startActivity(getIntent());
                return true;
            }
        });

        listaPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pedido pedido = (Pedido) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListaPedidoActivity.this, CadPedidoActivity.class);
                intent.putExtra("id_pedido", pedido.getId_pedido());
                startActivity(intent);
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaPedidoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
