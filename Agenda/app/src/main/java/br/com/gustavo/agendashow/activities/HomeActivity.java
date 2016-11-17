package br.com.gustavo.agendashow.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import br.com.gustavo.agendashow.R;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class HomeActivity extends AppCompatActivity {

    Button btnAgenda, btnCidade, btnMusica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnAgenda = (Button) findViewById(R.id.btnAgenda);
        btnCidade = (Button) findViewById(R.id.btnCidade);
        btnMusica = (Button) findViewById(R.id.btnMusica);

        btnAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ListaAgendaActivity.class);
                startActivity(intent);
            }
        });

        btnCidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ListaCidadeActivity.class);
                startActivity(intent);
            }
        });

        btnMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ListaMusicaActivity.class);
                startActivity(intent);
            }
        });
    }
}
