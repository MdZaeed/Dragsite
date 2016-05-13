package bs23.com.dragsite.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bs23.com.dragsite.R;
import bs23.com.dragsite.adapter.ImagesAdapter;
import bs23.com.dragsite.model.ImageSelectModel;

/**
 * Created by BrainStation on 4/15/16.
 */
public class ImageEditReplaceImageFragment extends ImagesListingFragment {

    private TextView textView;
    private ImageView imageView;
    private Button replaceImageButton;
    private ImagesAdapter imagesAdapter;
    ImageEditFragment imageEditFragment;

    public static ImageEditReplaceImageFragment newInstance() {
        return new ImageEditReplaceImageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_image_or_gallery, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageEditFragment = (ImageEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        textView = (TextView) imageEditFragment.getImageViewWidget().findViewById(R.id.tv_image_widget);
        imageView = (ImageView) imageEditFragment.getImageViewWidget().findViewById(R.id.iv_image_widget);
        replaceImageButton = (Button) view.findViewById(R.id.btn_special_header_button);

        replaceImageButton.setText(imageEditFragment.getAdditionMood());
        replaceImageButton.setVisibility(View.VISIBLE);
        replaceImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageClick();
            }
        });

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

    private void saveImageClick() {

        for (ImageSelectModel imageSelectModel : imageFiles) {
            if (imageSelectModel.isSelected()) {
                imageEditFragment.getImageViewWidget().setImage(imageSelectModel);
                super.backButtonClick();
                return;
            }
        }

        imageEditFragment.getImageViewWidget().setImage(null);
        super.backButtonClick();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1  && resultCode== Activity.RESULT_OK) {
            clearImageFilesSelection();
            imageFiles.add(1,imagesAdapter.setLastElement(new ImageSelectModel(photoFile,true,2)));
            imagesAdapter.setImageFiles(imageFiles);
            imagesAdapter.notifyDataSetChanged();
        }
    }
}
