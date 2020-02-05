package com.example.bustracker.PTVApi;

import java.util.List;

public class disruptions {
    List<disruptions> disruptions;
    int disruption_id;
    String title;
    String url;
    String description;
    String disruption_status;
    String disruption_type;
    String published_on;
    String last_updated;
    String from_date;
    String to_date;
    List<Route> routes;
    List<Stops> stops;
    String colour;
    boolean display_on_board;
    boolean display_status;
}
