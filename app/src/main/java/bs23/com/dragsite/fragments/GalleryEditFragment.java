package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import bs23.com.dragsite.MainActivity;
import bs23.com.dragsite.R;
import bs23.com.dragsite.widgets.GalleryViewWidget;

/**
 * Created by BS-86 on 4/28/2016.
 */
public class GalleryEditFragment extends BaseEditFragment {

    Button spacingButton;
    private GalleryViewWidget galleryViewWidget;
    private Button manageImagesButton;
    private Spinner coloumnNumberSpinner;
    private Button captionButton;
    private Button advancedButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery_edit, container, false);
    }

    public static GalleryEditFragment newInstance() {
        return new GalleryEditFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spacingButton=(Button) view.findViewById(R.id.btn_gallery_spacing);
        spacingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryEditSpacingFragment galleryEditSpacingFragment=GalleryEditSpacingFragment.newInstance();
                galleryEditSpacingFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0,android.R.anim.slide_in_left,0)
                        .replace(((MainActivity)getActivity()).getBottomPaneLinearLayout().getId(), galleryEditSpacingFragment)
                        .addToBackStack("null")
                        .commit();
            }
        });

        manageImagesButton=(Button) view.findViewById(R.id.btn_manage_images_gallery);
        manageImagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryEditManageImagesFragment galleryEditManageImagesFragment=GalleryEditManageImagesFragment.newInstance();
                galleryEditManageImagesFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0,android.R.anim.slide_in_left,0)
                        .replace(((MainActivity)getActivity()).getBottomPaneLinearLayout().getId(), galleryEditManageImagesFragment)
                        .addToBackStack("null")
                        .commit();
            }
        });

        coloumnNumberSpinner=(Spinner) view.findViewById(R.id.sp_no_of_coloumns_gallery);
        coloumnNumberSpinner.setSelection(galleryViewWidget.getNoOfColoumns()-2);
        coloumnNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                galleryViewWidget.setNoOfColoumns(position+2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        captionButton=(Button) view.findViewById(R.id.btn_captions_gallery);
        captionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryEditCaptionFragment galleryEditCaptionFragment=GalleryEditCaptionFragment.newInstance();
                galleryEditCaptionFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0,android.R.anim.slide_in_left,0)
                        .replace(((MainActivity)getActivity()).getBottomPaneLinearLayout().getId(), galleryEditCaptionFragment)
                        .addToBackStack("null")
                        .commit();
            }
        });

        advancedButton=(Button) view.findViewById(R.id.btn_advanced_gallery);
        advancedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryEditAdvancedFragment galleryEditAdvancedFragment=GalleryEditAdvancedFragment.newInstance();
                galleryEditAdvancedFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0,android.R.anim.slide_in_left,0)
                        .replace(((MainActivity)getActivity()).getBottomPaneLinearLayout().getId(), galleryEditAdvancedFragment)
                        .addToBackStack("null")
                        .commit();
            }
        });
    }

    public GalleryViewWidget getGalleryViewWidget() {
        return galleryViewWidget;
    }

    public void setGalleryViewWidget(GalleryViewWidget galleryViewWidget) {
        this.galleryViewWidget = galleryViewWidget;
    }
}
