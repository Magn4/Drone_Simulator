
package Formatter.DemoYTB;

public class Drones {
    private int number;
    private int id;
    private String manufacturer;
    private String typename;
    private int weight;
    private int max_speed;
    private int battery_capacity;
    private int control_range;
    private int max_carriage;

    // Add getters and setters for each field

    public Drones() {
        super();
    }

    public Drones(int number, int id, String manufacturer, String typename, int weight, int max_speed, int battery_capacity, int control_range, int max_carriage) {
        this.number = number;
        this.id = id;
        this.manufacturer = manufacturer;
        this.typename = typename;
        this.weight = weight;
        this.max_speed = max_speed;
        this.battery_capacity = battery_capacity;
        this.control_range = control_range;
        this.max_carriage = max_carriage;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getTypename() {
        return typename;
    }
    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMax_speed() {
        return max_speed;
    }
    public void setMax_speed(int max_speed) {
        this.max_speed = max_speed;
    }

    public int getBattery_capacity() {
        return battery_capacity;
    }
    public void setBattery_capacity(int battery_capacity) {
        this.battery_capacity = battery_capacity;
    }

    public int getControl_range() {
        return control_range;
    }
    public void setControl_range(int control_range) {
        this.control_range = control_range;
    }
    
    public int getMax_carriage() {
        return max_carriage;
    }
    public void setMax_carriage(int max_carriage) {
        this.max_carriage = max_carriage;
    }

    // Override the toString() method to provide a readable representation of the object


    @Override
    public String toString() {
        return "Drones{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", typename='" + typename + '\'' +
                ", weight=" + weight +
                ", max_speed=" + max_speed +
                ", battery_capacity=" + battery_capacity +
                ", control_range=" + control_range +
                ", max_carriage=" + max_carriage +
                '}';
    }

    public Object getDroneList() {
        return null;
    }
}
