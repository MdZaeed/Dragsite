package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import bs23.com.dragsite.MainActivity;
import bs23.com.dragsite.R;

/**
 * Created by BS-86 on 4/28/2016.
 */
public class ImageEditLinkFragment extends BaseSecondLevelEditFragment {

    private Button removeLinkButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_link_image_button, container, false);
    }

    public static ImageEditLinkFragment newInstance() {
        return new ImageEditLinkFragment();
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ImageEditFragment imageEditFragment = (ImageEditFragment) getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        Button setURLButton = (Button) view.findViewById(R.id.btn_set_url_image);
        setURLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageEditLinkURLFragment imageEditLinkURLFragment = ImageEditLinkURLFragment.newInstance();
                imageEditLinkURLFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0, android.R.anim.slide_in_left, 0)
                        .replace(((MainActivity) getActivity()).getBottomPaneLinearLayout().getId(), imageEditLinkURLFragment)
                        .addToBackStack("null")
                        .commit();
            }
        });

        removeLinkButton=(Button) view.findViewById(R.id.btn_remove_link);
        if(imageEditFragment.getImageViewWidget().getURL().equals(""))
        {
            removeLinkButton.setTextColor(getContext().getResources().getColor(android.R.color.darker_gray));
        }else
        {
            removeLinkButton.setTextColor(getContext().getResources().getColor(android.R.color.holo_blue_dark));
        }

        removeLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageEditFragment.getImageViewWidget().setURL("");
                removeLinkButton.setTextColor(getContext().getResources().getColor(android.R.color.darker_gray));
            }
        });
    }
}
