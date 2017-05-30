package rtrk.pnrs1.ra38_2014;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StatisticActivity extends AppCompatActivity {

    StatisticView view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        view = new StatisticView(getApplicationContext());
        setContentView(view);

    }
}
