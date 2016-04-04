package bs23.com.dragsite;

/**
 * Created by BS-86 on 4/1/2016.
 */
public class ElementsModel {
    private int imageId;
    private String elementName;

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public ElementsModel(String elementName,int imageId)
    {
        this.elementName=elementName;
        this.imageId=imageId;
    }
}
