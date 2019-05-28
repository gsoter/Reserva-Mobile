package a.android.reservamob.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import a.android.reservamob.R;

public class BuscarReservaActivity extends AppCompatActivity {

   ListView listBusca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_reserva);

        listBusca = findViewById(R.id.list_Busca);
    }




}
