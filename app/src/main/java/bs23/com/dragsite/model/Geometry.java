
package bs23.com.dragsite.model;

public class Geometry {

    private Location location;
    private String location_type;
    private Viewport viewport;

    /**
     * 
     * @return
     *     The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *     The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * 
     * @return
     *     The location_type
     */
    public String getLocation_type() {
        return location_type;
    }

    /**
     * 
     * @param location_type
     *     The location_type
     */
    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    /**
     * 
     * @return
     *     The viewport
     */
    public Viewport getViewport() {
        return viewport;
    }

    /**
     * 
     * @param viewport
     *     The viewport
     */
    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

}
