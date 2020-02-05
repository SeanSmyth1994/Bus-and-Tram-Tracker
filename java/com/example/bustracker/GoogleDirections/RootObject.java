package com.example.bustracker.GoogleDirections;

import com.example.bustracker.PTVApi.Route;

import java.util.List;

public class RootObject {
    public List<GeocodedWaypoint> geocoded_waypoints;
    public List<Route> routes;
    public String status;
}
