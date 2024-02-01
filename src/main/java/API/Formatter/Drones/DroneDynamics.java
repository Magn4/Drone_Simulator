package API.Formatter.Drones;



public class DroneDynamics {
    private int number;
    private String drone;
    private String timestamp;
    private int speed;
    private String align_roll;
    private String align_pitch;
    private String align_yaw;
    private String longitude;
    private String latitude;
    private int battery_status;
    private String last_seen;
    private String status;
    
 public DroneDynamics() {
    super();    
}
public DroneDynamics(int number,String drone, String timestamp, int speed, String align_roll, String align_pitch, String align_yaw, String longitude, String latitude, int battery_status, String last_seen, String status) {

    this.number = number;  
    this.drone = drone;
    this.timestamp = timestamp;
    this.speed = speed;
    this.align_roll = align_roll;
    this.align_pitch = align_pitch; 
    this.align_yaw = align_yaw;
    this.longitude = longitude;
    this.latitude = latitude;
    this.battery_status = battery_status;
    this.last_seen = last_seen;
    this.status = status;
}

public int getNumber() {
    return number;
}

public void setNumber(int number) {
    this.number = number;
}
public String getDrone() {
    return drone;
}

public void setDrone(String drone) {
    this.drone = drone;
}

public String getTimestamp() {
    return timestamp;
}

public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
}

public int getSpeed() {
    return speed;
}

public void setSpeed(int speed) {
    this.speed = speed;
}

public String getAlign_roll() {
    return align_roll;
}

public void setAlign_roll(String align_roll) {
    this.align_roll = align_roll;
}

public String getAlign_pitch() {
    return align_pitch;
}

public void setAlign_pitch(String align_pitch) {
    this.align_pitch = align_pitch;
}

public String getAlign_yaw() {
    return align_yaw;
}

public void setAlign_yaw(String align_yaw) {
    this.align_yaw = align_yaw;
}

public String getLongitude() {
    return longitude;
}

public void setLongitude(String longitude) {
    this.longitude = longitude;
}

public String getLatitude() {
    return latitude;
}

public void setLatitude(String latitude) {
    this.latitude = latitude;
}

public int getBattery_status() {
    return battery_status;
}

public void setBattery_status(int battery_status) {
    this.battery_status = battery_status;
}

public String getLast_seen() {
    return last_seen;
}

public void setLast_seen(String last_seen) {
    this.last_seen = last_seen;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}
