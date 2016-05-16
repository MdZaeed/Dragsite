package bs23.com.dragsite.model;

import java.io.File;

/**
 * Created by BS-86 on 5/3/2016.
 */
public class ImageSelectGalleryElementModel extends ImageSelectModel {

    private String caption="";
    private String link="";

    public ImageSelectGalleryElementModel(File file, boolean isSelected, int VIEW_TYPE)
    {
        super(file,isSelected,VIEW_TYPE);
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        if (!link.equals("")) {
            this.link = "http://" + link;
        }else{
            this.link = link;
        }
    }
}
