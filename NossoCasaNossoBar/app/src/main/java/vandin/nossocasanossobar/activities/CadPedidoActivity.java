package vandin.nossocasanossobar.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vandin.nossocasanossobar.R;
import vandin.nossocasanossobar.adapter.SpinnerAperitivoAdapter;
import vandin.nossocasanossobar.adapter.SpinnerBebidaAdapter;
import vandin.nossocasanossobar.adapter.SpinnerClienteAdapter;
import vandin.nossocasanossobar.model.AperitivoDao;
import vandin.nossocasanossobar.model.BebidaDao;
import vandin.nossocasanossobar.model.ClienteDao;
import vandin.nossocasanossobar.model.PedidoDao;
import vandin.nossocasanossobar.pojo.Aperitivo;
import vandin.nossocasanossobar.pojo.Bebida;
import vandin.nossocasanossobar.pojo.Cliente;
import vandin.nossocasanossobar.pojo.Pedido;

public class CadPedidoActivity extends AppCompatActivity {

    private Spinner SpCliente, SpBebida, SpAperitivo;
    private Button btCadastrar, btEditar, btVoltar;
    private Pedido pedido;
    private PedidoDao pedidoDao;
    private Cliente cliente;
    private ClienteDao clienteDao;
    private Bebida bebida;
    private BebidaDao bebidaDao;
    private Aperitivo aperitivo;
    private AperitivoDao aperitivoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_pedido);

        //Capturar os elementos da tela
        SpCliente = (Spinner)findViewById(R.id.SpCliente);
        SpBebida = (Spinner)findViewById(R.id.SpBebida);
        SpAperitivo = (Spinner)findViewById(R.id.SpAperitivo);
        btCadastrar = (Button)findViewById(R.id.btCadastrar);
        btEditar = (Button) findViewById(R.id.btEditar);
        btVoltar = (Button)findViewById(R.id.btVoltar);

        pedido = new Pedido();
        pedidoDao = PedidoDao.getInstance(this.getApplicationContext());

        Intent it = getIntent();
        final long id_pedido = it.getLongExtra("id_pedido", -1);

        //Montar a Listagem
        clienteDao = ClienteDao.getInstance(this);
        final List<Cliente> clientesCadastrados = clienteDao.getTodosRegistros();
        if(clientesCadastrados == null){
            Toast.makeText(CadPedidoActivity.this, "Não há clientes cadastrados!", Toast.LENGTH_SHORT).show();
        }else {
            SpCliente.setAdapter(new SpinnerClienteAdapter(this, clientesCadastrados));
        }

        bebidaDao = BebidaDao.getInstance(this);
        final List<Bebida> bebidasCadastradas = bebidaDao.getTodosRegistros();
        if(bebidasCadastradas == null){
            Toast.makeText(CadPedidoActivity.this, "Não há bebidas cadastradas!", Toast.LENGTH_SHORT).show();
        }else {
            SpBebida.setAdapter(new SpinnerBebidaAdapter(this, bebidasCadastradas));
        }

        aperitivoDao = AperitivoDao.getInstance(this);
        final List<Aperitivo> aperitivosCadastrados = aperitivoDao.getTodosRegistros();
        if(aperitivosCadastrados == null){
            Toast.makeText(CadPedidoActivity.this, "Não há aperitivos cadastrados!", Toast.LENGTH_SHORT).show();
        }else {
            SpAperitivo.setAdapter(new SpinnerAperitivoAdapter(this, aperitivosCadastrados));
        }

        //Cria o evento de click do botão salvar
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cliente = (Cliente) SpCliente.getSelectedItem();
                bebida = (Bebida) SpBebida.getSelectedItem();
                aperitivo = (Aperitivo) SpAperitivo.getSelectedItem();
                pedido.setCliente(cliente.getNome());
                pedido.setBebida(bebida.getNome());
                pedido.setAperitivo(aperitivo.getNome());

                long id = pedidoDao.incluir(pedido);

                Toast.makeText(CadPedidoActivity.this, "Cadastro nº "+id+" inserido com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CadPedidoActivity.this, ListaPedidoActivity.class);
                startActivity(intent);
            }
        });

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cliente = (Cliente) SpCliente.getSelectedItem();
                bebida = (Bebida) SpBebida.getSelectedItem();
                aperitivo = (Aperitivo) SpAperitivo.getSelectedItem();
                pedido.setCliente(cliente.getNome());
                pedido.setBebida(bebida.getNome());
                pedido.setAperitivo(aperitivo.getNome());
                pedido.setCliente(SpCliente.getSelectedItem().toString());
                pedido.setBebida(SpBebida.getSelectedItem().toString());
                pedido.setAperitivo(SpAperitivo.getSelectedItem().toString());

                long id = pedidoDao.atualizar(pedido, id_pedido);

                Toast.makeText(CadPedidoActivity.this,
                        "Cadastro nº "+id+" editado com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CadPedidoActivity.this, ListaPedidoActivity.class);
                startActivity(intent);
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadPedidoActivity.this, ListaPedidoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
