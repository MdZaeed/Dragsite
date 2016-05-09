package bs23.com.dragsite.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.squareup.picasso.Picasso;

import java.util.List;

import bs23.com.dragsite.R;
import bs23.com.dragsite.model.ImageSelectModel;

/**
 * Created by BS-86 on 5/5/2016.
 */
public class BaseGalleryAdapterCopyExtended extends BaseGalleryAdapterCopy {

    public BaseGalleryAdapterCopyExtended(Context context, List<ImageSelectModel> imageFiles) {
        super(context, imageFiles);
    }

    @Override
    public void onGalleryImageClick(View v) {
        PopupWindow popup =popupWindowsort();
        Rect rect=new Rect();
        v.getLocalVisibleRect(rect);
        if(rect.contains(v.getLeft(),v.getTop()))
        {
            popup.showAsDropDown(v,0,-v.getHeight());
        }
        else
        {
            popup.showAsDropDown(v,0,-rect.height());
        }
    }

    public PopupWindow popupWindowsort() {

        PopupWindow popupWindow = new PopupWindow(context);

        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // set the list view as pop up window content
        popupWindow.setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_gallery_image, null, false));

        return popupWindow;
    }
}
