
package bs23.com.dragsite.model;

public class Viewport {

    private Northeast northeast;
    private Southwest southwest;

    /**
     * 
     * @return
     *     The northeast
     */
    public Northeast getNortheast() {
        return northeast;
    }

    /**
     * 
     * @param northeast
     *     The northeast
     */
    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }

    /**
     * 
     * @return
     *     The southwest
     */
    public Southwest getSouthwest() {
        return southwest;
    }

    /**
     * 
     * @param southwest
     *     The southwest
     */
    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }

}
