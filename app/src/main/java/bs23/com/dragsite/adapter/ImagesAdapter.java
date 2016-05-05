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

import bs23.com.dragsite.model.ImageSelectModel;

import java.util.List;

import bs23.com.dragsite.R;

/**
 * Created by Ashraful on 3/3/2016.
 */
public class ImagesAdapter extends GalleryAdapter {

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
