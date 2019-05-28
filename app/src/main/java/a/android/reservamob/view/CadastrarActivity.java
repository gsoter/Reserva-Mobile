package a.android.reservamob.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import a.android.reservamob.R;
import a.android.reservamob.model.Cliente;
import a.android.reservamob.model.Database;

public class CadastrarActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNome;
    private EditText txtCpf;
    private EditText txtUsuario;
    private EditText txtSenha;
    private Button btnCadastrar;
    private Button btnCancelar;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        dbHelper = new Database(getApplicationContext());
        btnCancelar = findViewById(R.id.btnCancelar);
        btnCadastrar = findViewById(R.id.btnCadastrarUsuario);
        txtNome = findViewById(R.id.txtNome);
        txtCpf = findViewById(R.id.txtCpf);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtSenha = findViewById(R.id.txtSenha);

        btnCadastrar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnCadastrarUsuario:
                Cliente c;
                try {
                    c = new Cliente();
                    c.setNome(txtNome.getText().toString());
                    c.setCpf(txtCpf.getText().toString());
                    c.setUsuario(txtUsuario.getText().toString());
                    c.setSenha(txtSenha.getText().toString());
                    if (verificarCliente(c)) {
                        if (dbHelper.inserirCliente(c)) {
                            Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Preencha todos os campos para cadastrar", Toast.LENGTH_SHORT).show();
                            Log.e(getApplicationContext().toString(), "Erro ao cadastrar cliente");
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnCancelar:
                finish();
        }

    }

    public boolean verificarCliente(Cliente cliente) {
        return !((cliente == null ||
                cliente.getNome().isEmpty() ||
                cliente.getCpf().isEmpty() ||
                cliente.getUsuario().isEmpty() ||
                cliente.getSenha().isEmpty()) ||
                (cliente.getUsuario() == null || cliente.getSenha() == null));
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
