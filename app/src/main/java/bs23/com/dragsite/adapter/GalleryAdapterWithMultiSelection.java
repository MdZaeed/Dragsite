package bs23.com.dragsite.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
public class GalleryAdapterWithMultiSelection extends RecyclerView.Adapter {

    protected List<ImageSelectModel> imageFiles;
    protected Context context;
    protected int imageSize;
    protected CameraClick cameraClick;

    public GalleryAdapterWithMultiSelection(Context context, List<ImageSelectModel> imageFiles) {
        this.context = context;
        this.setImageFiles(imageFiles);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;
        switch (viewType) {
            case 1:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_box_no_checkbox, parent, false);
                return new CameraHolder(itemView);
            case 2:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_box_with_checkbox, parent, false);
                return new ImageSelectHolder(itemView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageSelectModel element = imageFiles.get(position);
        switch (element.VIEW_TYPE)
        {
            case 1:
                CameraHolder cameraHolder = (CameraHolder) holder;
                Picasso.with(context).load(element.getFile()).resize(getImageSize(), getImageSize()).centerCrop().into(cameraHolder.imageView);
                break;
            case 2:
                ImageSelectHolder elementsHolder = (ImageSelectHolder) holder;

                if (element.isSelected()) {
                    elementsHolder.checkBox.setChecked(true);
                } else {
                    elementsHolder.checkBox.setChecked(false);
                }

                Picasso.with(context).load(element.getFile()).resize(getImageSize(), getImageSize()).centerCrop().into(elementsHolder.imageView);
                break;

        }
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


    protected class ImageSelectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView imageView;
        protected CheckBox checkBox;

        public ImageSelectHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.iv_single_image);
            checkBox = (CheckBox) itemView.findViewById(R.id.cb_is_image_selected);

            itemView.setOnClickListener(this);
            checkBox.setClickable(false);
        }

        @Override
        public void onClick(View v) {
            onImageClick(getAdapterPosition());
        }
    }

    protected void onImageClick(int position)
    {
        if (getItem(position).isSelected()) {
            getItem(position).setSelected(false);
        } else {
            getItem(position).setSelected(true);
        }

        notifyItemChanged(position);
    }


    protected class CameraHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView imageView;

        public CameraHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.iv_single_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            getCameraClick().onCameraClick(v);
        }
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

    public CameraClick getCameraClick() {
        return cameraClick;
    }

    public void setCameraClick(CameraClick cameraClick) {
        this.cameraClick = cameraClick;
    }

    public interface CameraClick
    {
        void onCameraClick(View v);
    }
}
