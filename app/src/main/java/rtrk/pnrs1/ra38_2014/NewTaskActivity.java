package rtrk.pnrs1.ra38_2014;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class NewTaskActivity extends AppCompatActivity {

    Button crveni, zeleni, zuti, dodaj, otkazi;
    EditText naslov, opis;
    DatePicker datum;
    TimePicker vrijeme;
    int dan, mjesec, godina, sat , minut, prioritet;
    String date, nazivZadatka;
    boolean podsjetnik;
    CheckBox remainder;
    final TaskAdapter adapter = MainActivity.getTaskAdapter();
    Task task = new Task(0,0,true,0,0, false, "", "", 0,"",0);
    Calendar danas, taskDate;
    int dayToday, dayOfTask, dayOfWeek, year, taskYear;
    String Datum, nazivLijevogDugmeta, nazivDesnogDugmeta;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        crveni = (Button)findViewById(R.id.visok);
        zuti = (Button)findViewById(R.id.srednji);
        zeleni = (Button)findViewById(R.id.nizak);
        dodaj = (Button)findViewById(R.id.dodaj);
        otkazi = (Button)findViewById(R.id.otkazi);
        naslov = (EditText)findViewById(R.id.naslov);
        opis = (EditText)findViewById(R.id.opis);
        datum = (DatePicker)findViewById(R.id.datum);
        vrijeme = (TimePicker)findViewById(R.id.vrijeme);
        remainder = (CheckBox) findViewById(R.id.podsjetnik);


        datum.setMinDate(System.currentTimeMillis() -1000);

      //  Calendar danas = Calendar.getInstance();
      //  Calendar taskDate = Calendar.getInstance();

     //   taskDate.set(datum.getYear(),datum.getMonth(),datum.getDayOfMonth());

     //   dayToday = danas.get(Calendar.DAY_OF_YEAR);
     //   dayOfTask = taskDate.get(Calendar.DAY_OF_YEAR);

      //  if (dayOfTask - dayToday == 0) {
      //      Datum = "Danas";

     //   } else if (dayOfTask - dayToday == 1) {
       //     Datum = "Sutra";

       // } else if (dayOfTask - dayToday == 2) {
       //     Datum = "Prekosutra";

      //  } else if (dayOfTask - dayToday > 2 && dayOfTask - dayToday <7){
      //      SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
       //     Datum = sdf.format(taskDate);

       // } else {
      //      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
      //      Datum = sdf.format(taskDate);
      //  }

        nazivLijevogDugmeta = getIntent().getStringExtra("zaLijevi");
        nazivDesnogDugmeta = getIntent().getStringExtra("zaDesni");
       // Log.d("imena su", nazivLijevogDugmeta);
       // Log.d("imena su", nazivDesnogDugmeta);
        dodaj.setText(nazivLijevogDugmeta);
        otkazi.setText(nazivDesnogDugmeta);


        crveni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crveni.setEnabled(false);
                zuti.setEnabled(true);
                zeleni.setEnabled(true);
                zuti.setBackgroundResource(R.color.zuta);
                zeleni.setBackgroundResource(R.color.zelena);
                crveni.setBackgroundResource(R.color.tamnoCrvena);
                prioritet = 1;
                if(naslov.getText().length() > 0 && opis.getText().length() > 0 && (crveni.isEnabled() == false || zeleni.isEnabled() == false || zuti.isEnabled() == false)){
                    dodaj.setEnabled(true);
                }else{
                    dodaj.setEnabled(false);
                }


            }
        });

        zuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crveni.setEnabled(true);
                zuti.setEnabled(false);
                zeleni.setEnabled(true);
                zuti.setBackgroundResource(R.color.tamnoZuta);
                zeleni.setBackgroundResource(R.color.zelena);
                crveni.setBackgroundResource(R.color.crvena);
                prioritet = 2;
                if(naslov.getText().length() > 0 && opis.getText().length() > 0 && (crveni.isEnabled() == false || zeleni.isEnabled() == false || zuti.isEnabled() == false)){
                    dodaj.setEnabled(true);
                }else{
                    dodaj.setEnabled(false);
                }

            }
        });

        zeleni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crveni.setEnabled(true);
                zuti.setEnabled(true);
                zeleni.setEnabled(false);
                zuti.setBackgroundResource(R.color.zuta);
                zeleni.setBackgroundResource(R.color.tamnoZelena);
                crveni.setBackgroundResource(R.color.crvena);
                prioritet = 3;
                if(naslov.getText().length() > 0 && opis.getText().length() > 0 && (crveni.isEnabled() == false || zeleni.isEnabled() == false || zuti.isEnabled() == false)){
                    dodaj.setEnabled(true);
                }else{
                    dodaj.setEnabled(false);
                }


            }
        });

        otkazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewTaskActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        dodaj.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
             //    dan = datum.getDayOfMonth();
             //    mjesec = datum.getMonth();
             //    godina = datum.getYear();
             //   danas = Calendar.getInstance();
             //   date = String.valueOf(dan) + "." + String.valueOf(mjesec) + "." + String.valueOf(godina);
                nazivZadatka = naslov.getText().toString();
                podsjetnik = remainder.isChecked();

                danas = Calendar.getInstance();
                taskDate = Calendar.getInstance();

                //PROVERA
                danas.set(2017,11,30);

                taskDate.set(datum.getYear(),datum.getMonth(),datum.getDayOfMonth());

                dayToday = danas.get(Calendar.DAY_OF_YEAR);
                dayOfTask = taskDate.get(Calendar.DAY_OF_YEAR);
                dayOfWeek = taskDate.get(Calendar.DAY_OF_WEEK);
                year = danas.get(Calendar.YEAR);
                taskYear = taskDate.get(Calendar.YEAR);


                Log.i("DAN ZADATKA:",Integer.toString(dayOfTask));
                Log.i("DAN DANAS:",Integer.toString(dayToday));


                if(year == datum.getYear() || (taskYear - year == 1)) {

                    if (taskYear - year == 1) {
                        if (year % 4 == 0) {
                            dayOfTask += 366;
                        } else {
                            dayOfTask += 365;
                        }

                    }

                    if (dayOfTask - dayToday == 0) {
                        Datum = "Danas";

                    } else if (dayOfTask - dayToday == 1) {
                        Datum = "Sutra";


                    } else if (dayOfTask - dayToday == 2) {
                        Datum = "Prekosutra";


                    } else if (dayOfTask - dayToday > 2 && dayOfTask - dayToday < 7) {

                        switch (dayOfWeek - 1) {
                            case 1:
                                Datum = "Ponedeljak";
                                break;
                            case 2:
                                Datum = "Utorak";
                                break;
                            case 3:
                                Datum = "Sreda";
                                break;
                            case 4:
                                Datum = "Cetvrtak";
                                break;
                            case 5:
                                Datum = "Petak";
                                break;
                            case 6:
                                Datum = "Subota";
                                break;
                            case 7:
                                Datum = "Nedelja";
                                break;
                        }

                    } else {
                        Datum = Integer.toString(datum.getDayOfMonth()) + "." + Integer.toString(datum.getMonth() + 1) + "." + Integer.toString(datum.getYear());

                    }
                }else{
                    Datum = Integer.toString(datum.getDayOfMonth()) + "." + Integer.toString(datum.getMonth() + 1) + "." + Integer.toString(datum.getYear());
                }


                task.setmView(prioritet);
                task.setmText1(nazivZadatka);
                task.setmText2(Datum);
                task.setmCheckBox(false);
                task.setGodina(taskYear);
                task.setDan(dayOfTask);
                task.setMjesec(taskDate.get(Calendar.DAY_OF_YEAR));
                task.setSat(vrijeme.getCurrentHour());
                task.setMinut(vrijeme.getCurrentMinute());
                task.setmRadioButton(podsjetnik);
                task.setOpis(opis.getText().toString());

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",task);
                setResult(NewTaskActivity.RESULT_OK, returnIntent);
                finish();



            }
        });

                naslov.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(naslov.getText().length() > 0 && opis.getText().length() > 0 && (crveni.isEnabled() == false || zeleni.isEnabled() == false || zuti.isEnabled() == false)){
                            dodaj.setEnabled(true);
                        }else{
                            dodaj.setEnabled(false);
                        }



                    }
                });
        opis.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(naslov.getText().length() > 0 && opis.getText().length() > 0 && (crveni.isEnabled() == false || zeleni.isEnabled() == false || zuti.isEnabled() == false)){
                    dodaj.setEnabled(true);
                }else{
                    dodaj.setEnabled(false);
                }


            }
        });




        dodaj.setEnabled(false);

    }


   // @Override
   // protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      //  if (requestCode == 2) {
      //  }
   // }
}
