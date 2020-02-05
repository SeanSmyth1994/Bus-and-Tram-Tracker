package com.example.bustracker.PTVApi;

import java.util.List;

public class Directions {
    List<Directions> directions;
    private String route_direction_description;
    private int direction_id;
    private String direction_name;
    private int route_id;
    private int route_type;

    public Directions(List<Directions> directions, String route_direction_description, int directionid, String directionName, int route_type, int route_id){
        this.route_direction_description = route_direction_description;
        this.direction_id = directionid;
        this.direction_name = directionName;
        this.route_id = route_id;
        this.route_type = route_type;
        this.directions = directions;
    }

    public String getDirection_name(){
        return direction_name;
    }

    public int getRoute_type() {
        return route_type;
    }

    public List<Directions> getDirections() {
        return directions;
    }

    public int getDirectionid1() {
        int dId = directions.get(0).direction_id;

        return dId;
    }
    public int getDirectionId2(){
        int dId = directions.get(1).direction_id;
        return dId;

    }

    public String getDirectionNameFromDirectionId1() {
        String directionName = directions.get(0).direction_name;
        return directionName;
    }

    public String getDirectionNameFromDirectionId2() {
        String directionName = directions.get(1).direction_name;
        return directionName;
    }

}
