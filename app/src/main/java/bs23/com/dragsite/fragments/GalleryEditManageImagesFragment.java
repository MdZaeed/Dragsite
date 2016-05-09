package bs23.com.dragsite.fragments;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;

import bs23.com.dragsite.MainActivity;
import bs23.com.dragsite.R;
import bs23.com.dragsite.adapter.BaseGalleryAdapter;
import bs23.com.dragsite.adapter.BaseGalleryAdapterCopyExtended;

/**
 * Created by BS-86 on 5/9/2016.
 */
public class GalleryEditManageImagesFragment extends ImagesListingFragment {

    BaseGalleryAdapterCopyExtended baseGalleryAdapterCopyExtended;
    private Button addImageButton;
    GalleryEditFragment galleryEditFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_image_or_gallery, container, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public static GalleryEditManageImagesFragment newInstance() {
        return new GalleryEditManageImagesFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        galleryEditFragment = (GalleryEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        super.onViewCreated(view, savedInstanceState);

        addImageButton = (Button) view.findViewById(R.id.btn_special_header_button);
        addImageButton.setVisibility(View.VISIBLE);
        addImageButton.setText("Add Images");
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryEditAddImagesFragment galleryEditAddImagesFragment = GalleryEditAddImagesFragment.newInstance();
                galleryEditAddImagesFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0, android.R.anim.slide_in_left, 0)
                        .replace(((MainActivity) getActivity()).getBottomPaneLinearLayout().getId(), galleryEditAddImagesFragment)
                        .addToBackStack("null")
                        .commit();
            }
        });
    }

    @Override
    protected void addRecyclerView(int width) {
        int columnCount = 2;
        int spanPerColumn = width / columnCount;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnCount));
        baseGalleryAdapterCopyExtended = new BaseGalleryAdapterCopyExtended(getContext(), imageFiles);
        baseGalleryAdapterCopyExtended.setImageSize(spanPerColumn);
        recyclerView.setAdapter(baseGalleryAdapterCopyExtended);
    }

    @Override
    protected void createTheGalleryFiles() {
        imageFiles = new ArrayList<>();
        imageFiles = galleryEditFragment.getGalleryViewWidget().getImageSelectModels();
    }

/*    public void onCameraClick(View v) {
        PopupWindow popup =popupWindowsort();
        Rect r = new Rect();
        v.getGlobalVisibleRect(r);
        if(v.getTop()>=0)
        {
            popup.showAsDropDown(v,0,-v.getHeight());
        }
        else
        {
            popup.showAsDropDown(v,0,-r.height());
        }
    }

    public PopupWindow popupWindowsort() {

        PopupWindow popupWindow = new PopupWindow(getContext());

        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // set the list view as pop up window content
        popupWindow.setContentView(LayoutInflater.from(getContext()).inflate(R.layout.dialog_gallery_image, null, false));

        return popupWindow;
    }*/
}
