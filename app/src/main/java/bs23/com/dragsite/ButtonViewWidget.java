package bs23.com.dragsite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by BrainStation on 4/4/16.
 */
public class ButtonViewWidget extends BaseLinearLayout {

    Context context;
    public ButtonViewWidget(Context context) {
        super(context);
        this.context=context;
    }
    public void addContents()
    {
        super.addView(LayoutInflater.from(context).inflate(R.layout.button_view,null),new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
