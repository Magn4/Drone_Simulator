package API.Catalog;

public class Product {

    private final String id;
    private final String droneType;
    private final String creationDatum;
    private final String serialNumber;
    private final double carriageWeight;
    private final String carriageType;

    public Product(String id, String droneType, String creationDatum, String serialNumber, double carriageWeight, String carriageType) {
        this.id = id;
        this.droneType = droneType;
        this.creationDatum = creationDatum;
        this.serialNumber = serialNumber;
        this.carriageWeight = carriageWeight;
        this.carriageType = carriageType;
    }

    public String getId() {
        return id;
    }

    public String getDroneType() {
        return droneType;
    }

    public String getCreationDatum() {
        return creationDatum;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getCarriageWeight() {
        return carriageWeight;
    }

    public String getCarriageType() {
        return carriageType;
    }
}
