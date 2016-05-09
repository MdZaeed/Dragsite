package bs23.com.dragsite.adapter;

import android.content.Context;

import bs23.com.dragsite.model.ImageSelectModel;

import java.util.List;

/**
 * Created by Ashraful on 3/3/2016.
 */
public class ImagesAdapter extends BaseGalleryAdapter {

    private ImageSelectModel lastElement;

    public ImagesAdapter(Context context, List<ImageSelectModel> imageFiles) {
        super(context, imageFiles);
    }

    public void setImageFiles(List<ImageSelectModel> imageFiles) {
        this.imageFiles = imageFiles;
    }

    public ImageSelectModel getLastElement() {
        return lastElement;
    }

    public ImageSelectModel setLastElement(ImageSelectModel lastElement) {
        this.lastElement = lastElement;
        return lastElement;
    }

    @Override
    protected void onImageClick(int position) {
        if (getItem(position).isSelected()) {
            getItem(position).setSelected(false);
        } else {
            getItem(position).setSelected(true);
            if (getLastElement() != null) {
                getLastElement().setSelected(false);
                notifyItemChanged(imageFiles.indexOf(getLastElement()));
            }
        }

        notifyItemChanged(position);
        setLastElement(getItem(position));
    }
}
