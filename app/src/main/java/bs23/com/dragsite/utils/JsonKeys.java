package bs23.com.dragsite.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import bs23.com.dragsite.model.ImageSelectModel;

/**
 * Created by BS-86 on 5/19/2016.
 */
public class JsonKeys {

    public static final String WIDGET_IDS="ids";
    public static final String TITLE_WIDGET_TEXT="title";
    public static final String WIDGET_TYPE="type";

    public class ImageWidgetKeys
    {
        public static final String IMAGE_WIDGET_CAPTION="caption";
        public static final String IMAGE_WIDGET_ALTERNATIVE_TEXT="altText";
        public static final String IMAGE_WIDGET_SPACING_LEFT="spacingLeft";
        public static final String IMAGE_WIDGET_SPACING_RIGHT="spacingRight";
        public static final String IMAGE_WIDGET_BORDER_SIZE="borderSize";
        public static final String IMAGE_WIDGET_BORDER_COLOR="borderColor";
        public static final String IMAGE_WIDGET_IMAGE_FILE="imageFile";
        public static final String IMAGE_WIDGET_URL="imageUrl";
    }

    public class CommonKeys
    {
        public static final String IMAGE_WIDGET_SPACING_ABOVE="spacingAbove";
        public static final String IMAGE_WIDGET_SPACING_BELOW="spacingBelow";
    }
}
