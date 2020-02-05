package com.example.bustracker.Logic;

public class BusRoutes {
    private String id;
    String route_name;
    double lat;
    double lon;
    int stop_id;

    private String direction_name;

    public BusRoutes(){

    }
    public BusRoutes(String route, double lat, double lon, int stop_id, String direction_name){
        this.route_name = route;
        this.lat = lat;
        this.lon = lon;
        this.stop_id = stop_id;
        this.id = id;
        this.direction_name = direction_name;
    }
    public String getId() {
        return id;
    }

    public String getRoute_name() {
        return route_name;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public int getStop_id() {
        return stop_id;
    }
    public String getDirection_name() {
        return direction_name;
    }

}
