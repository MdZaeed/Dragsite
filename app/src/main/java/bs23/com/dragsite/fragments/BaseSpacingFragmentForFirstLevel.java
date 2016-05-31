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
public abstract class BaseSpacingFragmentForFirstLevel extends BaseEditFragment {

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
                ownWidget.setSpacingAboveAndUi(position* getPerCategoryDifference());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spacingBelowSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ownWidget.setSpacingBelowAndUi(position* getPerCategoryDifference());
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
