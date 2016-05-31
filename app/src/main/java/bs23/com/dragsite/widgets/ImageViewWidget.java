package bs23.com.dragsite.widgets;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import bs23.com.dragsite.Interfaces.IStyleChanger;
import bs23.com.dragsite.JsonWriter;
import bs23.com.dragsite.R;
import bs23.com.dragsite.model.ImageSelectModel;
import bs23.com.dragsite.utils.JsonKeys;

/**
 * Created by BS-86 on 4/1/2016.
 */
public class ImageViewWidget extends BaseLinearLayoutWithSpacingNeeds implements IStyleChanger {

    Context context;
    private int spacingLeft = 0;
    private int spacingRight = 0;
    private int borderSize;
    private int borderColor;
    private String alternateText = "Picture";
    private String captionString = "";
    private ImageSelectModel image;
    private TextView textView;
    private ImageView imageView;
    private String URL = "";
    public static final String TYPE = "image";

    public ImageViewWidget(Context context) {
        super(context);
        this.context = context;
    }

    public void addContents() {
        super.addView(LayoutInflater.from(context).inflate(R.layout.widget_image_view, null), new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        addBottomVIew(context);

        spacingAbove = dpToPx(0);
        spacingBelow = dpToPx(0);
        spacingLeft = dpToPx(0);
        spacingRight = dpToPx(0);
        borderSize = dpToPx(0);
        borderColor = 0;
        setMainView(this.findViewById(R.id.ll_image_widget));

        textView = (TextView) this.findViewById(R.id.tv_image_widget);
        imageView = (ImageView) this.findViewById(R.id.iv_image_widget);

        JsonWriter.getInstance(context).createImageWidgetObject(this);

        setCaptionStringAndUI(getCaptionString());
        setSpacingAboveAndUi(changePxToDp(spacingAbove));
        setSpacingBelowAndUi(changePxToDp(spacingBelow));
        setSpacingLeftAndUi(changePxToDp(spacingLeft));
        setSpacingRightAndUi(changePxToDp(spacingRight));
    }

    public void setSpacingAbove(int spacingAbove) {
        this.spacingAbove = spacingAbove;
    }

    public void setSpacingBelow(int spacingBelow) {
        this.spacingBelow = spacingBelow;
    }

    public void setSpacingLeft(int spacingLeft) {
        this.spacingLeft = spacingLeft;
    }

    public void setSpacingRight(int spacingRight) {
        this.spacingRight = spacingRight;
    }

    @Override
    public void setSpacingAboveAndUi(int spacingAbove) {
        this.spacingAbove = dpToPx(spacingAbove);
        if (getMainView() == null) {
            setMainView(this.findViewById(R.id.view_divider));
        }

        getMainView().setPadding(getSpacingLeft(), spacingAbove, getSpacingRight(), this.spacingBelow);

        JsonWriter.getInstance(getContext()).writeToFile(getId(), JsonKeys.CommonKeys.IMAGE_WIDGET_SPACING_ABOVE, this.spacingAbove);
    }

    @Override
    public void setSpacingBelowAndUi(int spacingBelow) {
        this.spacingBelow = dpToPx(spacingBelow);
        if (getMainView() == null) {
            setMainView(this.findViewById(R.id.view_divider));
        }

        getMainView().setPadding(getSpacingLeft(), spacingAbove, getSpacingRight(), this.spacingBelow);

        JsonWriter.getInstance(getContext()).writeToFile(getId(), JsonKeys.CommonKeys.IMAGE_WIDGET_SPACING_BELOW, this.spacingBelow);
    }

    public int getSpacingLeft() {
        return changePxToDp(spacingLeft);
    }

    public void setSpacingLeftAndUi(int spacingLeft) {
        this.spacingLeft = dpToPx(spacingLeft);
        if (getMainView() == null) {
            setMainView(this.findViewById(R.id.view_divider));
        }

/*
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getMainView().getLayoutParams();
        layoutParams.setMargins(0, spacingAbove, 0, this.spacingBelow);
        getMainView().setLayoutParams(layoutParams);
*/

        getMainView().setPadding(getSpacingLeft(), spacingAbove, getSpacingRight(), spacingBelow);

        JsonWriter.getInstance(getContext()).writeToFile(getId(), JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_LEFT, this.spacingLeft);
    }

    public int getSpacingRight() {
        return changePxToDp(spacingRight);
    }

    public void setSpacingRightAndUi(int spacingRight) {
        this.spacingRight = dpToPx(spacingRight);
        if (getMainView() == null) {
            setMainView(this.findViewById(R.id.view_divider));
        }

/*
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getMainView().getLayoutParams();
        layoutParams.setMargins(0, spacingAbove, 0, this.spacingBelow);
        getMainView().setLayoutParams(layoutParams);
*/

        getMainView().setPadding(getSpacingLeft(), spacingAbove, getSpacingRight(), spacingBelow);

        JsonWriter.getInstance(getContext()).writeToFile(getId(), JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_RIGHT, this.spacingRight);
    }

    public int getBorderSize() {
        return changePxToDp(borderSize);
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = dpToPx(borderSize);

        ImageView imageView = (ImageView) this.findViewById(R.id.iv_image_widget);
        imageView.setPadding(borderSize, borderSize, borderSize, borderSize);
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;

        ImageView imageView = (ImageView) this.findViewById(R.id.iv_image_widget);
        switch (borderColor) {
            case 0:
                imageView.setBackgroundResource(R.drawable.dark_gray_border_transparent_background);
                break;
            case 1:
                imageView.setBackgroundResource(R.drawable.dark_black_border_transparent_background);
        }
    }

    public String getAlternateText() {
        return alternateText;
    }

    private void setAlternateText(String alternateText) {
        this.alternateText = alternateText;

        JsonWriter.getInstance(getContext()).writeToFile(getId(), JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_ALTERNATIVE_TEXT, alternateText);
    }

    public String getCaptionString() {
        return captionString;
    }

    public void setCaptionString(String captionString) {
        this.captionString = captionString;
    }

    public void setCaptionStringAndUI(String captionString) {
        this.captionString = captionString;

        if (captionString.equals("")) {
            findViewById(R.id.tv_image_caption).setVisibility(GONE);
        } else {
            findViewById(R.id.tv_image_caption).setVisibility(VISIBLE);
        }

        ((TextView) findViewById(R.id.tv_image_caption)).setText(captionString);

        JsonWriter.getInstance(getContext()).writeToFile(getId(), JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_CAPTION, captionString);

    }

    public ImageSelectModel getImage() {
        return image;
    }

    public void setImage(ImageSelectModel image) {
        this.image = image;

        if (image != null) {
            textView.setVisibility(View.GONE);
            Picasso.with(getContext()).load(image.getFile()).resize(this.getWidth(), this.getWidth()).centerInside().into(imageView);
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        if (!URL.equals("")) {
            this.URL = "http://" + URL;
        } else {
            this.URL = URL;
        }
    }

    @Override
    public void applyStyle(String attributeName, String attributeValue) {

        if (attributeName.equals(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_CAPTION)) {
            setCaptionStringAndUI(attributeValue);
        } else if (attributeName.equals(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_ALTERNATIVE_TEXT)) {
            setAlternateText(attributeValue);
        } else if (attributeName.equals(JsonKeys.CommonKeys.IMAGE_WIDGET_SPACING_ABOVE)) {
            setSpacingAboveAndUi(Integer.parseInt(attributeValue));
        } else if (attributeName.equals(JsonKeys.CommonKeys.IMAGE_WIDGET_SPACING_BELOW)) {
            setSpacingBelowAndUi(Integer.parseInt(attributeValue));
        } else if (attributeName.equals(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_LEFT)) {
            setSpacingLeftAndUi(Integer.parseInt(attributeValue));
        } else if (attributeName.equals(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_RIGHT)) {
            setSpacingRightAndUi(Integer.parseInt(attributeValue));
        }
    }

    public Bundle createStyleBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(JsonKeys.WIDGET_IDS, getId());
        bundle.putString(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_CAPTION, getCaptionString());
        bundle.putString(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_ALTERNATIVE_TEXT, getAlternateText());
        bundle.putInt(JsonKeys.CommonKeys.IMAGE_WIDGET_SPACING_ABOVE, getSpacingAbove());
        bundle.putInt(JsonKeys.CommonKeys.IMAGE_WIDGET_SPACING_BELOW, getSpacingBelow());
        bundle.putInt(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_LEFT, getSpacingLeft());
        bundle.putInt(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_RIGHT, getSpacingRight());
        return bundle;
    }
}
