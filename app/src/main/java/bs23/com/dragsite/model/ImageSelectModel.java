package bs23.com.dragsite.model;

import java.io.File;

/**
 * Created by BS-86 on 5/3/2016.
 */
public class ImageSelectModel {
    private File file;
    private boolean isSelected;

    public ImageSelectModel(File file,boolean isSelected)
    {
        this.file=file;
        isSelected=isSelected;
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
}
