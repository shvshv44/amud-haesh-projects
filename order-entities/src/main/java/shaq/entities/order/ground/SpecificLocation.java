package shaq.entities.order.ground;

public class SpecificLocation extends Location {

    private Long encodedLocation;

    public Long getEncodedLocation() {
        return encodedLocation;
    }

    public void setEncodedLocation(Long encodedLocation) {
        this.encodedLocation = encodedLocation;
    }
}
