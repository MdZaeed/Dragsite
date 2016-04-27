package bs23.com.dragsite.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import bs23.com.dragsite.R;


public class TitleViewWidget extends BaseLinearLayout {

    Context context;
    boolean isButtonPressedHere=false;

    public TitleViewWidget(Context context) {
        super(context);
        this.context = context;
    }
    public void addContents()
    {
        super.addView(LayoutInflater.from(context).inflate(R.layout.widget_title_view,null),new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        addBottomVIew(context);
    }
}
