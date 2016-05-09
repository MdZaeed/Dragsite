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
import java.util.List;

import bs23.com.dragsite.MainActivity;
import bs23.com.dragsite.R;
import bs23.com.dragsite.adapter.BaseGalleryAdapter;

/**
 * Created by BS-86 on 5/9/2016.
 */
public class GalleryEditManageImagesFragment extends ImagesListingFragment {

    BaseGalleryAdapter imagesAdapter;
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

        addImageButton=(Button) view.findViewById(R.id.btn_special_header_button);
        addImageButton.setVisibility(View.VISIBLE);
        addImageButton.setText("Add Images");
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryEditAddImagesFragment galleryEditAddImagesFragment=GalleryEditAddImagesFragment.newInstance();
                galleryEditAddImagesFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0,android.R.anim.slide_in_left,0)
                        .replace(((MainActivity)getActivity()).getBottomPaneLinearLayout().getId(), galleryEditAddImagesFragment)
                        .addToBackStack("null")
                        .commit();
            }
        });
    }

    @Override
    protected void addRecyclerView(int width) {
        int columnCount = 2;
        int spanPerColumn = width / columnCount;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnCount));
        imagesAdapter = new BaseGalleryAdapter(getContext(), imageFiles);
        imagesAdapter.setImageSize(spanPerColumn);
        recyclerView.setAdapter(imagesAdapter);
        imagesAdapter.setCameraClick(this);
    }

    @Override
    protected void createTheGalleryFiles() {
        imageFiles=new ArrayList<>();
        imageFiles=galleryEditFragment.getGalleryViewWidget().getImageSelectModels();
    }

    public void onCameraClick() {
    }
}
