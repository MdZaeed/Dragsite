package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import bs23.com.dragsite.R;
import bs23.com.dragsite.RecommendedStoreAdapter;
import bs23.com.dragsite.adapter.ImagesAdapter;
import bs23.com.dragsite.model.ImageSelectModel;

/**
 * Created by BrainStation on 4/15/16.
 */
public class ImageEditReplaceImageFragment extends BaseSecondLevelEditFragment {

    List<ImageSelectModel> imageFiles;
    RecyclerView recyclerView;
    private int coloumnCount=3;
    private TextView textView;
    private ImageView imageView;
    private ImageEditFragment imageEditFragment;

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

        imageEditFragment=(ImageEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        textView=(TextView) imageEditFragment.getImageViewWidget().findViewById(R.id.tv_image_widget);
        imageView=(ImageView) imageEditFragment.getImageViewWidget().findViewById(R.id.iv_image_widget);

        imageFiles=new ArrayList<>();

        if(isExternalStorageReadable()) {
/*
            File file =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
*/
/*            for (String filename : file.list()) {
                Log.d("File name: ", filename);
            }*/

            listFilesForFolder(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
            listFilesForFolder(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_images);

/*        ViewGroup.LayoutParams layoutParams=(ViewGroup.LayoutParams) view.getLayoutParams();
        Log.d("Width: " ,layoutParams.width + "");*/

        view.post(new Runnable() {
            @Override
            public void run() {
                Log.d("Width: " , view.getWidth() + "");

                addRecyclerView(view.getWidth());
            }
        });
/*        recyclerView = (RecyclerView) view.findViewById(R.id.rv_images);
*//*        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);*//*
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ImagesAdapter imagesAdapter = new ImagesAdapter(getContext(), imageFiles);
        recyclerView.setAdapter(imagesAdapter);*/
/*
        view.post(new Runnable() {
            @Override
            public void run() {
                Log.d("Width: " , view.getWidth() + "");
            }
        });*/

    }

    private void addRecyclerView(int width) {
/*        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);*/
        int spanPerColoumn=width/coloumnCount;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), coloumnCount));
        ImagesAdapter imagesAdapter = new ImagesAdapter(getContext(), imageFiles);
        imagesAdapter.setImageSize(spanPerColoumn);
        recyclerView.setAdapter(imagesAdapter);
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                if(!fileEntry.getName().startsWith(".")) {
                    listFilesForFolder(fileEntry);
                }
            } else {
                if(fileEntry.getName().endsWith(".jpg")) {
                    imageFiles.add(new ImageSelectModel(fileEntry,false));
                }
                System.out.println(fileEntry.getName());
            }
        }
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
}
