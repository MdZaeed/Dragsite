package bs23.com.dragsite.model;

/**
 * Created by BS-86 on 5/27/2016.
 */
public class Style {

    private int widgetId;
    private String attributeName;
    private String attributeValue;

    public Style(int widgetId, String attributeName, String attributeValue)
    {
        this.setWidgetId(widgetId);
        this.setAttributeName(attributeName);
        this.setAttributeValue(attributeValue);
    }

    public int getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(int widgetId) {
        this.widgetId = widgetId;
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
