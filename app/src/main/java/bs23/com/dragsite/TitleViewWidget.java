package bs23.com.dragsite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class TitleViewWidget extends BaseLinearLayout {

    Context context;

    public TitleViewWidget(Context context) {
        super(context);
        this.context = context;
    }
    public void addContents()
    {
        super.addView(LayoutInflater.from(context).inflate(R.layout.title_view,null),new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
