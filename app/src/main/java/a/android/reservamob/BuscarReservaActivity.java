package a.android.reservamob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BuscarReservaActivity extends AppCompatActivity{

    private TextView txtDtChegada;
    private TextView txtDtSaida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_reserva);

        txtDtChegada = findViewById(R.id.txtDtChegada);
        txtDtSaida = findViewById(R.id.txtDtSaida);
    }



}
