package a.android.reservamob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnPesquisa = findViewById(R.id.btnPesquisa);
        btnPesquisa.setOnClickListener(this);

        final Button btnApto01 = findViewById(R.id.btnApto01);
        btnApto01.setOnClickListener(this);

        final Button btnApto02 = findViewById(R.id.btnApto02);
        btnApto02.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {

            case R.id.btnPesquisa:
//                ReservaDialogFragment reservaDialog = new ReservaDialogFragment();
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
}
