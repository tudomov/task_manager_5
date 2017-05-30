package rtrk.pnrs1.ra38_2014;

import java.io.Serializable;

/**
 * Created by Radenko on 11/04/2017.
 */

public class Task implements Serializable {
    public int mView;
    public String mText1;
    public String mText2;
    public boolean mCheckBox;
    public boolean mRadioButton;

    public Task(int mView, String mText1, String mText2, boolean mCheckBox, boolean mRadioButton) {
        this.mView = mView;
        this.mText1 = mText1;
        this.mText2 = mText2;
        this.mCheckBox = mCheckBox;
        this.mRadioButton = mRadioButton;
    }

    public int getmView() {
        return mView;
    }

    public void setmView(int mView) {
        this.mView = mView;
    }

    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }

    public boolean ismCheckBox() {
        return mCheckBox;
    }

    public void setmCheckBox(boolean mCheckBox) {
        this.mCheckBox = mCheckBox;
    }

    public boolean ismRadioButton() {
        return mRadioButton;
    }

    public void setmRadioButton(boolean mRadioButton) {
        this.mRadioButton = mRadioButton;
    }
}
