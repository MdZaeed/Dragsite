package bs23.com.dragsite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by BS-86 on 4/12/2016.
 */
public class MapsWidget extends BaseLinearLayout {
    Context context;

    public MapsWidget(Context context) {
        super(context);
        this.context=context;
    }

    public void addContents()
    {
        addView(LayoutInflater.from(context).inflate(R.layout.maps_layout, null), new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        addBottomVIew(context);
        setClickable(true);
    }
}
