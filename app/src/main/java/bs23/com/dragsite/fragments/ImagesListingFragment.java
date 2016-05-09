package bs23.com.dragsite.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import bs23.com.dragsite.adapter.BaseGalleryAdapter;
import bs23.com.dragsite.adapter.ImagesAdapter;
import bs23.com.dragsite.model.ImageSelectModel;

/**
 * Created by BS-86 on 5/5/2016.
 */
public abstract class ImagesListingFragment extends BaseSecondLevelEditFragment implements BaseGalleryAdapter.CameraClick {

    protected List<ImageSelectModel> imageFiles;
    protected ImagesAdapter imagesAdapter;
    protected File photoFile;
    protected RecyclerView recyclerView;


    @Nullable
    @Override
    public  abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


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
                if((fileEntry.getName().endsWith(".jpg") || fileEntry.getName().endsWith(".png")) && (fileEntry.length()/1024)!=0) {
                    imageFiles.add(new ImageSelectModel(fileEntry,false,2));
                }
                System.out.println(fileEntry.getName());
            }
        }
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    protected void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            // Create the File where the photo should go
            photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }

        }
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

    private void clearImageFilesSelection()
    {
        for(ImageSelectModel imageSelectModel: imageFiles)
        {
            imageSelectModel.setSelected(false);
        }
    }


    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File imageTemp = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + imageTemp.getAbsolutePath();
        return imageTemp;
    }

    protected File createTheCameraImage()
    {
        File f;
        try
        {
            f=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/Image.jpg");
            InputStream inputStream = getContext().getResources().openRawResource(R.drawable.youtube_play);
            OutputStream out=new FileOutputStream(f);
            byte buf[]=new byte[1024];
            int len;
            while((len=inputStream.read(buf))>0)
                out.write(buf,0,len);
            out.close();
            inputStream.close();
        }
        catch (IOException e){
            f=null;
        }

        return f;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageFiles = new ArrayList<>();

        if (isExternalStorageReadable()) {
            imageFiles.add(new ImageSelectModel(createTheCameraImage(), false, 1));
            listFilesForFolder(Environment.getExternalStorageDirectory());
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_images);

        view.post(new Runnable() {
            @Override
            public void run() {
                addRecyclerView(view.getWidth());
            }
        });


    }

    public void onCameraClick() {
        dispatchTakePictureIntent();
    }

    protected abstract void addRecyclerView(int width);
}
