package rtrk.pnrs1.ra38_2014;

/**
 * Created by student on 9.6.2017.
 */

public class CalculateNative {

    public native float getPercentage(int num,int finished);

    static {
        System.loadLibrary("native");
    }
}
