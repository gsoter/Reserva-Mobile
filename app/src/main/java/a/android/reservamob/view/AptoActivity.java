package a.android.reservamob.view;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import a.android.reservamob.R;

public class AptoActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 5;
    private ViewPager viewPager;
    private CustomPagerAdapter customPagerAdapter;

    int[] resourcesAp01 = {
            R.drawable.ap01_194875850,
            R.drawable.ap01_194875854,
            R.drawable.ap01_194875847,
            R.drawable.ap01_194875838,
            R.drawable.ap01_194875836
    };
    int[] resourcesAp02 = {
            R.drawable.ap02_194875883,
            R.drawable.ap02_194875879,
            R.drawable.ap02_194875875,
            R.drawable.ap02_194875871,
            R.drawable.ap02_194875866,
            R.drawable.ap02_194875860
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        try {
            if (i.getExtras().containsKey("apto01")) {
                setContentView(R.layout.activity_apto);
                viewPager = findViewById(R.id.pgrView);
                customPagerAdapter = new CustomPagerAdapter(this, resourcesAp01);
                viewPager.setAdapter(customPagerAdapter);
            }
            if (i.getExtras().containsKey("apto02")) {
                setContentView(R.layout.activity_apto02);
                viewPager = findViewById(R.id.pgrView02);
                customPagerAdapter = new CustomPagerAdapter(this, resourcesAp02);
                viewPager.setAdapter(customPagerAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
