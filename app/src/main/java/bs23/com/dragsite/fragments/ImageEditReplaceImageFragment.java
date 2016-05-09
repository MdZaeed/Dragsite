package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bs23.com.dragsite.R;
import bs23.com.dragsite.adapter.ImagesAdapter;
import bs23.com.dragsite.model.ImageSelectModel;

/**
 * Created by BrainStation on 4/15/16.
 */
public class ImageEditReplaceImageFragment extends ImagesListingFragment {

    private TextView textView;
    private ImageView imageView;
    private Button replaceImageButton;

    public static ImageEditReplaceImageFragment newInstance() {
        return new ImageEditReplaceImageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_image_or_gallery, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageEditFragment imageEditFragment = (ImageEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        textView = (TextView) imageEditFragment.getImageViewWidget().findViewById(R.id.tv_image_widget);
        imageView = (ImageView) imageEditFragment.getImageViewWidget().findViewById(R.id.iv_image_widget);
        replaceImageButton = (Button) view.findViewById(R.id.btn_special_header_button);

        replaceImageButton.setText(imageEditFragment.getAdditionMood());
        replaceImageButton.setVisibility(View.VISIBLE);
        replaceImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageClick();
            }
        });

    }

    protected void addRecyclerView(int width) {
        int columnCount = 3;
        int spanPerColumn = width / columnCount;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnCount));
        imagesAdapter = new ImagesAdapter(getContext(), imageFiles);
        imagesAdapter.setImageSize(spanPerColumn);
        recyclerView.setAdapter(imagesAdapter);
        imagesAdapter.setCameraClick(this);
    }

    private void saveImageClick() {
        for (ImageSelectModel imageSelectModel : imageFiles) {
            if (imageSelectModel.isSelected()) {
                textView.setVisibility(View.GONE);
                Picasso.with(getContext()).load(imageSelectModel.getFile()).into(imageView);
                imageView.setVisibility(View.VISIBLE);
                super.backButtonClick();
                return;
            }
        }

        imageView.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
        super.backButtonClick();
    }
}
