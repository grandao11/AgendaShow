package vandin.nossocasanossobar.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;
import vandin.nossocasanossobar.model.ClienteDao;
import vandin.nossocasanossobar.pojo.Cliente;
import vandin.nossocasanossobar.R;


public class CadClienteActivity extends AppCompatActivity {

    public EditText txtNome, txtCpf, txtCelular;
    private Button btCadastrar, btEditar, btVoltar;
    private Cliente cliente;
    private ClienteDao clienteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cliente);

        //Capturar os elementos da tela
        txtNome = (EditText)findViewById(R.id.txtNome);
        txtCpf = (EditText)findViewById(R.id.txtCpf);
        txtCelular = (EditText)findViewById(R.id.txtCelular);
        btCadastrar = (Button)findViewById(R.id.btCadastrar);
        btEditar = (Button) findViewById(R.id.btEditar);
        btVoltar = (Button)findViewById(R.id.btVoltar);

        MaskEditTextChangedListener maskCPF = new MaskEditTextChangedListener("###.###.###-##", txtCpf);
        txtCpf.addTextChangedListener(maskCPF);

        MaskEditTextChangedListener maskCEL = new MaskEditTextChangedListener("(##)#####-####", txtCelular);
        txtCelular.addTextChangedListener(maskCEL);

        cliente = new Cliente();
        clienteDao = ClienteDao.getInstance(this.getApplicationContext());

        Intent it = getIntent();
        final long id_cliente = it.getLongExtra("id_cliente", -1);
        String nome_cliente = it.getStringExtra("nome_cliente");
        String cpf_cliente = it.getStringExtra("cpf_cliente");
        String celular_cliente = it.getStringExtra("celular_cliente");

        txtNome.setText(nome_cliente);
        txtCpf.setText(cpf_cliente);
        txtCelular.setText(celular_cliente);

        //Cria o evento de click do botão salvar
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cliente.setNome(txtNome.getText().toString());
                cliente.setCpf(txtCpf.getText().toString());
                cliente.setCelular(txtCelular.getText().toString());

                long id = clienteDao.incluir(cliente);

                Toast.makeText(CadClienteActivity.this,
                        "Cadastro nº "+id+" inserido com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CadClienteActivity.this, ListaClienteActivity.class);
                startActivity(intent);
            }
        });

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cliente.setNome(txtNome.getText().toString());
                cliente.setCpf(txtCpf.getText().toString());
                cliente.setCelular(txtCelular.getText().toString());

                long id = clienteDao.atualizar(cliente, id_cliente);

                Toast.makeText(CadClienteActivity.this,
                        "Cadastro nº "+id+" editado com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CadClienteActivity.this, ListaClienteActivity.class);
                startActivity(intent);
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadClienteActivity.this, ListaClienteActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
