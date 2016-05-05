package bs23.com.dragsite.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import bs23.com.dragsite.R;

/**
 * Created by BS-86 on 4/1/2016.
 */
public class ImageViewWidget extends BaseLinearLayoutWithSpacingNeeds {

    Context context;
    private int spacingLeft;
    private int spacingRight;

    public ImageViewWidget(Context context) {
        super(context);
        this.context=context;
    }

    public void addContents()
    {
        super.addView(LayoutInflater.from(context).inflate(R.layout.widget_image_view, null), new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        addBottomVIew(context);

        spacingAbove = dpToPx(0);
        spacingBelow = dpToPx(0);
        spacingLeft = dpToPx(0);
        spacingRight = dpToPx(0);
        setMainView(this.findViewById(R.id.ll_image_widget));
    }

    @Override
    public void setSpacingAbove(int spacingAbove) {
        this.spacingAbove = dpToPx(spacingAbove);
        if (getMainView() == null) {
            setMainView(this.findViewById(R.id.view_divider));
        }

/*
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getMainView().getLayoutParams();
        layoutParams.setMargins(0, spacingAbove, 0, this.spacingBelow);
        getMainView().setLayoutParams(layoutParams);
*/

        getMainView().setPadding(getSpacingLeft(),spacingAbove,getSpacingRight(),this.spacingBelow);
    }

    @Override
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

        getMainView().setPadding(getSpacingLeft(),spacingAbove, getSpacingRight(),this.spacingBelow);
    }

    public int getSpacingLeft() {
        return changePxToDp(spacingLeft);
    }

    public void setSpacingLeft(int spacingLeft) {
        this.spacingLeft = dpToPx(spacingLeft);
        if (getMainView() == null) {
            setMainView(this.findViewById(R.id.view_divider));
        }

/*
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getMainView().getLayoutParams();
        layoutParams.setMargins(0, spacingAbove, 0, this.spacingBelow);
        getMainView().setLayoutParams(layoutParams);
*/

        getMainView().setPadding(getSpacingLeft(),spacingAbove, getSpacingRight(),spacingBelow);
    }

    public int getSpacingRight() {
        return changePxToDp(spacingRight);
    }

    public void setSpacingRight(int spacingRight) {
        this.spacingRight = dpToPx(spacingRight);
        if (getMainView() == null) {
            setMainView(this.findViewById(R.id.view_divider));
        }

/*
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getMainView().getLayoutParams();
        layoutParams.setMargins(0, spacingAbove, 0, this.spacingBelow);
        getMainView().setLayoutParams(layoutParams);
*/

        getMainView().setPadding(getSpacingLeft(),spacingAbove, getSpacingRight(),spacingBelow);
    }

}
