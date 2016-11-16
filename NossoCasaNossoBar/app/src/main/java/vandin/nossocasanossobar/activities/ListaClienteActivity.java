package vandin.nossocasanossobar.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import vandin.nossocasanossobar.adapter.ListaClienteAdapter;
import vandin.nossocasanossobar.model.ClienteDao;
import vandin.nossocasanossobar.pojo.Cliente;
import vandin.nossocasanossobar.R;

public class ListaClienteActivity extends AppCompatActivity {

    private Button btCadastrar, btVoltar;
    private ListView listaClientes;
    private Cliente cliente;
    private ClienteDao clienteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);

        //Capturo os elementos
        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        btVoltar = (Button) findViewById(R.id.btVoltar);
        listaClientes = (ListView) findViewById(R.id.listaClientes);

        //Montar a Listagem
        clienteDao = ClienteDao.getInstance(this);
        final List<Cliente> clientesCadastrados = clienteDao.getTodosRegistros();

        if(clientesCadastrados == null){
            Toast.makeText(ListaClienteActivity.this, "Nenhum item foi cadastrado!", Toast.LENGTH_SHORT).show();
        }else {
            listaClientes.setAdapter(new ListaClienteAdapter(this, clientesCadastrados));
        }

        //Evento de Clique
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaClienteActivity.this, CadClienteActivity.class);
                startActivity(intent);
            }
        });

        listaClientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente cliente = (Cliente) parent.getItemAtPosition(position);
                String id_cliente = Long.toString(cliente.getId_cliente());
                String[] strArray = new String[] {id_cliente};
                clienteDao.deletar(strArray);
                Toast.makeText(ListaClienteActivity.this, "Cadastro excluído com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListaClienteActivity.this, ListaClienteActivity.class);
                finish();
                startActivity(getIntent());
                return true;
            }
        });

        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente cliente = (Cliente) parent.getItemAtPosition(position);
                String nome_cliente = cliente.getNome();
                String cpf_cliente = cliente.getCpf();
                String celular_cliente = cliente.getCelular();
                Intent intent = new Intent(ListaClienteActivity.this, CadClienteActivity.class);
                intent.putExtra("id_cliente", cliente.getId_cliente());
                intent.putExtra("nome_cliente", nome_cliente);
                intent.putExtra("cpf_cliente", cpf_cliente);
                intent.putExtra("celular_cliente", celular_cliente);
                startActivity(intent);
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaClienteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
