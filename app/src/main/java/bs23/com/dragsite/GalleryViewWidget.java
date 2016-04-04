package bs23.com.dragsite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by BrainStation on 4/4/16.
 */
public class GalleryViewWidget extends BaseLinearLayout {

    Context context;
    public GalleryViewWidget(Context context) {
        super(context);
        this.context = context;
    }

    public void addContents()
    {
        super.addView(LayoutInflater.from(context).inflate(R.layout.gallery_layout,null),new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
