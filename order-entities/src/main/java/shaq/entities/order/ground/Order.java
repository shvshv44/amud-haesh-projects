package shaq.entities.order.ground;

import java.util.List;

public class Order {

    private String sourceName;
    private List<String> requests;
    private List<Location> locations;

    public String getSouceName() {
        return sourceName;
    }

    public void setSouceName(String souceName) {
        this.sourceName = souceName;
    }

    public List<String> getRequests() {
        return requests;
    }

    public void setRequests(List<String> requests) {
        this.requests = requests;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
