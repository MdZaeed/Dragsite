package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import bs23.com.dragsite.R;
import bs23.com.dragsite.widgets.GalleryViewWidget;

/**
 * Created by BS-86 on 5/11/2016.
 */
public class GalleryEditCaptionFragment extends BaseSecondLevelEditFragment {

    GalleryEditFragment galleryEditFragment;
    private Switch hoverSwitch;
    private Spinner captionTypeSpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery_captions, container, false);
    }

    public static GalleryEditCaptionFragment newInstance() {
        return new GalleryEditCaptionFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        galleryEditFragment = (GalleryEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        hoverSwitch=(Switch) view.findViewById(R.id.switch_caption_hover_gallery);
        hoverSwitch.setChecked(galleryEditFragment.getGalleryViewWidget().isThumbnailCaptionHovering());
        hoverSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                galleryEditFragment.getGalleryViewWidget().setThumbnailCaptionHovering(isChecked);
            }
        });

        final List<String> typeList=Arrays.asList(GalleryViewWidget.typeArray);
        ArrayAdapter<String> typeAdapter= new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, typeList);
        captionTypeSpinner=(Spinner) view.findViewById(R.id.sp_caption_hover_type_gallery);
        captionTypeSpinner.setAdapter(typeAdapter);
        captionTypeSpinner.setSelection(typeList.indexOf(galleryEditFragment.getGalleryViewWidget().getThumbnailCaptionType()));
        captionTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                galleryEditFragment.getGalleryViewWidget().setThumbnailCaptionType(typeList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
