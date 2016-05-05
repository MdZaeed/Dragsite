package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import bs23.com.dragsite.MainActivity;
import bs23.com.dragsite.R;
import bs23.com.dragsite.widgets.ImageViewWidget;

/**
 * Created by BrainStation on 4/15/16.
 */
public class ImageEditFragment extends BaseEditFragment {

    Button editImageButton;
    Button addImageButton;
    private ImageViewWidget imageViewWidget;
    private String additionMood="ADD IMAGE";

    public static ImageEditFragment newInstance() {
        return new ImageEditFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_widget, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editImageButton=(Button) view.findViewById(R.id.btn_edit_image);
        addImageButton=(Button) view.findViewById(R.id.btn_add_image);


        if (getImageViewWidget().findViewById(R.id.tv_image_widget).getVisibility()==View.GONE)
        {
            editImageButton.setVisibility(View.VISIBLE);
            addImageButton.setText("REPLACE IMAGE");
            additionMood="REPLACE IMAGE";
        }else
        {
            editImageButton.setVisibility(View.GONE);
            addImageButton.setText("ADD IMAGE");
            additionMood="ADD IMAGE";
        }


        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageEditReplaceImageFragment imageEditReplaceImageFragment= ImageEditReplaceImageFragment.newInstance();
                imageEditReplaceImageFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0,android.R.anim.slide_in_left,0)
                        .replace(((MainActivity)getActivity()).getBottomPaneLinearLayout().getId(), imageEditReplaceImageFragment)
                        .addToBackStack("null")
                        .commit();
            }
        });
    }

    public ImageViewWidget getImageViewWidget() {
        return imageViewWidget;
    }

    public void setImageViewWidget(ImageViewWidget imageViewWidget) {
        this.imageViewWidget = imageViewWidget;
    }

    public String getAdditionMood() {
        return additionMood;
    }

    public void setAdditionMood(String additionMood) {
        this.additionMood = additionMood;
    }
}
