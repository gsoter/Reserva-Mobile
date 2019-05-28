package a.android.reservamob.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import a.android.reservamob.R;
import a.android.reservamob.model.Database;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText textLogin;
    private EditText textSenha;
    private Button btnCadastrar;
    private Button btnEntrar;
    private int count = 3;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new Database(this);
        textLogin = findViewById(R.id.txt_login);
        textSenha = findViewById(R.id.txt_senha);
        btnCadastrar = findViewById(R.id.btnCadastro);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnCadastrar.setOnClickListener(this);
        btnEntrar.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        Intent i;
        String login = textLogin.getText().toString();
        String senha = textSenha.getText().toString();

        switch (view.getId()) {
            case R.id.btnCadastro:
                i = new Intent(LoginActivity.this, CadastrarActivity.class);
                startActivity(i);
                break;

            case R.id.btnEntrar:
                if (dbHelper.consultarUsuario(login, senha) != null) {
                    Toast.makeText(getApplicationContext(), "Redirecionando...", Toast.LENGTH_SHORT).show();
                    i = new Intent(LoginActivity.this, PerfilActivity.class);

                } else {
                    Toast.makeText(getApplicationContext(), "Usuário e/ou Senha inválidos " +
                            "\n" + "Número máximo de tentativas = " + count, Toast.LENGTH_SHORT).show();
                    count--;
                    if (count == 0) {
                        Toast.makeText(getApplicationContext(), "Usuário e/ou Senha inválidos" + "\n" + "Número máximo de tentativas = " + count, Toast.LENGTH_SHORT).show();
                        btnEntrar.setEnabled(false);
                        break;
                    }
                }
        }
    }
/*
    public boolean verificarCliente(Cliente cliente) {
        if ((!(cliente.getUsuario().isEmpty() || cliente.getUsuario() == null)) ||
                (!(cliente == null)) ||
                (!(cliente.getSenha().isEmpty() || cliente.getSenha() == null))) ;
        return true;
    }

    public boolean verificarUsuarioSenha(String usuario, String senha) {
        if ((!usuario.isEmpty() || usuario != null) || (!senha.isEmpty() || senha != null)) ;
        return true;
    }

    public boolean pesquisarUsuario(String usuario, String senha) {

        if (verificarUsuarioSenha(usuario, senha))
        cliente = new Cliente();
        cliente = dbHelper.consultarUsuario(usuario, senha);
        return (verificarCliente(cliente));
    }*/


}
