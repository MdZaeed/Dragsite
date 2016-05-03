package bs23.com.dragsite.adapter;

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import bs23.com.dragsite.model.ImageSelectModel;

import java.util.ArrayList;
import java.util.List;

import bs23.com.dragsite.R;

/**
 * Created by Ashraful on 3/3/2016.
 */
public class ImagesAdapter extends RecyclerView.Adapter {

    List<ImageSelectModel> imageFiles;
    List<CheckBox> forSingleChoiceCheckBoxes;
    protected Context context;
    private int imageSize;

    public ImagesAdapter(Context context, List<ImageSelectModel> imageFiles) {
        this.context = context;
        this.imageFiles = imageFiles;

        forSingleChoiceCheckBoxes=new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_image, parent, false);
        return new ImageSelectHolder(itemView);
    }

    public ImageSelectModel getItem(int position) {
        return imageFiles.get(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageSelectHolder elementsHolder = (ImageSelectHolder) holder;
        ImageSelectModel element = imageFiles.get(position);
        Picasso.with(context).load(element.getFile()).resize(getImageSize(),getImageSize()).centerCrop().into(elementsHolder.imageView);


        elementsHolder.checkBox.setChecked(false);

        forSingleChoiceCheckBoxes.add(elementsHolder.checkBox);

/*        elementsHolder.elementName.setText(element.getElementName());
        Drawable drawable=context.getResources().getDrawable(element.getImageId());
        elementsHolder.elementName.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);*/
    }


    @Override
    public int getItemCount() {
        return imageFiles.size();
    }

    public void clearAllCheckBoxes()
    {
        for(ImageSelectModel imageSelectModel : imageFiles)
        {
            imageSelectModel.setSelected(false);
        }
        for(CheckBox checkBox : forSingleChoiceCheckBoxes)
        {
            checkBox.setChecked(false);
        }
    }

    public int getImageSize() {
        return imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }


    protected class ImageSelectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView imageView;
        protected CheckBox checkBox;

        public ImageSelectHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.iv_single_image);
            checkBox=(CheckBox) itemView.findViewById(R.id.cb_is_image_selected);

            imageView.setOnClickListener(this);
            checkBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(imageFiles.get(getAdapterPosition()).isSelected()) {
                clearAllCheckBoxes();
            }else {
                clearAllCheckBoxes();
                imageFiles.get(getAdapterPosition()).setSelected(true);
                forSingleChoiceCheckBoxes.get(getAdapterPosition()).setChecked(true);
            }


/*
            notifyDataSetChanged();
*/
        }
    }
}
