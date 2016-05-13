package bs23.com.dragsite.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import bs23.com.dragsite.MainActivity;
import bs23.com.dragsite.R;
import bs23.com.dragsite.adapter.BaseGalleryAdapterCopyExtended;

/**
 * Created by BS-86 on 5/9/2016.
 */
public class GalleryEditManageImagesFragment extends ImagesListingFragment implements BaseGalleryAdapterCopyExtended.OnSingleImageClicked {

    BaseGalleryAdapterCopyExtended baseGalleryAdapterCopyExtended;
    private Button addImageButton;
    GalleryEditFragment galleryEditFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_image_or_gallery, container, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public static GalleryEditManageImagesFragment newInstance() {
        return new GalleryEditManageImagesFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        galleryEditFragment = (GalleryEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        super.onViewCreated(view, savedInstanceState);

        addImageButton = (Button) view.findViewById(R.id.btn_special_header_button);
        addImageButton.setVisibility(View.VISIBLE);
        addImageButton.setText("Add Images");
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryEditAddImagesFragment galleryEditAddImagesFragment = GalleryEditAddImagesFragment.newInstance();
                galleryEditAddImagesFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0, android.R.anim.slide_in_left, 0)
                        .replace(((MainActivity) getActivity()).getBottomPaneLinearLayout().getId(), galleryEditAddImagesFragment)
                        .addToBackStack("null")
                        .commit();
            }
        });
    }

    @Override
    protected void addRecyclerView(int width) {
        int columnCount = 3;
        int spanPerColumn = width / columnCount;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnCount));
        baseGalleryAdapterCopyExtended = new BaseGalleryAdapterCopyExtended(getContext(), imageFiles,spanPerColumn);
        baseGalleryAdapterCopyExtended.setOnSingleImageClicked(this);
        recyclerView.setAdapter(baseGalleryAdapterCopyExtended);
    }

    @Override
    protected void createTheGalleryFiles() {
        imageFiles = new ArrayList<>();
        imageFiles = galleryEditFragment.getGalleryViewWidget().getImageSelectModels();
    }

    @Override
    public void onSigleImageDeleteClick() {
        galleryEditFragment.getGalleryViewWidget().setImageSelectModels(baseGalleryAdapterCopyExtended.getImageFiles());
    }

    @Override
    public void onSingleImageCaptionClick(int posiiton) {
        GalleryEditCaptionSingleImageFragment galleryEditCaptionSingleImageFragment = GalleryEditCaptionSingleImageFragment.newInstance();
        galleryEditCaptionSingleImageFragment.setFragmentManager1(getFragmentManager1());
        galleryEditCaptionSingleImageFragment.setDataPosition(posiiton);
        getFragmentManager1().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, 0, android.R.anim.slide_in_left, 0)
                .replace(((MainActivity) getActivity()).getBottomPaneLinearLayout().getId(), galleryEditCaptionSingleImageFragment)
                .addToBackStack("null")
                .commit();
    }

    @Override
    public void onSIngleImageLinkClick(int posiiton) {
        GalleryEditLinkSingleImageFragment galleryEditLinkSingleImageFragment = GalleryEditLinkSingleImageFragment.newInstance();
        galleryEditLinkSingleImageFragment.setFragmentManager1(getFragmentManager1());
        galleryEditLinkSingleImageFragment.setDataPosition(posiiton);
        getFragmentManager1().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, 0, android.R.anim.slide_in_left, 0)
                .replace(((MainActivity) getActivity()).getBottomPaneLinearLayout().getId(), galleryEditLinkSingleImageFragment)
                .addToBackStack("null")
                .commit();
    }
}
