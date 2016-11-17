package br.com.gustavo.agendashow.activities;

/**
 * Created by Gustavo on 17/11/2016.
 */
/**
 * Created by Gustavo on 15/11/2016.
 */
public class MainActivity extends AppCompatActivity {



    EditText txtNome, txtSenha;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNome = (EditText) findViewById(R.id.txtNome);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        btnLogin = (Button) findViewById(R.id.btLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String senha = txtSenha.getText().toString();
                if(!txtNome.equals("gustavo") && senha.equals("123")){
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Usuario ou Senha incorretos!");

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
