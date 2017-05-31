package rtrk.pnrs1.ra38_2014;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Radenko on 11/04/2017.
 */

public class TaskAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Task> mTasks;

    public TaskAdapter(Context context) {
        mContext = context;
        mTasks = new ArrayList<Task>();
    }

    public void addTask(Task task){
        mTasks.add(task);
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return mTasks.size();
    }

    @Override
    public Object getItem(int position) {
        Object rv = null;
        try {
            rv = mTasks.get(position);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return rv;
    }

    public void updateAdapter(Task[] items) {
        mTasks.clear();
              if (items != null) {
            for (Task item:items) {
                mTasks.add(item);
                          }
                  }
        notifyDataSetChanged();
           }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.task_row, null);

            ViewHolder holder = new ViewHolder();

            holder.view = (View) view.findViewById(R.id.task_row_view);
            holder.textview = (TextView) view.findViewById(R.id.task_row_textview);
            holder.textview2 = (TextView) view.findViewById(R.id.task_row_textview2);
            holder.mCheckBox = (CheckBox) view.findViewById(R.id.task_row_checkbox);
            holder.mRadioButton = (RadioButton) view.findViewById(R.id.task_row_radiobutton);
            view.setTag(holder);

        }
        final Task task = (Task) getItem(position);
        final ViewHolder holder = (ViewHolder) view.getTag();

        if(task.mView == 1){
            holder.view.setBackgroundResource(R.color.crvena);
        } else if(task.mView == 2){
            holder.view.setBackgroundResource(R.color.zuta);
        }else if(task.mView == 3){
            holder.view.setBackgroundResource(R.color.zelena);
        }

        holder.textview.setText(task.mText1);
        holder.textview2.setText(task.mText2);

        holder.mRadioButton.setChecked(task.mRadioButton);
        holder.mCheckBox.setChecked(false);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    task.mCheckBox = true;

                    holder.textview.setPaintFlags(holder.textview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }else {
                    task.mCheckBox = false;
                    holder.textview.setPaintFlags(0);
                }
            }
        });

        return view;

    }

    private class ViewHolder {
        public View view = null;
        public TextView textview = null;
        public TextView textview2 = null;
        public CheckBox mCheckBox = null;
        public RadioButton mRadioButton = null;

    }

    
}
