package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.Arrays;
import java.util.List;

import bs23.com.dragsite.R;
import bs23.com.dragsite.widgets.GalleryViewWidget;

/**
 * Created by BS-86 on 5/11/2016.
 */
public class GalleryEditAdvancedFragment extends BaseSecondLevelEditFragment {

    GalleryEditFragment galleryEditFragment;
    Spinner borderSpinner;
    Spinner spacingSpinner;
    int perCategoryDiffForSpacing=3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery_advanced, container, false);
    }

    public static GalleryEditAdvancedFragment newInstance() {
        return new GalleryEditAdvancedFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        galleryEditFragment = (GalleryEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        borderSpinner=(Spinner) view.findViewById(R.id.sp_border_gallery);
        borderSpinner.setSelection(galleryEditFragment.getGalleryViewWidget().getBorderSize()/2);
        borderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                galleryEditFragment.getGalleryViewWidget().setBorderSize(position*2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spacingSpinner=(Spinner) view.findViewById(R.id.sp_spacing_gallery_item);
        spacingSpinner.setSelection(galleryEditFragment.getGalleryViewWidget().getSpacingOfIndividualElement()/perCategoryDiffForSpacing);
        spacingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                galleryEditFragment.getGalleryViewWidget().setSpacingOfIndividualElement(position*perCategoryDiffForSpacing);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
