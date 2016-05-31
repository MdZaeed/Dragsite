package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import bs23.com.dragsite.MainActivity;
import bs23.com.dragsite.R;
import bs23.com.dragsite.model.StyleChange;
import bs23.com.dragsite.utils.JsonKeys;
import bs23.com.dragsite.widgets.ImageViewWidget;

/**
 * Created by BS-86 on 4/28/2016.
 */
public class ImageEditSpacingFragment extends BaseSpacingFragmentForSecondLevel {

    ImageEditFragment imageEditFragment;
    private Spinner spacingRightSpinner;
    private Spinner spacingLeftSpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_spacing, container, false);
    }

    public static ImageEditSpacingFragment newInstance(Bundle bundle) {
        ImageEditSpacingFragment imageEditSpacingFragment = new ImageEditSpacingFragment();
        imageEditSpacingFragment.setArguments(bundle);
        return imageEditSpacingFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        imageEditFragment = (ImageEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        ownWidget = imageEditFragment.getImageViewWidget();

        super.onViewCreated(view, savedInstanceState);

        spacingLeftSpinner = (Spinner) view.findViewById(R.id.sp_spacing_left);
        spacingRightSpinner = (Spinner) view.findViewById(R.id.sp_spacing_right);

        spacingRightSpinner.setSelection((getArguments().getInt(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_RIGHT) / getPerCategoryDifference()));
        spacingLeftSpinner.setSelection((getArguments().getInt(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_LEFT) / getPerCategoryDifference()));

        spacingLeftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
/*
                ((ImageViewWidget)ownWidget).setSpacingLeftAndUi(position* getPerCategoryDifference());
*/
/*
                ((MainActivity) getActivity()).changeStyle(new StyleChange(getArguments().getInt(JsonKeys.WIDGET_IDS),JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_LEFT,position*getPerCategoryDifference()+""));
*/
                styleChangeRequest(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_LEFT, position * getPerCategoryDifference() + "",1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spacingRightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
/*
                ((ImageViewWidget)ownWidget).setSpacingRightAndUi(position* getPerCategoryDifference());
*/
/*
                ((MainActivity) getActivity()).changeStyle(new StyleChange(getArguments().getInt(JsonKeys.WIDGET_IDS),JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_RIGHT,position*getPerCategoryDifference()+""));
*/
                styleChangeRequest(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_RIGHT, position * getPerCategoryDifference() + "",1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
