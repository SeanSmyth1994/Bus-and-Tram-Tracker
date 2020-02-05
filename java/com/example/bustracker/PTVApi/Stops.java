package com.example.bustracker.PTVApi;

import java.util.List;

public class Stops {

        public List<Stops> stops;
        disruptions disruptions;
        status status;
        private String[] disruption_ids;
        private double stop_distance;
        private String stop_suburb;
        private String stop_name;
        private int stop_id;
        private int route_type;



    private double stop_latitude;
        private double stop_longitude;
        private int stop_sequence;

        public Stops(String[] disruption_ids, double stop_distance, String stop_suburb, String stop_name, int stop_id, int route_type, double stop_latitude, double stop_longitude, int stop_sequence){
            this.disruption_ids = disruption_ids;
            this.stop_distance = stop_distance;
            this.stop_suburb = stop_suburb;
            this.stop_name = stop_name;
            this.stop_id = stop_id;
            this.route_type = route_type;
            this.stop_latitude = stop_latitude;
            this.stop_longitude = stop_longitude;
            this.stop_sequence = stop_sequence;
        }

    public Stops(List<Stops> stops, disruptions disruptions, status status){
        this.stops = stops;
        this.disruptions = disruptions;
        this.status = status;
    }

    public double getStop_distance() {
        return stop_distance;
    }

    public int getStopSequence(){
            return stop_sequence;
    }


    public int getStop_id() {
        return stop_id;
    }
    public double getStop_latitude() {
        return stop_latitude;
    }

    public double getStop_longitude() {
        return stop_longitude;
    }
    public String getStop_name() {
        return stop_name;
    }


}
