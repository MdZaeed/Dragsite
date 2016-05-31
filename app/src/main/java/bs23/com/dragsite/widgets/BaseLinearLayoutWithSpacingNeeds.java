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


/*
Steps to add spacing above and spacing below button to a widget

Step 1: extend BaseSpacingFragmentForSecondLevel from spacingEditFreagment
Step 2: on the SpaceEditFragment, find the corcerned widget from the first edit fragment and store it in the ownWidget variable;
Step 3: Extend the BaseLinearLayoutWithSpacingNeeds class from your Widget class
Step 4: set initial spacingAbove and Below and add the widget's layout in the mainView
Step 4: give the spacing Above spinner and below spinner the ids sp_spacing_above and sp_spacing_below respectively
Step 5: Dont forget to add the onclick action
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

    public void setSpacingAboveAndUi(int spacingAbove) {
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

    public void setSpacingBelowAndUi(int spacingBelow) {
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

    protected int changePxToDp(int px) {
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
