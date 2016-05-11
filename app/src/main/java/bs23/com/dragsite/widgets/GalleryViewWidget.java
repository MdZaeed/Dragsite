package bs23.com.dragsite.widgets;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bs23.com.dragsite.R;
import bs23.com.dragsite.adapter.BaseGalleryAdapter;
import bs23.com.dragsite.adapter.BaseGalleryAdapterCopy;
import bs23.com.dragsite.model.ImageSelectModel;

/**
 * Created by BrainStation on 4/4/16.
 */
public class GalleryViewWidget extends BaseLinearLayoutWithSpacingNeeds implements BaseGalleryAdapter.CameraClick {

    Context context;
    private List<ImageSelectModel> imageSelectModels;
    private int noOfColoumns=5;
    RecyclerView recyclerView;
    BaseGalleryAdapterCopy baseGalleryAdapterCopy;
    TextView textView;
    GestureDetector gestureDetector;

    public GalleryViewWidget(Context context) {
        super(context);
        this.context = context;
        imageSelectModels=new ArrayList<>();

        gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
            public void onLongPress(MotionEvent e) {
                Log.e("", "Longpress detected");
                onManualLongClick();
            }
        });
    }

    private void onManualLongClick() {
        onLongClick(this);
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

        recyclerView=(RecyclerView) this.findViewById(R.id.rv_gallery);
        textView=(TextView) this.findViewById(R.id.tv_gallery_dummy);
    }

    public List<ImageSelectModel> getImageSelectModels() {
        return imageSelectModels;
    }

    public void setImageSelectModels(List<ImageSelectModel> imageSelectModels) {
        this.imageSelectModels = imageSelectModels;

        addRecyclerView(recyclerView.getWidth());
    }

    protected void addRecyclerView(int width) {

        if(imageSelectModels.isEmpty())
        {
            textView.setVisibility(VISIBLE);
        }
        else {
            int spanPerColumn = width / noOfColoumns;
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), noOfColoumns));
            baseGalleryAdapterCopy = new BaseGalleryAdapterCopy(getContext(), imageSelectModels);
            baseGalleryAdapterCopy.setImageSize(spanPerColumn);
            recyclerView.setAdapter(baseGalleryAdapterCopy);

            textView.setVisibility(GONE);
        }
    }

    @Override
    public void onCameraClick(View v) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(!imageSelectModels.isEmpty()) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                this.performClick();
                gestureDetector.onTouchEvent(ev);
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
