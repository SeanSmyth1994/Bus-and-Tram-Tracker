package com.example.bustracker.Data;

import com.example.bustracker.RoadsApi.SnappedToRoads;
import com.example.bustracker.Vehicle.Bus;

import com.example.bustracker.PTVApi.Departures;
import com.example.bustracker.PTVApi.Directions;
import com.example.bustracker.PTVApi.Pattern;
import com.example.bustracker.PTVApi.Route;
import com.example.bustracker.PTVApi.Stops;
import com.example.bustracker.GoogleDirections.googleDirections;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;


public class getJSON {

    static JSONObject jsonObject;
    static String jsonText_;


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static Bus readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);


            Gson gson = new GsonBuilder().serializeNulls().create();
            String gsonText = gson.toJson(json.toString());

            Bus bus = new Gson().fromJson(String.valueOf(json.toString()), Bus.class);
            return bus;

        } finally {

        }
    }



    public static Stops readStopData(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);


            Gson gson = new GsonBuilder().serializeNulls().create();
            String gsonText = gson.toJson(json.toString());

            Stops stops = new Gson().fromJson(String.valueOf(json.toString()), Stops.class);
            return stops;

        } finally {

        }
    }

    public static Route readRouteData(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);


            Gson gson = new GsonBuilder().serializeNulls().create();
            String gsonText = gson.toJson(json.toString());

            Route route = new Gson().fromJson(String.valueOf(json.toString()), Route.class);
            return route;

        } finally {

        }
    }
    public static Directions readDirections(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);


            Gson gson = new GsonBuilder().serializeNulls().create();
            String gsonText = gson.toJson(json.toString());

            Directions direction = new Gson().fromJson(String.valueOf(json.toString()), Directions.class);
            return direction;

        } finally {

        }
    }

    public static Departures readDepartures(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);


            Gson gson = new GsonBuilder().serializeNulls().create();
            String gsonText = gson.toJson(json.toString());

            Departures departures = new Gson().fromJson(String.valueOf(json.toString()), Departures.class);
            return departures;

        } finally {

        }
    }

    public static googleDirections readGoogleDepartures(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);


            Gson gson = new GsonBuilder().serializeNulls().create();
            String gsonText = gson.toJson(json.toString());

            googleDirections departures = new Gson().fromJson(String.valueOf(json.toString()), googleDirections.class);
            return departures;

        } finally {

        }
    }

    public static Pattern readPatternData(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            final JSONObject json = new JSONObject(jsonText);


            Gson gson = new GsonBuilder().serializeNulls().create();
            String gsonText = gson.toJson(json.toString());

            Pattern pattern = new Gson().fromJson(String.valueOf(json.toString()), new TypeToken<Pattern>(){}.getType());
            return pattern;

        } finally {

        }
    }
    public static SnappedToRoads readRoadsApi(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);


            Gson gson = new GsonBuilder().serializeNulls().create();
            String gsonText = gson.toJson(json.toString());

            SnappedToRoads roads = new Gson().fromJson(String.valueOf(json.toString()), SnappedToRoads.class);
            return roads;

        } finally {

        }
    }

    public static StorageApi readStorageApi(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);


            Gson gson = new GsonBuilder().serializeNulls().create();
            String gsonText = gson.toJson(json.toString());

            StorageApi storageApi = new Gson().fromJson(String.valueOf(json.toString()), StorageApi.class);
            return storageApi;

        } finally {

        }
    }


    public static void deSerializeJSON(){





    }
}



