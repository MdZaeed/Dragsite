package bs23.com.dragsite;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by BS-86 on 4/4/2016.
 */
public class BaseLinearLayout extends LinearLayout {
    private int aboveId=0;

    public BaseLinearLayout(Context context) {
        super(context);
    }

    public int getAboveId() {
        return aboveId;
    }

    public void setAboveId(int aboveId) {
        this.aboveId = aboveId;
    }
}
