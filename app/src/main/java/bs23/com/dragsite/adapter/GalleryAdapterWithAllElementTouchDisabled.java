package bs23.com.dragsite.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import bs23.com.dragsite.R;
import bs23.com.dragsite.model.ImageSelectGalleryElementModel;
import bs23.com.dragsite.model.ImageSelectModel;
import bs23.com.dragsite.widgets.GalleryViewWidget;

/**
 * Created by BS-86 on 5/5/2016.
 */
public class GalleryAdapterWithAllElementTouchDisabled extends RecyclerView.Adapter {

    protected List<ImageSelectGalleryElementModel> imageFiles;
    protected Context context;
    protected int imageSize;
    private int borderSize = 0;
    private int spacingOfElemnts;
    private float scalingFactor;
    private int lowestHeight;
    private String cropType = GalleryViewWidget.cropTypeArray[1];

    public GalleryAdapterWithAllElementTouchDisabled(Context context, List<ImageSelectGalleryElementModel> imageFiles, int imageSize) {
        this.context = context;
        this.setImageFiles(imageFiles);
        setSpacingOfElemnts(dpToPx(3));
        this.imageSize = imageSize;

        scalingFactor = calculateScalingFactor();

        calculateTheLowestHeight();
    }

    private void calculateTheLowestHeight() {
        int lowestHeight = 65555;
        for (ImageSelectModel imageSelectModel : imageFiles) {
            if (getFileImageWidth(imageSelectModel.getFile()) < lowestHeight) {
                lowestHeight = getFileImageHeight(imageSelectModel.getFile());
            }
        }

        this.lowestHeight = (int) (lowestHeight * scalingFactor);
        if (this.lowestHeight<75)
        {
            this.lowestHeight=75;
        }
    }

    private float calculateScalingFactor() {
        int highestWidth = 0;
        for (ImageSelectModel imageSelectModel : imageFiles) {
            if (getFileImageWidth(imageSelectModel.getFile()) > highestWidth) {
                highestWidth = getFileImageWidth(imageSelectModel.getFile());
            }
        }

        if (highestWidth != 0) {
            Log.d("Scaling Factor: ", imageSize + "/" + highestWidth + "=" + (float) imageSize / highestWidth);
            return (float) imageSize / highestWidth;
        }
        return 1;
    }

    private int getFileImageWidth(File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        return options.outWidth;
    }

    private int getFileImageHeight(File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        return options.outHeight;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_box_no_checkbox, parent, false);
        return new CameraHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageSelectModel element = imageFiles.get(position);
        CameraHolder cameraHolder = (CameraHolder) holder;
        holder.itemView.setPadding(spacingOfElemnts, spacingOfElemnts, spacingOfElemnts, spacingOfElemnts);
        cameraHolder.getImageView().setPadding(getBorderSize(), getBorderSize(), getBorderSize(), getBorderSize());

        if (getCropType().equals(GalleryViewWidget.cropTypeArray[0])) {
            Picasso.with(context).load(element.getFile()).resize(getImageSize() - (spacingOfElemnts * 2), getImageSize() - (spacingOfElemnts * 2)).centerInside().into(cameraHolder.getImageView());
        } else if (getCropType().equals(GalleryViewWidget.cropTypeArray[1])) {
            Picasso.with(context).load(element.getFile()).resize(getImageSize() - (spacingOfElemnts * 2), getImageSize() - (spacingOfElemnts * 2)).centerCrop().into(cameraHolder.getImageView());
        } else if (getCropType().equals(GalleryViewWidget.cropTypeArray[2]))
        {
            Picasso.with(context).load(element.getFile()).resize(lowestHeight-(spacingOfElemnts*2),getImageSize()-(spacingOfElemnts*2)).centerCrop().into(cameraHolder.getImageView());
        }

/*        Log.d("height and width: " , cameraHolder.getImageView().getHeight() + " " + cameraHolder.getImageView().getWidth());
        Log.d("I height and width: " , holder.itemView.getHeight() + " " + holder.itemView.getWidth());*/

        Log.d("height and width: " , getFileImageHeight(element.getFile()) + " " + getFileImageWidth(element.getFile()));
/*        int width=(int) (getFileImageWidth(element.getFile())*scalingFactor);
        int height=(int) (getFileImageHeight(element.getFile())*scalingFactor);

        if (width<50 || height<50)
        {
            Picasso.with(context).load(element.getFile()).resize(50,50).centerInside().into(cameraHolder.getImageView());
        }
        else
        {
            Picasso.with(context).load(element.getFile()).resize(width-(spacingOfElemnts*2), height-(spacingOfElemnts*2)).centerInside().into(cameraHolder.getImageView());
        }*/

/*
        Picasso.with(context).load(element.getFile()).resize(lowestHeight-(spacingOfElemnts*2),getImageSize()-(spacingOfElemnts*2)).centerCrop().into(cameraHolder.getImageView());
*/
    }

    @Override
    public int getItemCount() {
        return imageFiles.size();
    }

    public ImageSelectModel getItem(int position) {
        return imageFiles.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).VIEW_TYPE;
    }

    public List<ImageSelectGalleryElementModel> getImageFiles() {
        return imageFiles;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    public int getSpacingOfElemnts() {
        return changePxToDp(spacingOfElemnts);
    }

    public void setSpacingOfElemnts(int spacingOfElemnts) {
        this.spacingOfElemnts = dpToPx(spacingOfElemnts);
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    protected class CameraHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;

        public CameraHolder(View itemView) {
            super(itemView);
            setImageView((ImageView) itemView.findViewById(R.id.iv_single_image));

            getImageView().setPadding(getBorderSize(), getBorderSize(), getBorderSize(), getBorderSize());
            itemView.setPadding(spacingOfElemnts, spacingOfElemnts, spacingOfElemnts, spacingOfElemnts);

            getImageView().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onGalleryImageClick(v, getAdapterPosition());
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }
    }

    public void onGalleryImageClick(View v, int position) {
    }


    public int getImageSize() {
        return imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    public void setImageFiles(List<ImageSelectGalleryElementModel> imageFiles) {
        this.imageFiles = imageFiles;
    }

    protected int changePxToDp(int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
