package com.example.bustracker.PTVApi;

import java.util.List;
import java.util.Map;

public class Pattern {
    // Load up same lists
    List<Departures_2> departures;
    List<Pattern> pattern;
    List<disruptions> disruptions;
    private Map<String, Stops_2> stops;
    private Map<String, Route> routes;
    private Map<String, Directions> directions;

    public Pattern(){

    }


    public Pattern(List<Departures_2> departures){
        this.departures = departures;
    }

    public List<Departures_2> getDepartures() {
        return departures;
    }

    public Map<String, Stops_2> getStops() {
        return stops;
    }
    public Map<String, Route> getRoutes() {
        return routes;
    }
    public Map<String, Directions> getDirections() {
        return directions;
    }

}
