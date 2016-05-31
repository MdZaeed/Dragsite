package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bs23.com.dragsite.R;

/**
 * Created by BS-86 on 4/28/2016.
 */
public class GalleryEditSpacingFragment extends BaseSpacingFragmentForSecondLevel {

    GalleryEditFragment galleryEditFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_spacing_gallery_slideshow_button_youtube_social, container, false);
    }

    public static GalleryEditSpacingFragment newInstance() {
        return new GalleryEditSpacingFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        galleryEditFragment=(GalleryEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        ownWidget=galleryEditFragment.getGalleryViewWidget();

        super.onViewCreated(view, savedInstanceState);

/*
*//*        String[] some_array = getResources().getStringArray(R.array.youtube_video_margins);
        final List<String> spacingList=Arrays.asList(some_array);

        spacingAboveSpinner.setSelection(spacingList.indexOf(ownWidget.getYoutubeWidget().getSpacingAbove()));
        spacingBelowSpinner.setSelection(spacingList.indexOf(ownWidget.getYoutubeWidget().getSpacingBelow()));*//*

        spacingAboveSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                youtubeEditFragment.getYoutubeWidget().setSpacingAboveAndUi(position*perCategoryDifference);
*//*
                ownWidget.getYoutubeWidget().setSpacingAboveAndUi(spacingList.get(position));
*//*
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spacingBelowSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                youtubeEditFragment.getYoutubeWidget().setSpacingBelowAndUi(position*perCategoryDifference);
*//*
                ownWidget.getYoutubeWidget().setSpacingBelowAndUi(spacingList.get(position));
*//*
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }
}
