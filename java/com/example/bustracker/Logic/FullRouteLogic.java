package com.example.bustracker.Logic;

import android.graphics.Color;
import android.os.Looper;
import android.os.SystemClock;

import com.example.bustracker.Activities.MapActivity;
import com.example.bustracker.Data.GenerateSignature;
import com.example.bustracker.Data.getJSON;
import com.example.bustracker.GoogleDirections.googleDirections;
import com.example.bustracker.PTVApi.Departures_2;
import com.example.bustracker.PTVApi.Pattern;
import com.example.bustracker.PTVApi.Stops_2;
import com.example.bustracker.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class FullRouteLogic {
    Marker marker;
    Marker priormarker;
    GoogleMap mMap;
    int route_type;
    int run_id;
    int direction_id;
    int routeNumber;
    LinkedList<LatLng> priorlatLngs;
    LinkedList<LatLng> currentLatLngs;
    String url;
    Marker routeMarker;
    public boolean isTimerOn = false;
    public Timer timerTask = new Timer();
    Pattern pattern;
    int icon;
    int actualMins;
    double countTime = 0;
    double lat;
    double lon;
    int red;
    int green;
    int blue;

    public void PatternRoute(final Marker marker_, GoogleMap mMap_, int route_type_, int routeNumber_, int run_id_, int direction_id_, final int actualMins_) {
        GenerateSignature generateSignature = new GenerateSignature();
        this.direction_id = direction_id_;
        this.marker = marker_;
        this.mMap = mMap_;
        this.route_type = route_type_;
        this.run_id = run_id_;
        this.routeNumber = routeNumber_;
        this.actualMins = actualMins_;
        lat = marker.getPosition().latitude;
        lon = marker.getPosition().longitude;



        // String stopUrl = generateSignature.generateCompleteURLWithSignature("5057f779-eb69-43d0-a897-106c88468536","/v3/stops/route/" + routeNumber +"/route_type/"+ route_type,3001241);
        String PatternsUrl = generateSignature.generateCompleteURLWithSignature("5057f779-eb69-43d0-a897-106c88468536", "/v3/pattern/run/" + run_id + "/route_type/" + route_type + "?expand=all", 3001241);
        this.url = PatternsUrl;
        if (marker != null) {
            marker.remove();
            mMap.clear();
        }

        if(route_type == 1){
            icon = R.drawable.tramway;
        }else{
            icon = R.drawable.busstopicon;
        }
        try {
            if (isTimerOn) {
                timerTask.cancel();
                timerTask = new Timer();
            }
        } catch (Exception e) {

        }
        countTime = 0;
        timerTask.schedule(new TimerTask() {
            @Override
            public void run() {

                new android.os.Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mMap.clear();
                            priorlatLngs = new LinkedList<>();
                            currentLatLngs = new LinkedList<>();
                            isTimerOn = true;


                            try {
                                pattern = getJSON.readPatternData(url);
                            } catch (Exception e) {

                            }
                            Collections.sort(pattern.getDepartures(), new Comparator<Departures_2>() {
                                @Override
                                public int compare(Departures_2 t, Departures_2 t1) {

                                    return t.getScheduled_departure_utc().compareTo(t1.getScheduled_departure_utc());
                                }
                            });
                            actualMins = (int) (actualMins - countTime);
                            countTime = countTime + 0.5;


                            for (Departures_2 time : pattern.getDepartures()) {
                                DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                                m_ISO8601Local.setTimeZone(TimeZone.getTimeZone("UTC"));
                                String depTime = "";
                                Date date = null;
                                try {
                                    if (time.getEstimated_departure_utc() != null) {
                                        depTime = time.getEstimated_departure_utc();
                                    } else {
                                        depTime = time.getScheduled_departure_utc();
                                    }
                                    date = m_ISO8601Local.parse(depTime);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                long departureTime = date.getTime();
                                DateFormat currentTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                                currentTime.setTimeZone(TimeZone.getTimeZone("UTC"));
                                Date currentDate = new Date();
                                currentTime.format(currentDate);
                                long current = currentDate.getTime();
                                long diff = departureTime - current;
                                int mins = (int) (diff / (60 * 1000));


                                for (Map.Entry<String, Stops_2> dep : pattern.getStops().entrySet()) {
                                    if (dep.getValue().getStop_id() == time.getStop_id()) {
                                        if(diff >= 0){
                                            currentLatLngs.add(new LatLng(dep.getValue().getStop_latitude(), dep.getValue().getStop_longitude()));
                                            marker = mMap.addMarker(new MarkerOptions()
                                                    .position(new LatLng(dep.getValue().getStop_latitude(), dep.getValue().getStop_longitude()))
                                                    .title(dep.getValue().getStop_name())
                                                    .icon(BitmapDescriptorFactory.fromResource(icon)));
                                            if (currentLatLngs.getFirst() == priorlatLngs.getLast()) {
                                                marker.remove();
                                            }

                                        } else {
                                            marker = mMap.addMarker(new MarkerOptions()
                                                    .position(new LatLng(dep.getValue().getStop_latitude(), dep.getValue().getStop_longitude()))
                                                    .title(dep.getValue().getStop_name())
                                                    .icon(BitmapDescriptorFactory.fromResource(icon))
                                                    .alpha(0.5f));
                                            priorlatLngs.add(new LatLng(dep.getValue().getStop_latitude(), dep.getValue().getStop_longitude()));
                                        }
                                    }
                                }

                            }

                            try {
                                if (priorlatLngs.size() == 0) {
                                    priorlatLngs.add(currentLatLngs.getLast());
                                }
                                upDatePosition(priorlatLngs.getLast(), currentLatLngs.getFirst());

                            } catch (Exception e) {
                                e.printStackTrace();

                            }

                        } catch (Exception f) {

                        }

                    }
                });
            }
        }, 0, 30000);
    }

// controls movement of a marker
    private void upDatePosition(LatLng priorPoint, final LatLng currentPoints) {
        if (routeMarker != null) {
            routeMarker.remove();
        }
        String snippet;
        if(actualMins == 0){
            snippet = "Arriving at your stop now";
        }else{
            snippet = "Arriving to your stop in " + actualMins + " mins";
        }

        if (route_type == 2) {
            routeMarker = mMap.addMarker(new MarkerOptions().position(priorPoint)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.finalbus))
                    .title("Approximate Location")
                    .snippet(snippet));
            routeMarker.showInfoWindow();
        } else {
            routeMarker = mMap.addMarker(new MarkerOptions().position(priorPoint)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.finaltram))
                    .title("Approximate Location")
                    .snippet(snippet));
            routeMarker.showInfoWindow();
        }
        int zoom;
        if(actualMins <= 5){
            zoom = 16;
        }
        else if(actualMins > 5  && actualMins < 20){
            zoom = 13;
        }else{
            zoom = 11;
        }

        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(routeMarker.getPosition().latitude, routeMarker.getPosition().longitude)) // Sets the new camera position
                .zoom(zoom) // Sets the zoom
                .build(); // Creates a CameraPosition from the builder

        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), null);

    }


}


