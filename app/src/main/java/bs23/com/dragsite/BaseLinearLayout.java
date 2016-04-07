package bs23.com.dragsite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by BS-86 on 4/4/2016.
 */
public abstract class BaseLinearLayout extends LinearLayout {
    private int aboveId=0;
    View bottomView;

    public BaseLinearLayout(Context context) {
        super(context);
        this.setOrientation(VERTICAL);
    }

    public void addBottomVIew(Context context)
    {
        bottomView=new View(context);
        bottomView.setBackgroundColor(getResources().getColor(android.R.color.white));
        super.addView(bottomView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 20));
    }

    public int getAboveId() {
        return aboveId;
    }

    public void setAboveId(int aboveId) {
        this.aboveId = aboveId;
    }

    public void setBottomViewColor(int color)
    {
        bottomView.setBackgroundColor(getResources().getColor(color));
    }

    public abstract void addContents();

}
