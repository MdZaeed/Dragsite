package bs23.com.dragsite.model;

/**
 * Created by BS-86 on 5/27/2016.
 */
public class StyleChange extends Style {

    private int widgetId;

    public StyleChange(int widgetId, String attributeName, String attributeValue)
    {
        super(attributeName,attributeValue);
        setWidgetId(widgetId);
    }

    public int getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(int widgetId) {
        this.widgetId = widgetId;
    }

}
