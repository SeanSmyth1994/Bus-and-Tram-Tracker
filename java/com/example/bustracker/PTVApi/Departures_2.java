package com.example.bustracker.PTVApi;

import java.util.List;

public class Departures_2 {
    List<Departures_2> departures;
    int stop_id;
    int route_id;
    int run_id;
    int direction_id;
    int[] disruption_ids;



    String scheduled_departure_utc;
    String estimated_departure_utc;
    boolean at_platform;
    String platform_number;
    String flags;
    int departure_sequence;

    public List<Departures_2> getDepartures() {
        return departures;
    }

    public int getDirection_id() {
        return direction_id;
    }

    public int getStop_id() {
        return stop_id;
    }
    public int getDeparture_sequence(){
        return departure_sequence;
    }


    public String getScheduled_departure_utc() {
        return scheduled_departure_utc;
    }

    public String getEstimated_departure_utc() {
        return estimated_departure_utc;
    }

}
