package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import bs23.com.dragsite.R;

/**
 * Created by BS-86 on 4/28/2016.
 */
public class ImageEditAdvancedFragment extends BaseSecondLevelEditFragment {

    Spinner borderSizeSpinner;
    Spinner borderColorSpinner;
    EditText altEditText;
    int perCategoryDifference=2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_advanced, container, false);
    }

    public static ImageEditAdvancedFragment newInstance() {
        return new ImageEditAdvancedFragment();
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ImageEditFragment imageEditFragment = (ImageEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        borderSizeSpinner =(Spinner) view.findViewById(R.id.sp_image_border_size);

        borderSizeSpinner.setSelection(imageEditFragment.getImageViewWidget().getBorderSize()/perCategoryDifference);

        borderSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageEditFragment.getImageViewWidget().setBorderSize(position*2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        borderColorSpinner=(Spinner) view.findViewById(R.id.sp_image_border_color);

        borderColorSpinner.setSelection(imageEditFragment.getImageViewWidget().getBorderColor());

        borderColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageEditFragment.getImageViewWidget().setBorderColor(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        altEditText=(EditText) view.findViewById(R.id.et_alt_image_text);
        altEditText.setText(imageEditFragment.getImageViewWidget().getAlternateText());

        altEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                imageEditFragment.getImageViewWidget().setAlternateText(altEditText.getText().toString());
                return false;
            }
        });
    }
}
