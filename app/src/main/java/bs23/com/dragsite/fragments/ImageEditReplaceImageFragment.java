package bs23.com.dragsite.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import bs23.com.dragsite.R;
import bs23.com.dragsite.RecommendedStoreAdapter;
import bs23.com.dragsite.adapter.ImagesAdapter;
import bs23.com.dragsite.model.ImageSelectModel;

/**
 * Created by BrainStation on 4/15/16.
 */
public class ImageEditReplaceImageFragment extends ImagesFragment implements ImagesAdapter.CameraClick {

    RecyclerView recyclerView;
    private TextView textView;
    private ImageView imageView;

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

        ImageEditFragment imageEditFragment = (ImageEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        textView=(TextView) imageEditFragment.getImageViewWidget().findViewById(R.id.tv_image_widget);
        imageView=(ImageView) imageEditFragment.getImageViewWidget().findViewById(R.id.iv_image_widget);

        imageFiles=new ArrayList<>();

        if(isExternalStorageReadable()) {
            imageFiles.add(new ImageSelectModel(createTheCameraImage(),false,1));
            listFilesForFolder(Environment.getExternalStorageDirectory());
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_images);

        view.post(new Runnable() {
            @Override
            public void run() {
                Log.d("Width: " , view.getWidth() + "");

                addRecyclerView(view.getWidth());
            }
        });

    }

    private void addRecyclerView(int width) {
        int columnCount = 3;
        int spanPerColumn=width/ columnCount;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnCount));
        imagesAdapter = new ImagesAdapter(getContext(), imageFiles);
        imagesAdapter.setImageSize(spanPerColumn);
        recyclerView.setAdapter(imagesAdapter);
        imagesAdapter.setCameraClick(this);
    }

    @Override
    protected void backButtonClick() {
        for(ImageSelectModel imageSelectModel : imageFiles)
        {
            if(imageSelectModel.isSelected())
            {
                textView.setVisibility(View.GONE);
                Picasso.with(getContext()).load(imageSelectModel.getFile()).into(imageView);
                imageView.setVisibility(View.VISIBLE);
                super.backButtonClick();
                return;
            }
        }

        imageView.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
        super.backButtonClick();
    }

    @Override
    public void onCameraClick() {
        dispatchTakePictureIntent();
    }
}
