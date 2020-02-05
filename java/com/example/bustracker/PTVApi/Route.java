package com.example.bustracker.PTVApi;

import java.util.List;

public class Route {
    public Route route;
    private List<Route> routes;
    private RouteServiceStatus route_service_status;
    private int route_type;
    private int route_id;
    private String route_name;
    private String route_number;

    private String route_gtfs_id;

    public Route(List<Route> routeList, RouteServiceStatus route_service_status,int route_type, int routeId, String routeName, String routeNumber, String route_gtfs_id) {
        this.route_gtfs_id = route_gtfs_id;
        this.route_type = route_type;
        this.route_id = routeId;
        this.route_name = routeName;
        this.route_number = routeNumber;
        this.route_service_status = route_service_status;
        this.routes = routeList;
    }

    public Route(List<Route> routes,RouteServiceStatus route_service_status, status status){
        this.route_service_status = route_service_status;
        this.routes = routes;

    }


    public int getRouteId() {
        int RouteID =  routes.get(0).route_id;
        return RouteID;
    }
    public List<Route> getRouteList(){
        return routes;
    }

    public String getRoute_name(){
        return route_name;
    }
    public String getRoute_number(){
        return route_number;
    }
}
