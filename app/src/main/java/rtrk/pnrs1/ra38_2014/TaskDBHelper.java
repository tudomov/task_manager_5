package rtrk.pnrs1.ra38_2014;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by student on 30.5.2017.
 */

public class TaskDBHelper extends SQLiteOpenHelper {

    final static String baseName = "Task.db";
    final static int version = 1;



    public static final String TABLE_NAME="tabela";
    public static final String TASK_NAME="ime_zadatka";
    public static final String TASK_DESCRIPTION="opis";
    public static final String PRIORITY="prioritet";
    public static final String NOTIFICATION="podsjetnik";
    public static final String CHECKED="zavrsen";
    public static final String DATE="datum";
    public static final String DATE_YEAR="godina";
    public static final String DATE_MONTH="mjesec";
    public static final String DATE_DAY="dan";
    public static final String DATE_HOUR="sat";
    public static final String DATE_MINUTE="minut";
    public static final String TASK_ID="id";





    public TaskDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public TaskDBHelper(Context context) {
        super(context,baseName,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                TASK_NAME + " TEXT, " +
                TASK_DESCRIPTION + " TEXT, " +
                PRIORITY + " INTEGER, " +
                NOTIFICATION + " INTEGER, " +
                CHECKED + " INTEGER, " +
                DATE + " TEXT, " +
                DATE_YEAR + " INTEGER, " +
                DATE_MONTH + " INTEGER, " +
                DATE_DAY + " INTEGER, " +
                DATE_HOUR + " INTEGER, " +
                DATE_MINUTE + " INTEGER, " +
                TASK_ID + " INTEGER ); " );
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }

