package bs23.com.dragsite.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import bs23.com.dragsite.R;


public class BlockquoteWidget extends BaseLinearLayout {
    Context context;
    public BlockquoteWidget(Context context) {
        super(context);
        this.context = context;
    }

    public void addContents()
    {
        super.addView(LayoutInflater.from(context).inflate(R.layout.widget_blockquote,null),new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        addBottomVIew(context);
    }
}
