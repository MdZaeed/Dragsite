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
public class GalleryEditLinkSingleImageFragment extends BaseSecondLevelEditFragment {

    GalleryEditFragment galleryEditFragment;
    private int dataPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_link_image_button, container, false);
    }

    public static GalleryEditLinkSingleImageFragment newInstance() {
        return new GalleryEditLinkSingleImageFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        galleryEditFragment = (GalleryEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);
    }

    public int getDataPosition() {
        return dataPosition;
    }

    public void setDataPosition(int dataPosition) {
        this.dataPosition = dataPosition;
    }
}
