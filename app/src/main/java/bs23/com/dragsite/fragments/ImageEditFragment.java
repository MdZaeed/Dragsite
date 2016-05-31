package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import bs23.com.dragsite.MainActivity;
import bs23.com.dragsite.R;
import bs23.com.dragsite.model.StyleChange;
import bs23.com.dragsite.utils.JsonKeys;
import bs23.com.dragsite.widgets.ImageViewWidget;

/**
 * Created by BrainStation on 4/15/16.
 */
public class ImageEditFragment extends BaseEditFragment {

    Button editImageButton;
    Button addImageButton;
    private ImageViewWidget imageViewWidget;
    private String additionMood="ADD IMAGE";
    private EditText captionEditText;

    public static ImageEditFragment newInstance(Bundle styleBundle) {
        ImageEditFragment imageEditFragment=new ImageEditFragment();
        imageEditFragment.setArguments(styleBundle);
        return imageEditFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_widget, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editImageButton=(Button) view.findViewById(R.id.btn_edit_image);



        addImageButton=(Button) view.findViewById(R.id.btn_add_image);
        if (getImageViewWidget().findViewById(R.id.tv_image_widget).getVisibility()==View.GONE)
        {
            editImageButton.setVisibility(View.VISIBLE);
            addImageButton.setText("REPLACE IMAGE");
            additionMood="REPLACE IMAGE";
        }else
        {
            editImageButton.setVisibility(View.GONE);
            addImageButton.setText("ADD IMAGE");
            additionMood="ADD IMAGE";
        }

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageEditReplaceImageFragment imageEditReplaceImageFragment= ImageEditReplaceImageFragment.newInstance();
                imageEditReplaceImageFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0,android.R.anim.slide_in_left,0)
                        .replace(((MainActivity)getActivity()).getBottomPaneLinearLayout().getId(), imageEditReplaceImageFragment)
                        .addToBackStack("null")
                        .commit();
            }
        });

        Button spacingButton = (Button) view.findViewById(R.id.btn_spacing_image);
        spacingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt(JsonKeys.WIDGET_IDS,getArguments().getInt(JsonKeys.WIDGET_IDS));
                bundle.putInt(JsonKeys.CommonKeys.IMAGE_WIDGET_SPACING_ABOVE,getArguments().getInt(JsonKeys.CommonKeys.IMAGE_WIDGET_SPACING_ABOVE));
                bundle.putInt(JsonKeys.CommonKeys.IMAGE_WIDGET_SPACING_BELOW,getArguments().getInt(JsonKeys.CommonKeys.IMAGE_WIDGET_SPACING_BELOW));
                bundle.putInt(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_LEFT,getArguments().getInt(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_LEFT));
                bundle.putInt(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_RIGHT,getArguments().getInt(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_RIGHT));
                ImageEditSpacingFragment imageEditSpacingFragment=ImageEditSpacingFragment.newInstance(bundle);
/*                imageEditSpacingFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0,android.R.anim.slide_in_left,0)
                        .replace(((MainActivity)getActivity()).getBottomPaneLinearLayout().getId(), imageEditSpacingFragment)
                        .addToBackStack("null")
                        .commit();*/
                swapFragments(imageEditSpacingFragment);
            }
        });

        Button advancedOptionButton = (Button) view.findViewById(R.id.btn_image_edit_advanced);
        advancedOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt(JsonKeys.WIDGET_IDS,getArguments().getInt(JsonKeys.WIDGET_IDS));
                bundle.putString(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_ALTERNATIVE_TEXT,getArguments().getString(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_ALTERNATIVE_TEXT));
                ImageEditAdvancedFragment imageEditAdvancedFragment=ImageEditAdvancedFragment.newInstance(bundle);
/*                imageEditAdvancedFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0,android.R.anim.slide_in_left,0)
                        .replace(((MainActivity)getActivity()).getBottomPaneLinearLayout().getId(), imageEditAdvancedFragment)
                        .addToBackStack("null")
                        .commit();*/
                swapFragments(imageEditAdvancedFragment);
            }
        });

        captionEditText=(EditText) view.findViewById(R.id.et_image_caption);
        captionEditText.setText(getArguments().getString(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_CAPTION));
        captionEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
/*
                getImageViewWidget().setCaptionStringAndUI(captionEditText.getText().toString());
*/

                ((MainActivity)getActivity()).changeStyle(new StyleChange(getArguments().getInt(JsonKeys.WIDGET_IDS), JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_CAPTION,captionEditText.getText().toString()));
                return false;
            }
        });

        Button linkButton = (Button) view.findViewById(R.id.btn_image_link);
        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageEditLinkFragment imageEditLinkFragment=ImageEditLinkFragment.newInstance();
/*                imageEditLinkFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0,android.R.anim.slide_in_left,0)
                        .replace(((MainActivity)getActivity()).getBottomPaneLinearLayout().getId(), imageEditLinkFragment)
                        .addToBackStack("null")
                        .commit();*/
                swapFragments(imageEditLinkFragment);
            }
        });
    }

    public ImageViewWidget getImageViewWidget() {
        return imageViewWidget;
    }

    public void setImageViewWidget(ImageViewWidget imageViewWidget) {
        this.imageViewWidget = imageViewWidget;
    }

    public String getAdditionMood() {
        return additionMood;
    }

    public void setAdditionMood(String additionMood) {
        this.additionMood = additionMood;
    }
}
