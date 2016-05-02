package bs23.com.dragsite.widgets;

import android.content.Context;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import bs23.com.dragsite.R;


public class DividerWidget extends BaseLinearLayout {
    Context context;

    private int spacingAbove;
    private int spacingBelow;
    private int widthPercentage;
    View dividerView;

    public DividerWidget(Context context) {
        super(context);
        this.context = context;
    }

    public void addContents() {
        super.addView(LayoutInflater.from(context).inflate(R.layout.widget_divider, null), new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        addBottomVIew(context);

        spacingAbove = dpToPx(20);
        spacingBelow = dpToPx(20);
        widthPercentage = 100;
    }

    public int getSpacingAbove() {
        return changePxToDp(spacingAbove);
    }

    public void setSpacingAbove(int spacingAbove) {
        this.spacingAbove = dpToPx(spacingAbove);
        if (dividerView == null) {
            dividerView = this.findViewById(R.id.view_divider);
        }

        PercentRelativeLayout.LayoutParams layoutParams = (PercentRelativeLayout.LayoutParams) dividerView.getLayoutParams();
        layoutParams.setMargins(0, this.spacingAbove, 0, spacingBelow);
        dividerView.setLayoutParams(layoutParams);
    }

    public int getSpacingBelow() {
        return changePxToDp(spacingBelow);
    }

    public void setSpacingBelow(int spacingBelow) {
        this.spacingBelow = dpToPx(spacingBelow);
        if (dividerView == null) {
            dividerView = this.findViewById(R.id.view_divider);
        }

        PercentRelativeLayout.LayoutParams layoutParams = (PercentRelativeLayout.LayoutParams) dividerView.getLayoutParams();
        layoutParams.setMargins(0, spacingAbove, 0, this.spacingBelow);
        dividerView.setLayoutParams(layoutParams);
    }

    public int getWidthPercentage() {
        return widthPercentage;
    }

    public void setWidthPercentage(int widthPercentage) {
        this.widthPercentage = widthPercentage;

        PercentRelativeLayout.LayoutParams params = (PercentRelativeLayout.LayoutParams) dividerView.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo info = params.getPercentLayoutInfo();
        info.widthPercent = (float) widthPercentage/100;
        dividerView.requestLayout();
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
}
