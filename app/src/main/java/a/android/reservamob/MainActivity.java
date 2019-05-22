package a.android.reservamob;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import a.android.reservamob.ReservaDialogFragment.ReservaDialogListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ReservaDialogListener {
    Button btnPesquisa;
    Button btnApto01;
    Button btnApto02;
    String txtDtEntrada;
    String txtDtSaida;
    BottomNavigationView bnv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPesquisa = findViewById(R.id.btnPesquisa);
        btnPesquisa.setOnClickListener(this);

        btnApto01 = findViewById(R.id.btnApto01);
        btnApto01.setOnClickListener(this);

        btnApto02 = findViewById(R.id.btnApto02);
        btnApto02.setOnClickListener(this);

        bnv = findViewById(R.id.bottom_nav);
        bnv.setOnNavigationItemSelectedListener(navListener);
    }

    public BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_menu:
                    break;
                case R.id.nav_home:
                    Intent i = new Intent();
                    startActivity(i);
                    break;
                case R.id.nav_user:
                    break;
            }
            return true;
        }
    };

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.btnBemVindo:

            case R.id.btnPesquisa:
                openDialog();
            break;

            case R.id.btnApto01:
                i = new Intent(this, AptoActivity.class);
                i.putExtra("apto01", 01);
                startActivity(i);
                break;

            case R.id.btnApto02:
                i = new Intent(this, AptoActivity.class);
                i.putExtra("apto02", 02);
                startActivity(i);
                break;
        }
    }

    @Override
    public void consultar(String dtEntrada, String dtSaida) {
        this.txtDtEntrada = dtEntrada;
        this.txtDtSaida = dtSaida;
        Intent intent = new Intent(this, BuscarReservaActivity.class);
        intent.putExtra("dtEntrada", txtDtEntrada);
        intent.putExtra("dtSaida", txtDtSaida);
        startActivity(intent);
    }

    private void openDialog() {
        ReservaDialogFragment dialog = new ReservaDialogFragment();
        dialog.show(getSupportFragmentManager(), "Buscar Reserva");
    }
}
