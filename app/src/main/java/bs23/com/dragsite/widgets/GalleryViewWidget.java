package bs23.com.dragsite.widgets;

import android.content.Context;
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
import bs23.com.dragsite.adapter.GalleryAdapterWithMultiSelection;
import bs23.com.dragsite.adapter.GalleryAdapterWithAllElementTouchDisabled;
import bs23.com.dragsite.model.ImageSelectGalleryElementModel;

/**
 * Created by BrainStation on 4/4/16.
 */
public class GalleryViewWidget extends BaseLinearLayoutWithSpacingNeeds implements GalleryAdapterWithMultiSelection.CameraClick {

    public static String[] typeArray={"none","partial","full"};
    public static String[] cropTypeArray ={"none","square","rectangle"};


    Context context;
    private List<ImageSelectGalleryElementModel> imageSelectModels;
    private int noOfColoumns=3;
    RecyclerView recyclerView;
    GalleryAdapterWithAllElementTouchDisabled baseGalleryAdapterCopy;
    TextView textView;
    GestureDetector gestureDetector;
    private boolean isThumbnailCaptionHovering=true;
    private String thumbnailCaptionType=typeArray[0];
    private int borderSize=0;
    private int spacingOfIndividualElement=3;
    private String cropType = cropTypeArray[1];

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

    public List<ImageSelectGalleryElementModel> getImageSelectModels() {
        return imageSelectModels;
    }

    public void setImageSelectModels(List<ImageSelectGalleryElementModel> imageSelectModels) {
        this.imageSelectModels = imageSelectModels;

        addRecyclerView(recyclerView.getWidth());
    }

    protected void addRecyclerView(int width) {

        if(imageSelectModels.isEmpty())
        {
            textView.setVisibility(VISIBLE);
        }
        else {
            int spanPerColumn = width / getNoOfColoumns();
/*            int borderSize=0,spacingElements=3;
            String cropType=GalleryViewWidget.cropTypeArray[1];
            if(baseGalleryAdapterCopy!=null)
            {
                borderSize=baseGalleryAdapterCopy.getBorderSize();
                spacingElements=baseGalleryAdapterCopy.getSpacingOfElemnts();
                cropType=baseGalleryAdapterCopy.getCropType();
            }*/
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getNoOfColoumns()));
            baseGalleryAdapterCopy = new GalleryAdapterWithAllElementTouchDisabled(getContext(), imageSelectModels,spanPerColumn);
            baseGalleryAdapterCopy.setBorderSize(borderSize);
            baseGalleryAdapterCopy.setSpacingOfElemnts(spacingOfIndividualElement);
            baseGalleryAdapterCopy.setCropType(cropType);
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
                gestureDetector.onTouchEvent(ev);
            }else if (ev.getAction() == MotionEvent.ACTION_UP)
            {
                this.performClick();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public int getNoOfColoumns() {
        return noOfColoumns;
    }

    public void setNoOfColoumns(int noOfColoumns) {
        this.noOfColoumns = noOfColoumns;

        addRecyclerView(recyclerView.getWidth());
    }

    public boolean isThumbnailCaptionHovering() {
        return isThumbnailCaptionHovering;
    }

    public void setThumbnailCaptionHovering(boolean thumbnailCaptionHovering) {
        isThumbnailCaptionHovering = thumbnailCaptionHovering;
    }

    public String getThumbnailCaptionType() {
        return thumbnailCaptionType;
    }

    public void setThumbnailCaptionType(String thumbnailCaptionType) {
        this.thumbnailCaptionType = thumbnailCaptionType;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;

        if (baseGalleryAdapterCopy!=null) {
            baseGalleryAdapterCopy.setBorderSize(borderSize);
            baseGalleryAdapterCopy.notifyDataSetChanged();
        }
    }

    public int getSpacingOfIndividualElement() {
        return spacingOfIndividualElement;
    }

    public void setSpacingOfIndividualElement(int spacingOfIndividualElement) {
        this.spacingOfIndividualElement = spacingOfIndividualElement;

        if (baseGalleryAdapterCopy!=null) {
            baseGalleryAdapterCopy.setSpacingOfElemnts(spacingOfIndividualElement);
            baseGalleryAdapterCopy.notifyDataSetChanged();
        }
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;

        if(baseGalleryAdapterCopy!=null) {
            baseGalleryAdapterCopy.setCropType(cropType);
            baseGalleryAdapterCopy.notifyDataSetChanged();
        }
    }
}
