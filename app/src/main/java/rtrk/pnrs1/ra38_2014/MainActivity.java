package rtrk.pnrs1.ra38_2014;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    Button noviZadatakButton, statistikaButton;
    public static TaskAdapter adapter;
    public TaskDBHelper dbHelper;
    int pozisn;
    int LIST_LONG_PRESS = 2;
    int ADD_TASK_CLICK = 1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        noviZadatakButton = (Button)findViewById(R.id.novi_zadatak_button);
        statistikaButton = (Button)findViewById(R.id.statistika_button);

        noviZadatakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewTaskActivity.class);
                intent.putExtra("zaLijevi", getText(R.string.dodajButton));
                intent.putExtra("zaDesni", getText(R.string.otkaziButton));
                    startActivityForResult(intent, 1);
            }
        });

        statistikaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStat = new Intent(MainActivity.this, StatisticActivity.class);
                startActivity(intentStat);
            }
        });




        //adapter.addTask(new Task(1, 1, 1, 1));

        // adapter.addTask(new Task(1, getString(R.string.imeZadatka), getString(R.string.imeZadatka), true));
        //adapter.addTask(new Task(1, getString(R.string.imeZadatka), getString(R.string.imeZadatka), true));

        adapter = new TaskAdapter(this);
        ListView list = (ListView) findViewById(R.id.list);
        dbHelper = new TaskDBHelper(this);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(MainActivity.this, NewTaskActivity.class);
               intent.putExtra("zaLijevi", getText(R.string.sacuvaj));
               intent.putExtra("zaDesni", getText(R.string.obrisi));
                Log.d("Poslat", ""+position);
                Task task = (Task) adapter.getItem(position);
                Log.d("Poslat2", task.getmText1());
               intent.putExtra("result", (Serializable) task );
               startActivityForResult(intent, 2);
             //   Log.d("Sta god", "dugi klik");
              //  startActivity(intent);


                return true;
            }
        });
        list.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Task[] tasks = dbHelper.readTasks();
        adapter.updateAdapter(tasks);
    }

    //   Intent intent = this.getIntent();

        // intent.getIntExtra("prioritet",prioritet);
      //   text1 = intent.getStringExtra("nazivZadatka");
       //  text2 = intent.getStringExtra("datum");
       //  gotovTask = intent.getBooleanExtra("podsjetnik", false);
        // novTask = intent.getIntExtra("novTask",0);

     //   if(novTask!=0) {
      //        adapter.addTask(new Task(prioritet, text1, text2, gotovTask));
      //  }

   // }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Task t = (Task)data.getSerializableExtra("result");
                adapter.addTask(t);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }

        }else if(requestCode == 2){

            if(resultCode == Activity.RESULT_OK){
                //nista
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }

    }

    public static TaskAdapter getTaskAdapter(){
            return adapter;
        }


}
