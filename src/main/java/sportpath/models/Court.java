package sportpath.models;

public class Court {
    private int id;
    private double lat;
    private double lon;
    private String address;
    private String sport;

    public Court() {}

    public Court(int id, double lat, double lon, String address, String sport) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.sport = sport;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
