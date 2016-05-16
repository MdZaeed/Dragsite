package bs23.com.dragsite.fragments;

import android.app.Activity;
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

import bs23.com.dragsite.R;
import bs23.com.dragsite.adapter.GalleryAdapterWithMultiSelection;
import bs23.com.dragsite.model.ImageSelectGalleryElementModel;
import bs23.com.dragsite.model.ImageSelectModel;

/**
 * Created by BS-86 on 5/9/2016.
 */
public class GalleryEditAddImagesFragment extends ImagesListingFragment {

    GalleryAdapterWithMultiSelection imagesAdapter;
    private Button replaceImageButton;
    private List<ImageSelectGalleryElementModel> galleryFiles;
    GalleryEditFragment galleryEditFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_image_or_gallery, container, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1  && resultCode== Activity.RESULT_OK) {
            imageFiles.add(1,new ImageSelectModel(photoFile,true,2));
            imagesAdapter.setImageFiles(imageFiles);
            imagesAdapter.notifyDataSetChanged();
        }
    }

    public static GalleryEditAddImagesFragment newInstance() {
        return new GalleryEditAddImagesFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        galleryEditFragment = (GalleryEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        replaceImageButton = (Button) view.findViewById(R.id.btn_special_header_button);

        replaceImageButton.setText("Add Images");
        replaceImageButton.setVisibility(View.VISIBLE);
        replaceImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageClick();
            }
        });

        galleryFiles=new ArrayList<>();
    }

    private void saveImageClick() {
        galleryFiles=galleryEditFragment.getGalleryViewWidget().getImageSelectModels();
        for (ImageSelectModel imageSelectModel : imageFiles) {
            if (imageSelectModel.isSelected()) {
                ImageSelectModel imageSelectModelTemp=imageSelectModel;
                imageSelectModelTemp.VIEW_TYPE=1;
                galleryFiles.add(new ImageSelectGalleryElementModel(imageSelectModel.getFile(),imageSelectModel.isSelected(),imageSelectModel.VIEW_TYPE));
            }
        }

        galleryEditFragment.getGalleryViewWidget().setImageSelectModels(galleryFiles);
        super.backButtonClick();
    }

    @Override
    protected void addRecyclerView(int width) {
        int columnCount = 3;
        int spanPerColumn = width / columnCount;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnCount));
        imagesAdapter = new GalleryAdapterWithMultiSelection(getContext(), imageFiles);
        imagesAdapter.setImageSize(spanPerColumn);
        recyclerView.setAdapter(imagesAdapter);
        imagesAdapter.setCameraClick(this);
    }
}
