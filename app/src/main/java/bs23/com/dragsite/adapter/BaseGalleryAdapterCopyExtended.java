package bs23.com.dragsite.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import java.util.List;

import bs23.com.dragsite.R;
import bs23.com.dragsite.model.ImageSelectModel;

/**
 * Created by BS-86 on 5/5/2016.
 */
public class BaseGalleryAdapterCopyExtended extends BaseGalleryAdapterCopy {

    private OnSingleImageClicked onSingleImageClicked;

    public BaseGalleryAdapterCopyExtended(Context context, List<ImageSelectModel> imageFiles) {
        super(context, imageFiles);
    }

    @Override
    public void onGalleryImageClick(View v, final int position) {
        final PopupWindow popup = createPopupWindow();
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

        View singleItemMenuView=popup.getContentView();
        Button deleteButton=(Button) singleItemMenuView.findViewById(R.id.btn_delete_gallery_image);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletSingleItem(position);
                popup.dismiss();
            }
        });
    }

    private void deletSingleItem(int position) {
        imageFiles.remove(position);
        notifyDataSetChanged();
        getOnSingleImageClicked().onSigleImageClick();
    }

    public PopupWindow createPopupWindow() {

        PopupWindow popupWindow = new PopupWindow(context);

        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        popupWindow.setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_gallery_image, null, false));

        return popupWindow;
    }

    public OnSingleImageClicked getOnSingleImageClicked() {
        return onSingleImageClicked;
    }

    public void setOnSingleImageClicked(OnSingleImageClicked onSingleImageClicked) {
        this.onSingleImageClicked = onSingleImageClicked;
    }

    public interface OnSingleImageClicked
    {
        public void onSigleImageClick();
    }
}
