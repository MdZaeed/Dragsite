package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import bs23.com.dragsite.MainActivity;
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

        Button setURLButton = (Button) view.findViewById(R.id.btn_set_url_image);
        setURLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryEditLinkURLFragment galleryEditLinkURLFragment = GalleryEditLinkURLFragment.newInstance();
                galleryEditLinkURLFragment.setFragmentManager1(getFragmentManager1());
                galleryEditLinkURLFragment.setDataPosition(dataPosition);
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0, android.R.anim.slide_in_left, 0)
                        .replace(((MainActivity) getActivity()).getBottomPaneLinearLayout().getId(), galleryEditLinkURLFragment)
                        .addToBackStack("null")
                        .commit();
            }
        });

        final Button removeLinkButton=(Button) view.findViewById(R.id.btn_remove_link);
        if(galleryEditFragment.getGalleryViewWidget().getImageSelectModels().get(dataPosition).getLink().equals(""))
        {
            removeLinkButton.setTextColor(getContext().getResources().getColor(android.R.color.darker_gray));
        }else
        {
            removeLinkButton.setTextColor(getContext().getResources().getColor(android.R.color.holo_blue_dark));
        }

        removeLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryEditFragment.getGalleryViewWidget().getImageSelectModels().get(dataPosition).setLink("");
                removeLinkButton.setTextColor(getContext().getResources().getColor(android.R.color.darker_gray));
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
