package vandin.nossocasanossobar.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vandin.nossocasanossobar.R;

public class MainActivity extends AppCompatActivity {

    Button btClientes, btBebidas, btAperitivos, btPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btClientes = (Button) findViewById(R.id.btClientes);
        btBebidas = (Button) findViewById(R.id.btBebidas);
        btAperitivos = (Button) findViewById(R.id.btAperitivos);
        btPedidos = (Button) findViewById(R.id.btPedidos);

        btClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListaClienteActivity.class);
                startActivity(intent);
            }
        });

        btBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListaBebidaActivity.class);
                startActivity(intent);
            }
        });

        btAperitivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListaAperitivoActivity.class);
                startActivity(intent);
            }
        });

        btPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListaPedidoActivity.class);
                startActivity(intent);
            }
        });
    }
}
