package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import bs23.com.dragsite.R;
import bs23.com.dragsite.widgets.BaseLinearLayoutWithSpacingNeeds;

/**
 * Created by BS-86 on 5/2/2016.
 */

/*
Steps to add spacing above and spacing below button to a widget

Step 1: extend BaseSpacingFragmentForSecondLevel from spacingEditFreagment
Step 2: on the SpaceEditFragment, find the corcerned widget from the first edit fragment and store it in the ownWidget variable;
Step 3: Extend the BaseLinearLayoutWithSpacingNeeds class from your Widget class
Step 4: set initial spacingAbove and Below and add the widget's layout in the mainView
Step 4: give the spacing Above spinner and below spinner the ids sp_spacing_above and sp_spacing_below respectively
Step 5: Dont forget to add the onclick action
 */


public abstract class BaseSpacingFragmentForSecondLevel extends BaseSecondLevelEditFragment {

    Spinner spacingAboveSpinner;
    Spinner spacingBelowSpinner;
    BaseLinearLayoutWithSpacingNeeds ownWidget;
    private int perCategoryDifference=5;

    @Nullable
    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spacingAboveSpinner=(Spinner) view.findViewById(R.id.sp_spacing_above);
        spacingBelowSpinner=(Spinner) view.findViewById(R.id.sp_spacing_below);

        spacingAboveSpinner.setSelection((ownWidget.getSpacingAbove()/ getPerCategoryDifference()));
        spacingBelowSpinner.setSelection((ownWidget.getSpacingBelow()/ getPerCategoryDifference()));

        spacingAboveSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ownWidget.setSpacingAbove(position* getPerCategoryDifference());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spacingBelowSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ownWidget.setSpacingBelow(position* getPerCategoryDifference());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public int getPerCategoryDifference() {
        return perCategoryDifference;
    }

    public void setPerCategoryDifference(int perCategoryDifference) {
        this.perCategoryDifference = perCategoryDifference;
    }
}
