package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import bs23.com.dragsite.R;

/**
 * Created by BS-86 on 4/28/2016.
 */
public class ImageEditLinkURLFragment extends BaseSecondLevelEditFragment {

    EditText linkEditText;
    TextInputLayout linkTextInputLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery_single_image_captions, container, false);
    }

    public static ImageEditLinkURLFragment newInstance() {
        return new ImageEditLinkURLFragment();
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ImageEditFragment imageEditFragment = (ImageEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        linkEditText=(EditText) view.findViewById(R.id.et_single_image_caption_gallery);
        linkTextInputLayout=(TextInputLayout) view.findViewById(R.id.til_caption_or_link);

        linkTextInputLayout.setHint("URL");
        linkEditText.setText(imageEditFragment.getImageViewWidget().getURL());
        linkEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                imageEditFragment.getImageViewWidget().setURL(linkEditText.getText().toString());
                return false;
            }
        });
    }
}
