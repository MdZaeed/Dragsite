
package bs23.com.dragsite.model;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private List<Address_component> address_components = new ArrayList<Address_component>();
    private String formatted_address;
    private Geometry geometry;
    private String place_id;
    private List<String> types = new ArrayList<String>();

    /**
     * 
     * @return
     *     The address_components
     */
    public List<Address_component> getAddress_components() {
        return address_components;
    }

    /**
     * 
     * @param address_components
     *     The address_components
     */
    public void setAddress_components(List<Address_component> address_components) {
        this.address_components = address_components;
    }

    /**
     * 
     * @return
     *     The formatted_address
     */
    public String getFormatted_address() {
        return formatted_address;
    }

    /**
     * 
     * @param formatted_address
     *     The formatted_address
     */
    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    /**
     * 
     * @return
     *     The geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * 
     * @param geometry
     *     The geometry
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     * 
     * @return
     *     The place_id
     */
    public String getPlace_id() {
        return place_id;
    }

    /**
     * 
     * @param place_id
     *     The place_id
     */
    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    /**
     * 
     * @return
     *     The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * 
     * @param types
     *     The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

}
