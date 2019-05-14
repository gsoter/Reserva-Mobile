package a.android.reservamob;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

public class AptoActivity extends AppCompatActivity {

    ImageView imgSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        if (i.getExtras().containsKey("apto01"))
            setContentView(R.layout.activity_apto);

        else if (i.getExtras().containsKey("apto02"))
            setContentView(R.layout.activity_apto02);


    }



}
