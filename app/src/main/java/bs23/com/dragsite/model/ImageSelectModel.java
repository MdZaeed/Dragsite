package bs23.com.dragsite.model;

import java.io.File;

/**
 * Created by BS-86 on 5/3/2016.
 */
public class ImageSelectModel {
    private File file;
    private boolean isSelected;
    public int VIEW_TYPE;
    private String caption="";

    public ImageSelectModel(File file,boolean isSelected,int VIEW_TYPE)
    {
        this.file=file;
        this.isSelected=isSelected;
        this.VIEW_TYPE=VIEW_TYPE;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
