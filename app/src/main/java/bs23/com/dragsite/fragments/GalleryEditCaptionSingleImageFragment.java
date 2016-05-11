package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import bs23.com.dragsite.R;

/**
 * Created by BS-86 on 5/11/2016.
 */
public class GalleryEditCaptionSingleImageFragment extends BaseSecondLevelEditFragment {

    EditText captionEditText;
    GalleryEditFragment galleryEditFragment;
    private int dataPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery_single_image_captions, container, false);
    }

    public static GalleryEditCaptionSingleImageFragment newInstance() {
        return new GalleryEditCaptionSingleImageFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        galleryEditFragment = (GalleryEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        captionEditText=(EditText) view.findViewById(R.id.et_single_image_caption_gallery);
        captionEditText.setText(galleryEditFragment.getGalleryViewWidget().getImageSelectModels().get(dataPosition).getCaption());
        captionEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                galleryEditFragment.getGalleryViewWidget().getImageSelectModels().get(dataPosition).setCaption(captionEditText.getText().toString());
                return false;
            }
        });
    }

    public int getDataPosition() {
        return dataPosition;
    }

    public void setDataPosition(int dataPosition) {
        this.dataPosition = dataPosition;
    }
}
