package Formatter.Drones;

public class DroneType2 {
    private int number;
    private int id;
    private String dronetype;
    private String serialnumber;
    private int carriage_weight;
    private String carriage_type;

    public DroneType2() {
        super();
    }
    public DroneType2(int number, int id, String dronetype, String serialnumber, int carriage_weight, String carriage_type) {

        this.number = number;
        this.id = id;
        this.dronetype = dronetype;
        this.serialnumber = serialnumber;
        this.carriage_weight = carriage_weight;
        this.carriage_type = carriage_type;
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
    public String getDronetype() {
        return dronetype;
    }
    public void setDronetype(String dronetype) {
        this.dronetype = dronetype;
    }
    public String getSerialnumber() {
        return serialnumber;
    }
    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }
    public int getCarriage_weight() {
        return carriage_weight;
    }
    public void setCarriage_weight(int carriage_weight) {
        this.carriage_weight = carriage_weight;
    }
    public String getCarriage_type() {
        return carriage_type;
    }
    public void setCarriage_type(String carriage_type) {
        this.carriage_type = carriage_type;
    }
    
}
