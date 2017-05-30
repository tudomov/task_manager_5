package rtrk.pnrs1.ra38_2014;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * Created by student on 3.5.2017.
 */

public class StatisticView extends View{

    Paint p = new Paint();
    Paint isjecak = new Paint();
    Paint isjecak2 = new Paint();
    Paint isjecak3 = new Paint();

    int red, green, yellow;
    boolean flag = true;

    float percentage = (float)0;
    float percentage2 = (float)0;
    float percentage3 = (float)0;

    RectF rectNeki, rectNeki2, rectNeki3;

    String string1, string2, string3;


    public StatisticView(Context context) {
        super(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(getResources().getColor(R.color.svijetloPlava));

        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/4, 200, p);
        canvas.drawCircle(canvas.getWidth()/4, canvas.getHeight()*2/3, 200, p);
        canvas.drawCircle(canvas.getWidth()*3/4, canvas.getHeight()*2/3, 200, p);


        p.setColor(Color.BLACK);
        p.setTextAlign(Paint.Align.CENTER);
        p.setTextSize(65);
        canvas.drawText(getResources().getString(R.string.visokPrior),canvas.getWidth()/2, canvas.getHeight()/4 + 280, p);
        canvas.drawText(getResources().getString(R.string.srednjiPrior),canvas.getWidth()/4, canvas.getHeight()*2/3 + 280, p);
        canvas.drawText(getResources().getString(R.string.nizakPrior),canvas.getWidth()*3/4, canvas.getHeight()*2/3 + 280, p);

        red = 20;
        green = 100;
        yellow = 55;

        isjecak.setColor(getResources().getColor(R.color.crvena));
        isjecak2.setColor(getResources().getColor(R.color.zuta));
        isjecak3.setColor(getResources().getColor(R.color.zelena));

        rectNeki.set(canvas.getWidth()/2-200, canvas.getHeight()/4-200,canvas.getWidth()/2+200, canvas.getHeight()/4+200);
        rectNeki2.set(canvas.getWidth()/4-200, canvas.getHeight()*2/3-200,canvas.getWidth()/4+200, canvas.getHeight()*2/3+200);
        rectNeki3.set(canvas.getWidth()*3/4-200, canvas.getHeight()*2/3-200,canvas.getWidth()*3/4+200, canvas.getHeight()*2/3+200);

        canvas.drawArc(rectNeki, -90, (float)3.6*percentage, true, isjecak);
        canvas.drawArc(rectNeki2, -90, (float)3.6*percentage2, true, isjecak2);
        canvas.drawArc(rectNeki3, -90, (float)3.6*percentage3, true, isjecak3);


        canvas.drawText(string1+"%",canvas.getWidth()/2, canvas.getHeight()/4 + 20, p);
        canvas.drawText(string2+"%",canvas.getWidth()/4, canvas.getHeight()*2/3 + 20, p);
        canvas.drawText(string3+"%",canvas.getWidth()*3/4, canvas.getHeight()*2/3 + 20, p);



        if (flag) {
            setPercentageHigh((float)(percentage),(float)(percentage2),(float)(percentage3));
        }
    }
    public void setPercentageHigh(float percentage,float percentage2,float percentage3) {

        if(this.percentage < red || this.percentage2 < yellow || this.percentage3 < green){
            if (this.percentage < red) {
                this.percentage = percentage + 1;
                string1 = Float.toString(this.percentage);
            }
            if (this.percentage2 < yellow) {
                this.percentage2 = percentage2 + 1;
                string2 = Float.toString(this.percentage2);
            }
            if (this.percentage3 < green) {
                this.percentage3 = percentage3 + 1;
                string3 = Float.toString(this.percentage3);
            }

            invalidate();
        } else {
            flag = false;
        }




    }



}
