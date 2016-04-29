package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import bs23.com.dragsite.R;
import bs23.com.dragsite.widgets.DividerWidget;

/**
 * Created by BS-86 on 4/28/2016.
 */
public class DividerEditFragment extends BaseEditFragment {

    Spinner spacingAboveSpinner;
    Spinner spacingBelowSpinner;
    Spinner widthPercentage;
    private DividerWidget dividerWidget;
    private int perCategoryDifference=10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_divider, container, false);
    }

    public static DividerEditFragment newInstance() {
        return new DividerEditFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spacingAboveSpinner=(Spinner) view.findViewById(R.id.sp_space_above_divider);
        spacingBelowSpinner=(Spinner) view.findViewById(R.id.sp_spacing_below_divider);
        widthPercentage=(Spinner) view.findViewById(R.id.sp_width_divider);

        spacingAboveSpinner.setSelection((getDividerWidget().getSpacingAbove()/perCategoryDifference)-1);
        spacingBelowSpinner.setSelection((getDividerWidget().getSpacingBelow()/perCategoryDifference)-1);
        if((getDividerWidget()).getWidthPercentage()<50)
        {
            widthPercentage.setSelection(0);
        }else
        {
            widthPercentage.setSelection(((getDividerWidget()).getWidthPercentage()/10)-4);
        }


        spacingAboveSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getDividerWidget().setSpacingAbove((position*perCategoryDifference)+perCategoryDifference);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spacingBelowSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getDividerWidget().setSpacingBelow((position*perCategoryDifference)+perCategoryDifference);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        widthPercentage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    getDividerWidget().setWidthPercentage(0);
                }else
                {
                    getDividerWidget().setWidthPercentage((position*10)+40);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public DividerWidget getDividerWidget() {
        return dividerWidget;
    }

    public void setDividerWidget(DividerWidget dividerWidget) {
        this.dividerWidget = dividerWidget;
    }
}
