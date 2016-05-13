package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import bs23.com.dragsite.R;

/**
 * Created by BS-86 on 4/28/2016.
 */
public class GalleryEditLinkURLFragment extends BaseSecondLevelEditFragment {

    EditText linkEditText;
    TextInputLayout linkTextInputLayout;
    private int dataPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery_single_image_captions, container, false);
    }

    public static GalleryEditLinkURLFragment newInstance() {
        return new GalleryEditLinkURLFragment();
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final GalleryEditFragment galleryEditFragment = (GalleryEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        linkEditText=(EditText) view.findViewById(R.id.et_single_image_caption_gallery);
        linkTextInputLayout=(TextInputLayout) view.findViewById(R.id.til_caption_or_link);

        linkTextInputLayout.setHint("URL");
        linkEditText.setText(galleryEditFragment.getGalleryViewWidget().getImageSelectModels().get(dataPosition).getLink());
        linkEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                galleryEditFragment.getGalleryViewWidget().getImageSelectModels().get(dataPosition).setLink(linkEditText.getText().toString());
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
