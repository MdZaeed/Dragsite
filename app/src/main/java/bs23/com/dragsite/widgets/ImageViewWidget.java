package bs23.com.dragsite.widgets;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import bs23.com.dragsite.R;

/**
 * Created by BS-86 on 4/1/2016.
 */
public class ImageViewWidget extends BaseLinearLayout implements View.OnLongClickListener {

    Context context;

    public ImageViewWidget(Context context) {
        super(context);
        this.context=context;
    }

    public void addContents()
    {
        super.addView(LayoutInflater.from(context).inflate(R.layout.image_view, null), new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        addBottomVIew(context);

        this.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
        v.startDrag(data, shadowBuilder, v, 0);
        return true;
    }
}
