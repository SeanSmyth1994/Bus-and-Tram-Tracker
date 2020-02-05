package com.example.bustracker.RoadsApi;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class SnappedToRoads {

    List<SnappedToRoads> snappedPoints;
    Location location;
    int originalIndex;
    String placeId;

    public String buildRequestUrl(List<LatLng> trackPoints) {
        StringBuilder url = new StringBuilder();
        url.append("https://roads.googleapis.com/v1/snapToRoads?path=");

        for (LatLng trackPoint : trackPoints) {
            url.append(String.format("%8.5f", trackPoint.latitude));
            url.append(",");
            url.append(String.format("%8.5f", trackPoint.longitude));
            url.append("|");
        }
        url.delete(url.length() - 1, url.length());
        url.append("&interpolate=true");
        url.append(String.format("&key=%s", "AIzaSyCSRUY-53Lu2q85W_edxPxEFPaZ2aunr0o"));

        return url.toString();
    }

    public List<SnappedToRoads> getSnappedPoints() {
        return snappedPoints;
    }
    public String getPlaceId(){
        return placeId;
    }
    public Location getLocation(){
        return location;
    }
}
