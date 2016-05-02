package bs23.com.dragsite.widgets;

import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import bs23.com.dragsite.R;

/**
 * Created by BS-86 on 5/2/2016.
 */
public abstract class BaseLinearLayoutWithSpacingNeeds extends BaseLinearLayout {

    public BaseLinearLayoutWithSpacingNeeds(Context context) {
        super(context);
    }

    @Override
    public abstract void addContents();


    protected int spacingAbove;
    protected int spacingBelow;
    private View mainView;

    public int getSpacingAbove() {
        return changePxToDp(spacingAbove);
    }

    public void setSpacingAbove(int spacingAbove) {
        this.spacingAbove = dpToPx(spacingAbove);
        if (getMainView() == null) {
            setMainView(this.findViewById(R.id.view_divider));
        }

 /*       LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getMainView().getLayoutParams();
        layoutParams.setMargins(0, this.spacingAbove, 0, spacingBelow);
        getMainView().setLayoutParams(layoutParams);*/

        getMainView().setPadding(0,this.spacingAbove,0,spacingBelow);

    }

    public int getSpacingBelow() {
        return changePxToDp(spacingBelow);
    }

    public void setSpacingBelow(int spacingBelow) {
        this.spacingBelow = dpToPx(spacingBelow);
        if (getMainView() == null) {
            setMainView(this.findViewById(R.id.view_divider));
        }

/*
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getMainView().getLayoutParams();
        layoutParams.setMargins(0, spacingAbove, 0, this.spacingBelow);
        getMainView().setLayoutParams(layoutParams);
*/

        getMainView().setPadding(0,spacingAbove,0,this.spacingBelow);
    }

    private int changePxToDp(int px) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public View getMainView() {
        return mainView;
    }

    public void setMainView(View mainView) {
        this.mainView = mainView;
    }
}
