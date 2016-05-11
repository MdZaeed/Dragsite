package bs23.com.dragsite.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bs23.com.dragsite.R;
import bs23.com.dragsite.model.ImageSelectModel;

/**
 * Created by BS-86 on 5/5/2016.
 */
public class BaseGalleryAdapterCopy extends RecyclerView.Adapter {

    protected List<ImageSelectModel> imageFiles;
    protected Context context;
    protected int imageSize;

    public BaseGalleryAdapterCopy(Context context, List<ImageSelectModel> imageFiles) {
        this.context = context;
        this.setImageFiles(imageFiles);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_box_no_checkbox, parent, false);
        return new CameraHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageSelectModel element = imageFiles.get(position);
        CameraHolder cameraHolder = (CameraHolder) holder;
        Picasso.with(context).load(element.getFile()).resize(getImageSize(), getImageSize()).centerCrop().into(cameraHolder.imageView);
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

    public List<ImageSelectModel> getImageFiles() {
        return imageFiles;
    }


    protected class CameraHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView imageView;

        public CameraHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_single_image);

            imageView.setOnClickListener(this);
/*            imageView.setEnabled(false);
            imageView.setClickable(false);
            imageView.setFocusable(false);
            itemView.setEnabled(false);
            itemView.setClickable(false);
            itemView.setFocusable(false);*/
        }

        @Override
        public void onClick(View v) {
            onGalleryImageClick(v,getAdapterPosition());
        }
    }

    public void onGalleryImageClick(View v,int position) {
    }


    public int getImageSize() {
        return imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    public void setImageFiles(List<ImageSelectModel> imageFiles) {
        this.imageFiles = imageFiles;
    }
}
