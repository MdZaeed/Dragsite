package bs23.com.dragsite.model;

/**
 * Created by BS-86 on 5/27/2016.
 */
public class Style {
    protected String attributeName;
    protected String attributeValue;

    public Style(String attributeName, String attributeValue)
    {
        setAttributeName(attributeName);
        setAttributeValue(attributeValue);
    }


    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
}
