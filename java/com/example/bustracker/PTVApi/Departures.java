package com.example.bustracker.PTVApi;

import java.util.List;

public class Departures {
    List<Departures> departures;
    int stop_id;
    int run_id;
    int direction_id;
    String[] disruption_ids;
    String scheduled_departure_utc;
    String estimated_departure_utc;
    boolean at_platform;
    int platform_number;
    String flags;
    int departure_sequence;
    int route_id;

    public Departures(List<Departures> departures){
        this.departures = departures;
    }
    public List<Departures> getDepartures(){
        return departures;
    }
    public String getScheduled_departure_utc(){
        return scheduled_departure_utc;
    }
    public int getRun_id(){
        return run_id;
    }
    public String getEstimated_departure_utc(){
        return estimated_departure_utc;
    }
    public int getRoute_id(){
        return route_id;
    }

}
