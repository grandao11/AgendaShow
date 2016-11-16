package vandin.nossocasanossobar.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vandin.nossocasanossobar.R;

public class LoginActivity extends AppCompatActivity {

    EditText txtNome, txtSenha;
    Button btEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtNome = (EditText) findViewById(R.id.txtNome);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        btEntrar = (Button) findViewById(R.id.btEntrar);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String senha = txtSenha.getText().toString();
                if(!txtNome.equals("EvandroFelipe") && senha.equals("123")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Usuário ou senha inválidos!!");

                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            txtNome.setText("");
                            txtSenha.setText("");
                            onResume();
                        }
                    });

                    builder.show();
                }
            }
        });
    }
}
