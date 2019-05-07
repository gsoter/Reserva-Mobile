package a.android.reservamob;

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
        switch (view.getId()){

            case R.id.btnPesquisa:

        }

    }
}
