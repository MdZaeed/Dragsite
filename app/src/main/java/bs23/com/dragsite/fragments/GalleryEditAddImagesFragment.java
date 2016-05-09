package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bs23.com.dragsite.R;
import bs23.com.dragsite.adapter.BaseGalleryAdapter;
import bs23.com.dragsite.adapter.ImagesAdapter;
import bs23.com.dragsite.model.ImageSelectModel;

/**
 * Created by BS-86 on 5/9/2016.
 */
public class GalleryEditAddImagesFragment extends ImagesListingFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_image_or_gallery, container, false);
    }

    public static GalleryEditAddImagesFragment newInstance() {
        return new GalleryEditAddImagesFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected void addRecyclerView(int width) {
        int columnCount = 3;
        int spanPerColumn = width / columnCount;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnCount));
        imagesAdapter = new ImagesAdapter(getContext(), imageFiles);
        imagesAdapter.setImageSize(spanPerColumn);
        recyclerView.setAdapter(imagesAdapter);
        imagesAdapter.setCameraClick(this);
    }
}
