package bs23.com.dragsite.model;

/**
 * Created by BS-86 on 5/27/2016.
 */
public class Style {

    private int widgetId;
    private String widgetType;
    private String attributeName;
    private String attributeValue;

    public Style(int widgetId,String widgetType,String attributeName,String attributeValue)
    {
        this.widgetId=widgetId;
        this.widgetType=widgetType;
        this.attributeName=attributeName;
        this.attributeValue=attributeValue;
    }
}