    public void addTask(Task t){
        ContentValues cv = new ContentValues();
        cv.put(TASK_NAME, t.getmText1());
        cv.put(DATE, t.getmText2());
        cv.put(DATE_YEAR, t.getGodina());
        cv.put(DATE_MONTH, t.getMjesec());
        cv.put(DATE_DAY, t.getDan());
        cv.put(DATE_HOUR, t.getSat());
        cv.put(DATE_MINUTE, t.getMinut());

        cv.put(TASK_DESCRIPTION, t.getOpis());
        cv.put(PRIORITY,t.getmView());
        Log.d("DodaliIdFUCKING", ""+(t.getTaskId()));
        cv.put(TASK_ID, t.getTaskId());

        if (t.ismRadioButton()) {
            cv.put(NOTIFICATION, 1);
        }else
            cv.put(NOTIFICATION, 0);


        if (t.ismCheckBox()) {
            cv.put(CHECKED, 1);
        }else
            cv.put(CHECKED, 0);




        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,cv);
        db.close();
    }

    public void updateTask(Task t, int id_taska){
        ContentValues cv = new ContentValues();

        cv.put(TASK_NAME, t.getmText1());
        cv.put(DATE, t.getmText2());
        cv.put(DATE_YEAR, t.getGodina());
        cv.put(DATE_MONTH, t.getMjesec());
        cv.put(DATE_DAY, t.getDan());
        cv.put(DATE_HOUR, t.getSat());
        cv.put(DATE_MINUTE, t.getMinut());
        cv.put(TASK_DESCRIPTION, t.getOpis());
        cv.put(PRIORITY,t.getmView());
        cv.put(TASK_ID, t.getTaskId());
        Log.d("APDEJTUJEM FUCKING", ""+t.getTaskId());
        if (t.ismRadioButton()) {
            cv.put(NOTIFICATION, 1);
        }else
            cv.put(NOTIFICATION, 0);

        if (t.ismCheckBox()) {
            cv.put(CHECKED, 1);
        }else
            cv.put(CHECKED, 0);




        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME,cv,TASK_ID + "=?", new String[] {String.valueOf(t.getTaskId())});
        db.close();
    }
    public void updateTask2(Task t, int id_taska){
        ContentValues cv = new ContentValues();

        cv.put(TASK_NAME, t.getmText1());
        cv.put(DATE, t.getmText2());
        cv.put(DATE_YEAR, t.getGodina());
        cv.put(DATE_MONTH, t.getMjesec());
        cv.put(DATE_DAY, t.getDan());
        cv.put(DATE_HOUR, t.getSat());
        cv.put(DATE_MINUTE, t.getMinut());
        cv.put(TASK_DESCRIPTION, t.getOpis());
        cv.put(PRIORITY,t.getmView());
        cv.put(TASK_ID, id_taska);
        Log.d("APDEJTUJEM FUCKING", ""+t.getTaskId());
        if (t.ismRadioButton()) {
            cv.put(NOTIFICATION, 1);
        }else
            cv.put(NOTIFICATION, 0);

        if (t.ismCheckBox()) {
            cv.put(CHECKED, 1);
        }else
            cv.put(CHECKED, 0);




        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME,cv,TASK_ID + "=?", new String[] {String.valueOf(t.getTaskId())});
        db.close();
    }

    public void updateTaskViaName(String name, Task t) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(TASK_NAME, t.getmText1());
        cv.put(DATE, t.getmText2());
        cv.put(DATE_YEAR, t.getGodina());
        cv.put(DATE_MONTH, t.getMjesec());
        cv.put(DATE_DAY, t.getDan());
        cv.put(DATE_HOUR, t.getSat());
        cv.put(DATE_MINUTE, t.getMinut());
        cv.put(TASK_DESCRIPTION, t.getOpis());
        cv.put(PRIORITY,t.getmView());
        if (t.ismRadioButton()) {
            cv.put(NOTIFICATION, 1);
        }else
            cv.put(NOTIFICATION, 0);

        if (t.ismCheckBox()) {
            cv.put(CHECKED, 1);
        }else
            cv.put(CHECKED, 0);

        db.update(TABLE_NAME,cv,TASK_NAME+"=?",new String[]{name});
        close();
    }

    public void removeTask(int koga_brisem){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,TASK_ID + "=?", new String[] {String.valueOf(koga_brisem)});
        db.close();
    }

    public Task readTask(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,TASK_ID + "=?",
                new String[] {String.valueOf(id)},null,null,null);
        cursor.moveToFirst();
        Task task = createTask(cursor);
        close();
        return task;

    }

    public Task[] readTasks(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);

        if (cursor.getCount() <= 0){
            return null;
        }
        Task[] tasks = new Task[cursor.getCount()];
        int i = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            tasks[i++] = createTask(cursor);
        }
        close();
        return tasks;

    }

    public Task[] updateTasks(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);

        if (cursor.getCount() <= 0){
            return null;
        }
        Task[] tasks = new Task[cursor.getCount()];
        int i = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            tasks[i++] = createTask(cursor);
            updateTask2(tasks[i-1], i-
                    1 );
        }
        close();
        return tasks;

    }

    private Task createTask(Cursor cursor){
        String name = cursor.getString(cursor.getColumnIndex(TASK_NAME));
        int godina = cursor.getInt(cursor.getColumnIndex(DATE_YEAR));
        int mjesec = cursor.getInt(cursor.getColumnIndex(DATE_MONTH));
        int dan = cursor.getInt(cursor.getColumnIndex(DATE_DAY));
        int sat = cursor.getInt(cursor.getColumnIndex(DATE_HOUR));
        int minut = cursor.getInt(cursor.getColumnIndex(DATE_MINUTE));

        String data = cursor.getString(cursor.getColumnIndex(DATE));
        String desq = cursor.getString(cursor.getColumnIndex(TASK_DESCRIPTION));
        int priority = cursor.getInt(cursor.getColumnIndex(PRIORITY));
        int reminder_i = cursor.getInt(cursor.getColumnIndex(NOTIFICATION));
        int id = cursor.getInt(cursor.getColumnIndex(TASK_ID));

        boolean reminder;
        if (reminder_i == 1)
            reminder=true;
        else
            reminder = false;


        int checked_i = cursor.getInt(cursor.getColumnIndex(CHECKED));
        boolean checked;
        if (checked_i == 1)
            checked=true;
        else
            checked = false;
        return new Task(dan,godina,checked,minut,mjesec, reminder, name, data, priority, desq, sat, id);

    }
}


