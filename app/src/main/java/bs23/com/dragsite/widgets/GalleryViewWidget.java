package bs23.com.dragsite.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bs23.com.dragsite.R;
import bs23.com.dragsite.model.ImageSelectModel;

/**
 * Created by BrainStation on 4/4/16.
 */
public class GalleryViewWidget extends BaseLinearLayoutWithSpacingNeeds {

    Context context;
    private List<ImageSelectModel> imageSelectModels;

    public GalleryViewWidget(Context context) {
        super(context);
        this.context = context;
        imageSelectModels=new ArrayList<>();
    }

    public void addContents()
    {
        super.addView(LayoutInflater.from(context).inflate(R.layout.widget_gallery_layout,null),new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        addBottomVIew(context);

        spacingAbove = dpToPx(0);
        spacingBelow = dpToPx(0);
        setMainView(this.findViewById(R.id.ll_gallery_widget));
    }

    public List<ImageSelectModel> getImageSelectModels() {
        return imageSelectModels;
    }

    public void setImageSelectModels(List<ImageSelectModel> imageSelectModels) {
        this.imageSelectModels = imageSelectModels;
    }
}
