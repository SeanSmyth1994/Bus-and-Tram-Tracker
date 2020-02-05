package com.example.bustracker.GoogleDirections;

import java.util.List;

public class googleDirections {
    List<GeocodedWaypoint> geocoded_waypoints;
    public List<GoogleRoute> routes;
    List<Leg> legs;
    List<Step> steps;
    String summary;
    String[] warnings;
    public OverviewPolyLine overview_polyline;

    private String points;

    public googleDirections(List<GeocodedWaypoint> geocoded_waypoints, List<GoogleRoute> routes, List<Leg> legs, List<Step> steps, OverviewPolyLine overview_polyline, String summary, String[] warnings){

       this.geocoded_waypoints = geocoded_waypoints;
       this.routes = routes;
       this.legs = legs;
       this.steps = steps;
       this.summary = summary;
       this.warnings = warnings;
       this.overview_polyline = overview_polyline;
    }

    public OverviewPolyLine getOverview_polyline(){
        return overview_polyline;
    }
    public String getPoints(){
        return points;
    }
}
