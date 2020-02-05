package com.example.bustracker.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.example.bustracker.Data.GenerateSignature;
import com.example.bustracker.Data.getJSON;
import com.example.bustracker.Logic.BusRoutes;
import com.example.bustracker.Logic.FullRouteLogic;
import com.example.bustracker.Logic.timeLogic;
import com.example.bustracker.PTVApi.Pattern;
import com.example.bustracker.PTVApi.Stops_2;
import com.example.bustracker.R;
import com.example.bustracker.PTVApi.Departures;
import com.example.bustracker.PTVApi.Directions;
import com.example.bustracker.PTVApi.Route;
import com.example.bustracker.PTVApi.Stops;
import com.example.bustracker.GoogleDirections.googleDirections;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.maps.android.PolyUtil;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private int count = 0;
    private int route_type = 2;
    private int max_results = 20;
    private final int max_distance = 2000;
    Stops stopsLocation;
    String routeDuration;
    Departures departures;
    ScrollView scrollView;
    googleDirections gDirections;
    double lat;
    double lon;
    double dlat;
    double dlon;
    TextView txt;
    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    TextView walkTime;
    TextView seek;
    int route_id1;
    int route_id2;
    int route_id3;
    int route_id4;
    int route_id5;
    int run_id1;
    int run_id2;
    int run_id3;
    int run_id4;
    int run_id5;
    String routeName1;
    String routeName2;
    String routeName3;
    String routeName4;
    String routeName5;
    String direction_name1;
    String direction_name2;
    String direction_name3;
    String direction_name4;
    String direction_name5;
    Timer timer = new Timer();
    RelativeLayout relativeLayout;
    ImageButton button;
    ImageButton button_show;
    FloatingActionButton close_seek;
    boolean directions;
    int stop_id = Integer.MIN_VALUE;
    long current;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Location mLastKnownLocation;
    private Location currentLocation;
    private LocationCallback locationCallback;
    private MaterialSearchBar searchBar;
    private View mapView;
    private GenerateSignature generateSignature_;
    private DrawerLayout drawer;
    private String search;
    private int actualMins;
    private int actualMins1;
    private int actualMins2;
    private int actualMins3;
    private int actualMins4;
    private int defaultZoom = 14;
    private int direction_id;
    private Route route;
    private Polyline polyline;
    private Marker marker;
    private ProgressBar progressBar;
    private RelativeLayout relativeLayout_;
    private int previous_direction_id;
    boolean isIntimer = false;
    private int drawable;
    FullRouteLogic FullRoute = new FullRouteLogic();
    private Button button_to;
    private Button button_from;
    private SwipeRefreshLayout swipeRefreshLayout;
    List<String> predictions;
    private boolean inSearch;
    private boolean isDragged = false;
    DatabaseReference databaseReference;
    List<String> suggestions;
    int red;
    int green;
    int blue;
    double distance_;
    String name;
    Marker m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_map);
        searchBar = (MaterialSearchBar) findViewById(R.id.searchBar);
        searchBar.setHint("Route Number");
        swipeRefreshLayout = findViewById(R.id.refresh);
        txt = findViewById(R.id.text);
        txt1 = findViewById(R.id.text1);
        txt2 = findViewById(R.id.text2);
        txt3 = findViewById(R.id.text3);
        txt4 = findViewById(R.id.text4);
        walkTime = findViewById(R.id.walkTime);
        scrollView = findViewById(R.id.scrollable);
        relativeLayout = findViewById(R.id.maplinear);
        button = findViewById(R.id.close);
        progressBar = findViewById(R.id.progressbar);
        suggestions = new ArrayList<>();
        StorageReference storageReference = null;
        try {
            FirebaseApp.initializeApp(this);
            FirebaseStorage storage = FirebaseStorage.getInstance();
            databaseReference = FirebaseDatabase.getInstance().getReference("routeinformation");
            storageReference = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/bustracker-fce71.appspot.com/o/BusRoutes.txt?alt=media&token=cc9d9017-87e0-4bd3-ae1a-388cc2a79fb6");
            storageReference.getBytes(1024*1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    String s = new String(bytes);

                    s = s.replaceAll("\r\n",":");
                    s = s.replaceAll("\n", ":");
                    String[] parts = s.split(":");
                    for(int i = 0; i < parts.length; i++){
                        suggestions.add(parts[i]);
                    }




                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        button_to = findViewById(R.id.to_city);
        button_from = findViewById(R.id.to_place);
        button_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSearchResults(search, false);
            }
        });
        button_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSearchResults(search, true);
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MapActivity.this);
        button_show = findViewById(R.id.show);
        generateSignature_ = new GenerateSignature();
        mapView = mapFragment.getView();
        final FloatingActionButton floatingActionButton = findViewById(R.id.floating);
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled}, // enabled
                new int[]{-android.R.attr.state_enabled}, // disabled
                new int[]{-android.R.attr.state_checked}, // unchecked
                new int[]{android.R.attr.state_pressed}  // pressed
        };
        int[] colors = new int[]{
                Color.BLUE,
                Color.RED,
                Color.GREEN,
                Color.BLUE
        };
        relativeLayout_ = findViewById(R.id.seekBarLayout);
        final SeekBar seekBar = findViewById(R.id.menu_seek);
        seekBar.setProgress(max_results);
        seek = findViewById(R.id.seek_text);
        seek.setText("Number of Close by Stops: " + max_results);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress_value = i;
                seek.setText("Number of Close by Stops: " + i);
                Toast.makeText(MapActivity.this, "Number of Stops = "  + i, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                max_results = progress_value;
                seek.setText("Number of Close by Stops: " + max_results);
                getDeviceLocation();
                relativeLayout_.setVisibility(View.GONE);


            }
        });
        close_seek = findViewById(R.id.close_seek);

        close_seek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout_.setVisibility(View.GONE);
            }
        });

        final ColorStateList myList = new ColorStateList(states, colors);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                floatingActionButton.setForegroundTintList(myList);
                getDeviceLocation();
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //get the menu from the navigation view
        Menu menu = navigationView.getMenu();
        drawer = findViewById(R.id.drawer_layout);
        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeRefreshLayout.setVisibility(View.VISIBLE);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          if (swipeRefreshLayout.getVisibility() == View.VISIBLE) {
                                              swipeRefreshLayout.setVisibility(View.GONE);
                                              button_show.setVisibility(View.VISIBLE);
                                          } else {
                                              swipeRefreshLayout.setVisibility(View.VISIBLE);
                                              button_show.setVisibility(View.GONE);
                                          }
                                      }
                                  }
        );
        searchBar.setMaxSuggestionCount(5);


        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                swipeRefreshLayout.setVisibility(View.GONE);
                button_show.setVisibility(View.VISIBLE);
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onSearchConfirmed(CharSequence text) {
                progressBar.setVisibility(View.VISIBLE);


                try {
                    if (FullRoute.isTimerOn) {
                        FullRoute.timerTask.cancel();
                    }
                }catch (Exception e){

                }
                search = text.toString();
                try {

                    searchBar.disableSearch();
                    getSearchResults(search, directions);


                } catch (Exception e) {
                    Toast.makeText(MapActivity.this, "Invalid Route Number", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onButtonClicked(int buttonCode) {
                if (buttonCode == MaterialSearchBar.BUTTON_NAVIGATION) {
                    searchBar.clearSuggestions();
                    drawer.openDrawer(Gravity.LEFT);


                } else if (buttonCode == MaterialSearchBar.BUTTON_BACK) {
                    searchBar.disableSearch();
                    searchBar.clearSuggestions();
                }
            }
        });
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                predictions = new ArrayList<>();
                for(String s : suggestions) {
                    if(s.startsWith(charSequence.toString())){
                        predictions.add(s);
                    }
                }

                if(route_type == 2 && i1 == 3){
                    searchBar.clearSuggestions();
                }else if(route_type == 1 && i1 == 2){
                    searchBar.clearSuggestions();
                }
                else if(!charSequence.toString().equals("")){
                    searchBar.updateLastSuggestions(predictions);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("")){
                    searchBar.hideSuggestionsList();
                    searchBar.clearSuggestions();
                }

            }
        });
        searchBar.setSuggestionsClickListener(new SuggestionsAdapter.OnItemViewClickListener() {
            @Override
            public void OnItemClickListener(int position, View v) {
                if(position >= suggestions.size()){
                    return;
                }
                search = searchBar.getLastSuggestions().get(position).toString();
                searchBar.setText(search);
                searchBar.clearSuggestions();
                searchBar.disableSearch();
                getSearchResults(search,false);
            }

            @Override
            public void OnItemDeleteListener(int position, View v) {


            }
        });

        mapView.setClickable(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        CameraPosition position = new CameraPosition.Builder()
                                .target(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())) // Sets the new camera position
                                .zoom(defaultZoom)
                                .build(); // Creates a CameraPosition from the builder

                        mMap.animateCamera(CameraUpdateFactory
                                .newCameraPosition(position), null);
                        //refresh all departures in list
                        try {
                        if(inSearch){
                            String getDepartures = generateSignature_.generateCompleteURLWithSignature("5057f779-eb69-43d0-a897-106c88468536", "/v3/departures/route_type/" + route_type + "/stop/" + stop_id + "/route/" + route.getRouteId() + "?look_backwards=false&max_results=5", 3001241);

                                departures = getJSON.readDepartures(getDepartures);

                        }else{
                            String dep = generateSignature_.generateCompleteURLWithSignature("5057f779-eb69-43d0-a897-106c88468536", "/v3/departures/route_type/" + route_type + "/stop/" + stop_id +"?look_backwards=false&max_results=5", 3001241);
                            departures = getJSON.readDepartures(dep);
                            }
                        }catch (Exception e){

                        }
                        getDepartures(lat, lon, departures,routeName1,routeName2,routeName3,routeName4,routeName5,direction_name1,direction_name2,direction_name3,direction_name4,direction_name5);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });
        swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        SettingsClient settingsClient = LocationServices.getSettingsClient(MapActivity.this);
        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());
        task.addOnSuccessListener(MapActivity.this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                getDeviceLocation();
            }
        });
        task.addOnFailureListener(MapActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                    try {
                        resolvableApiException.startResolutionForResult(MapActivity.this, 51);
                    } catch (IntentSender.SendIntentException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });
        mMap.setTrafficEnabled(true);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 51) {
            if (resultCode == RESULT_OK) {
                getDeviceLocation();
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getDeviceLocation() {
        mFusedLocationProviderClient.getLastLocation()
                .addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            mLastKnownLocation = task.getResult();
                            currentLocation = task.getResult();
                            if (mLastKnownLocation != null) {
                                isDragged = false;
                                String lat = Double.toString(currentLocation.getLatitude());
                                String lon = Double.toString(currentLocation.getLongitude());
                                if(m != null){
                                    m.remove();
                                }
                                m =  mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()))
                                        .draggable(true)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));

                                mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                                    @Override
                                    public void onMarkerDragStart(Marker marker) {

                                    }

                                    @Override
                                    public void onMarkerDrag(Marker marker) {
                                        CameraPosition position = new CameraPosition.Builder()
                                                .target(new LatLng(marker.getPosition().latitude, marker.getPosition().longitude)) // Sets the new camera position
                                                .zoom(defaultZoom) // Sets the zoom// Set the camera tilt
                                                .build(); // Creates a CameraPosition from the builder

                                        mMap.animateCamera(CameraUpdateFactory
                                                .newCameraPosition(position), null);

                                    }

                                    @Override
                                    public void onMarkerDragEnd(Marker marker) {
                                        marker.setTitle("Search Here");
                                        marker.showInfoWindow();
                                        dlat = marker.getPosition().latitude;
                                        dlon = marker.getPosition().longitude;
                                        String url = generateSignature_.generateCompleteURLWithSignature("5057f779-eb69-43d0-a897-106c88468536", "/v3/stops/location/" + marker.getPosition().latitude + "," + marker.getPosition().longitude + "?route_types=" + route_type + "&max_results=" + max_results + "&max_distance=" + max_distance + "&stop_disruptions=true", 3001241);
                                        isDragged = true;
                                        try {
                                            Stops stopLo = getJSON.readStopData(url);
                                            stopsLocation = stopLo;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                                String url = generateSignature_.generateCompleteURLWithSignature("5057f779-eb69-43d0-a897-106c88468536", "/v3/stops/location/" + lat + "," + lon + "?route_types=" + route_type + "&max_results=" + max_results + "&max_distance=" + max_distance + "&stop_disruptions=true", 3001241);

                                try {
                                    Stops stopLo = getJSON.readStopData(url);
                                    stopsLocation = stopLo;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()),defaultZoom));


                            } else {
                                final LocationRequest locationRequest = LocationRequest.create();
                                locationRequest.setInterval(10000);
                                locationRequest.setFastestInterval(5000);
                                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

                                locationCallback = new LocationCallback() {
                                    @Override
                                    public void onLocationResult(LocationResult locationResult) {
                                        super.onLocationResult(locationResult);
                                        if (locationResult == null) {
                                            return;
                                        }
                                        mLastKnownLocation = locationResult.getLastLocation();
                                        CameraPosition position = new CameraPosition.Builder()
                                                .target(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude())) // Sets the new camera position
                                                .zoom(defaultZoom) // Sets the zoom// Set the camera tilt
                                                .build(); // Creates a CameraPosition from the builder

                                        mMap.animateCamera(CameraUpdateFactory
                                                .newCameraPosition(position), null);


                                    }
                                };
                                mFusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);


                            }
                        } else {
                            Toast.makeText(MapActivity.this, "unable to get last Location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    // Search refers to the route number
    // parse out all logic to another class
    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void getSearchResults(String search, boolean direction) {
        button_to.setVisibility(View.VISIBLE);
        button_from.setVisibility(View.VISIBLE);
        inSearch = true;
        if (marker != null) {
            mMap.clear();
        }


        try {
            String getRouteNumber = generateSignature_.generateCompleteURLWithSignature("5057f779-eb69-43d0-a897-106c88468536", "/v3/routes?route_types=" + route_type + "&route_name=" + search, 3001241);
            route = getJSON.readRouteData(getRouteNumber);
            route_id1 = route.getRouteId();
            route_id2 = route.getRouteId();
            route_id3 = route.getRouteId();
            route_id4 = route.getRouteId();
            route_id5 = route.getRouteId();


            //from route id we can get a whole bunch of stuff
            //need directions to get whether or not they are going to the city or not
            // need departures to relate back to the stop id we got before to then
            String getDirections = generateSignature_.generateCompleteURLWithSignature("5057f779-eb69-43d0-a897-106c88468536", "/v3/directions/route/" + route.getRouteId(), 3001241);

            Directions ptvDirection = getJSON.readDirections(getDirections);

            if (direction == false) {
                direction_name1 = ptvDirection.getDirectionNameFromDirectionId1();
                direction_id = ptvDirection.getDirectionId2();

            }
            if (direction == true) {
                direction_name1 = ptvDirection.getDirectionNameFromDirectionId2();
                direction_id = ptvDirection.getDirectionid1();
            }

            button_to.setText("To: " + ptvDirection.getDirectionNameFromDirectionId1());
            button_from.setText("To: " + ptvDirection.getDirectionNameFromDirectionId2());
            //get stop id that matches the stop ids based on location


            String getRouteStopData = generateSignature_.generateCompleteURLWithSignature("5057f779-eb69-43d0-a897-106c88468536", "/v3/stops/route/" + route.getRouteId() + "/route_type/" + route_type + "?direction_id=" + direction_id, 3001241);
            Stops stopsByRoute = getJSON.readStopData(getRouteStopData);
            boolean found = false;
            double lat = 0;
            double lon = 0;
            double distance;
            double min = Integer.MAX_VALUE;
            String name = "";

            // if its found or the location stops is not empty
            for (Stops sLocation : stopsLocation.stops) {
                for (Stops sByRoute : stopsByRoute.stops) {
                    if (sLocation.getStop_id() == sByRoute.getStop_id()
                            && sLocation.getStopSequence() == sByRoute.getStopSequence()) {
                        distance = sLocation.getStop_distance();
                        if (min > distance) {
                            min = distance;
                            name = sLocation.getStop_name();
                            lat = sLocation.getStop_latitude();
                            lon = sLocation.getStop_longitude();
                            stop_id = sByRoute.getStop_id();
                            this.name = name;
                            this.distance_ = distance;
                        }
                        found = true;
                    }
                }
            }
            if (found == false) {
                Toast.makeText(MapActivity.this, "Couldn't Find Close Stop", Toast.LENGTH_SHORT).show();
            } else {
                // find closest departure that matches criteria
                LatLng latLng = new LatLng(lat, lon);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, defaultZoom);
                mMap.animateCamera(cameraUpdate);
                String getDepartures = generateSignature_.generateCompleteURLWithSignature("5057f779-eb69-43d0-a897-106c88468536", "/v3/departures/route_type/" + route_type + "/stop/" + stop_id + "/route/" + route.getRouteId() + "?look_backwards=false&max_results=5", 3001241);
                Departures departures = getJSON.readDepartures(getDepartures);
                this.lat = lat;
                this.lon = lon;

                String id = databaseReference.push().getKey();
                BusRoutes BusRouteInfo = new BusRoutes(search, lat, lon, stop_id, direction_name1);
                databaseReference.child(id).setValue(BusRouteInfo);
                getDepartures(lat, lon, departures, search,search,search,search,search, direction_name1,direction_name1,direction_name1,direction_name1,direction_name1);

            }


        }catch (Exception e) {
        }
    }

        public void getDepartures(double lat, double lon, Departures departures_, String RouteName1, String RouteName2,  String RouteName3, String RouteName4, String RouteName5, String directionName1, String directionName2,String directionName3,String directionName4,String directionName5){
                this.departures = departures_;
                direction_name1 = directionName1;
            direction_name2 = directionName2;
            direction_name3 = directionName3;
            direction_name4 = directionName4;
            direction_name5 = directionName5;

            routeName1 = RouteName1;
            routeName2 = RouteName2;
            routeName3 = RouteName3;
            routeName4 = RouteName4;
            routeName5 = RouteName5;

            if(isIntimer == true){
                timer.cancel();
                timer = new Timer();
            }
            if(FullRoute.isTimerOn){
                FullRoute.timerTask.cancel();
            }


                if (departures.getDepartures().size() == 0) {
                    swipeRefreshLayout.setVisibility(View.VISIBLE);
                    txt.setText("Next Departure: Nothing Currently Scheduled");

                } else {
                    int icon;
                    if(route_type == 1){
                        icon = R.drawable.tramway;
                        drawable = R.drawable.ic_tram_black_24dp;
                        red = 47; green = 216; blue = 41;
                    }else{
                        icon = R.drawable.busstopicon;
                        drawable = R.drawable.ic_directions_bus_black_24dp;
                        red = 255; green = 140; blue = 0;
                    }
                    String title;
                    String snippet;

                    swipeRefreshLayout.setVisibility(View.VISIBLE);
                    mMap.clear();

                    if(isDragged){
                        getGoogleDirections(dlat, dlon,
                                lat, lon, red, green, blue);
                    }else{
                        getGoogleDirections(currentLocation.getLatitude(), currentLocation.getLongitude(),
                                lat, lon, red, green, blue);
                    }


                    routeDuration = gDirections.routes.get(0).legs.get(0).duration.getText();
                    walkTime.setText(routeDuration);
                    final int walkTimeDuration = (int) (gDirections.routes.get(0).legs.get(0).duration.getValue() / 60);
                    run_id1 = departures.getDepartures().get(0).getRun_id();
                    run_id2 = departures.getDepartures().get(1).getRun_id();
                    run_id3 = departures.getDepartures().get(2).getRun_id();
                    run_id4 = departures.getDepartures().get(3).getRun_id();
                    run_id5 = departures.getDepartures().get(4).getRun_id();


                    final DecimalFormat decimalFormat = new DecimalFormat("##");
                    this.distance_ =  gDirections.routes.get(0).legs.get(0).distance.getValue();
                    if(distance_ > 0){
                        title = name;
                        snippet = "Distance " + ((int)(distance_)) + "m";
                    }else{
                        title = name;
                        snippet = "";
                    }
                    mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(m.getPosition().latitude, m.getPosition().longitude))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
                    marker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(lat,lon))
                            .icon(BitmapDescriptorFactory.fromResource(icon))
                            .title(title)
                            .snippet(snippet));
                    marker.showInfoWindow();


                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            isIntimer = true;
                            String time = departures.getDepartures().get(0).getScheduled_departure_utc();

                            if (departures.getDepartures().get(0).getEstimated_departure_utc() != null) {
                                time = departures.getDepartures().get(0).getEstimated_departure_utc();
                            }
                            String time1 = departures.getDepartures().get(1).getScheduled_departure_utc();

                            if (departures.getDepartures().get(1).getEstimated_departure_utc() != null) {
                                time1 = departures.getDepartures().get(1).getEstimated_departure_utc();
                            }
                            String time2 = departures.getDepartures().get(2).getScheduled_departure_utc();

                            if (departures.getDepartures().get(2).getEstimated_departure_utc() != null) {
                                time2 = departures.getDepartures().get(2).getEstimated_departure_utc();
                            }
                            String time3 = departures.getDepartures().get(3).getScheduled_departure_utc();

                            if (departures.getDepartures().get(3).getEstimated_departure_utc() != null) {
                                time3 = departures.getDepartures().get(3).getEstimated_departure_utc();
                            }
                            String time4 = departures.getDepartures().get(4).getScheduled_departure_utc();

                            if (departures.getDepartures().get(4).getEstimated_departure_utc() != null) {
                                time4 = departures.getDepartures().get(4).getEstimated_departure_utc();
                            }
                            DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                            m_ISO8601Local.setTimeZone(TimeZone.getTimeZone("UTC"));

                            Date date = null;
                            Date date1 = null;
                            Date date2 = null;
                            Date date3 = null;
                            Date date4 = null;
                            try {
                                date = m_ISO8601Local.parse(time);
                                date1 = m_ISO8601Local.parse(time1);
                                date2 = m_ISO8601Local.parse(time2);
                                date3 = m_ISO8601Local.parse(time3);
                                date4 = m_ISO8601Local.parse(time4);
                            } catch (ParseException e) {
                                }
                            long departureTime = date.getTime();
                            long departureTime1 = date1.getTime();
                            long departureTime2 = date2.getTime();
                            long departureTime3 = date3.getTime();
                            long departureTime4 = date4.getTime();
                            DateFormat currentTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                            currentTime.setTimeZone(TimeZone.getTimeZone("UTC"));
                            Date currentDate = new Date();
                            currentTime.format(currentDate);
                            current = currentDate.getTime();
                            long diff = departureTime - current;
                            long diff1 = departureTime1 - current;
                            long diff2 = departureTime2 - current;
                            long diff3 = departureTime3 - current;
                            long diff4 = departureTime4 - current;

                            int mins = (int) (diff / (60 * 1000));
                            actualMins = mins;
                            int mins1 = (int) (diff1 / (60 * 1000));
                            actualMins1 = mins1;
                            int mins2 = (int) (diff2 / (60 * 1000));
                            actualMins2 = mins2;
                            int mins3 = (int) (diff3 / (60 * 1000));
                            actualMins3 = mins3;
                            int mins4 = (int) (diff4 / (60 * 1000));
                            actualMins4 = mins4;

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    timeLogic.setupLocation(txt,txt1,txt2,txt3,txt4,actualMins, actualMins1, actualMins2, actualMins3, actualMins4, walkTimeDuration, drawable);
                                    timeLogic.updateUi(txt,txt1,txt2,txt3,txt4,actualMins, actualMins1, actualMins2, actualMins3, actualMins4, routeName1,routeName2,routeName3,routeName4,routeName5, direction_name1,direction_name2,direction_name3,direction_name4,direction_name5);
                                }
                            });



                        }
                    }, 10, 5000);

                    // parse into new method for logic on the pattern


                }
                //now have time until next departure and can parse into new method to get directions


            }





    // method will use googles direction api in order to see how long it will take
    // to walk to a stop, the if the time < then estimated time it will tell user to go
    // for the next bus.
    // alon alon -> a stands for arrival destination
    // dlat dlon --> o stands for origin dest
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void getGoogleDirections(double oLat, double oLon, double aLat, double aLon, int red, int green, int blue) {

        String request = "https://maps.googleapis.com/maps/api/directions/json?" +
                "origin=" + oLat + "," + oLon + "&destination=" + aLat + "," + aLon +
                "&mode=walking&key=AIzaSyCSRUY-53Lu2q85W_edxPxEFPaZ2aunr0o";

        try {
            gDirections = getJSON.readGoogleDepartures(request);
            List<LatLng> allPoints = PolyUtil.decode(gDirections.routes.get(0).overview_polyline.getPoints());
            polyline = mMap.addPolyline(new PolylineOptions()
                    .width(20)
                    .color(Color.rgb(red, green, blue))
                    .addAll(allPoints)
                    .jointType(2));

        } catch (Exception e) {

        }
    }

    // will show when user clicks the toggle button, this should allow a user to see if a bus has
    // arrived at a stop...hopefully
    public void myOnClickMethod(MenuItem menu) {
        mMap.clear();
        inSearch = false;
        int icon;
        if(route_type == 1){
            icon = R.drawable.tramway;
        }else{
            icon = R.drawable.busstopicon;
        }

            drawer.closeDrawer(Gravity.LEFT);
            final List<Marker> markerList = new ArrayList<>();
            for (Stops s : stopsLocation.stops) {

                Marker marker_ = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(s.getStop_latitude(), s.getStop_longitude()))
                        .title(s.getStop_name())
                        .icon(BitmapDescriptorFactory.fromResource(icon)));
                marker_.setTag(s.getStop_id());

                markerList.add(marker_);
            }
        this.distance_ = 0;

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    if (polyline != null) {
                        polyline.remove();
                    }
                    try {


                        double lat = 0, lon = 0;
                        for (Marker m : markerList) {
                            if (((int) m.getTag()) == ((int) marker.getTag())) {
                                stop_id = ((int) marker.getTag());
                                lat = m.getPosition().latitude;
                                lon = m.getPosition().longitude;
                                name = m.getTitle();


                            }
                        }
                        getDepartures(stop_id, lat, lon);
                    }catch (Exception e){
                        marker.showInfoWindow();
                    }
                    return true;
                }
            });
            menu.setChecked(true);


    }

    public void textViewClick(View view){
        switch (view.getId()){
            case R.id.text:
                view.startAnimation(getAnimation());
                FullRoute.PatternRoute(marker,mMap,route_type,route_id1,run_id1,direction_id, actualMins);
                break;
            case R.id.text1:
                view.startAnimation(getAnimation());
                FullRoute.PatternRoute(marker,mMap,route_type,route_id2,run_id2,direction_id, actualMins1);
            case R.id.text2:
                view.startAnimation(getAnimation());
                FullRoute.PatternRoute(marker,mMap,route_type,route_id3,run_id3,direction_id, actualMins2);
                break;
            case R.id.text3:
                view.startAnimation(getAnimation());
                FullRoute.PatternRoute(marker,mMap,route_type,route_id4,run_id4,direction_id, actualMins3);
                break;
            case R.id.text4:
                view.startAnimation(getAnimation());
                FullRoute.PatternRoute(marker,mMap,route_type,route_id5,run_id5,direction_id, actualMins4);
                break;
        }
    }

    public void popUpSeekBarListner(MenuItem menu){
        relativeLayout_.setVisibility(View.VISIBLE);
        drawer.closeDrawer(Gravity.LEFT);
        menu.setChecked(true);
    }

    public Animation getAnimation(){
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(4);
        return anim;
    }

    public void getDepartures(int stop_id, double lat, double lon){
        this.lat = lat;
        this.lon = lon;
        if(marker != null){
            mMap.clear();
        }
        mMap.addMarker(new MarkerOptions()
        .position(new LatLng(m.getPosition().latitude,m.getPosition().longitude))
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        try {

            swipeRefreshLayout.setVisibility(View.VISIBLE);
            String dep = generateSignature_.generateCompleteURLWithSignature("5057f779-eb69-43d0-a897-106c88468536", "/v3/departures/route_type/" + route_type + "/stop/" + stop_id +"?look_backwards=false&max_results=5", 3001241);
            Departures departures_ = getJSON.readDepartures(dep);
            route_id1 = departures_.getDepartures().get(0).getRoute_id();
            route_id2 = departures_.getDepartures().get(1).getRoute_id();
            route_id3 = departures_.getDepartures().get(2).getRoute_id();
            route_id4 = departures_.getDepartures().get(3).getRoute_id();
            route_id5 = departures_.getDepartures().get(4).getRoute_id();

            String pattern1 = generateSignature_.generateCompleteURLWithSignature("5057f779-eb69-43d0-a897-106c88468536", "/v3/pattern/run/" + departures_.getDepartures().get(0).getRun_id() + "/route_type/" + route_type + "?expand=all", 3001241);
            String pattern2 = generateSignature_.generateCompleteURLWithSignature("5057f779-eb69-43d0-a897-106c88468536", "/v3/pattern/run/" + departures_.getDepartures().get(1).getRun_id() + "/route_type/" + route_type + "?expand=all", 3001241);
            String pattern3 = generateSignature_.generateCompleteURLWithSignature("5057f779-eb69-43d0-a897-106c88468536", "/v3/pattern/run/" + departures_.getDepartures().get(2).getRun_id() + "/route_type/" + route_type + "?expand=all", 3001241);
            String pattern4 = generateSignature_.generateCompleteURLWithSignature("5057f779-eb69-43d0-a897-106c88468536", "/v3/pattern/run/" + departures_.getDepartures().get(3).getRun_id() + "/route_type/" + route_type + "?expand=all", 3001241);
            String pattern5 = generateSignature_.generateCompleteURLWithSignature("5057f779-eb69-43d0-a897-106c88468536", "/v3/pattern/run/" + departures_.getDepartures().get(4).getRun_id() + "/route_type/" + route_type + "?expand=all", 3001241);
            Pattern pattern_1 = getJSON.readPatternData(pattern1);
            Pattern pattern_2 = getJSON.readPatternData(pattern2);
            Pattern pattern_3 = getJSON.readPatternData(pattern3);
            Pattern pattern_4 = getJSON.readPatternData(pattern4);
            Pattern pattern_5 = getJSON.readPatternData(pattern5);
            // get the route name
            String name1 = "";
            String name2 = "";
            String name3 = "";
            String name4 = "";
            String name5 = "";

            String directionName1 = "";
            String directionName2 = "";
            String directionName3 = "";
            String directionName4 = "";
            String directionName5 = "";

            for (Map.Entry<String, Route> d : pattern_1.getRoutes().entrySet()) {
                name1 = d.getValue().getRoute_number();
              directionName1 = d.getValue().getRoute_name();
            }
            for (Map.Entry<String, Route> d : pattern_2.getRoutes().entrySet()) {
                name2 = d.getValue().getRoute_number();
                directionName2 = d.getValue().getRoute_name();
            }
            for (Map.Entry<String, Route> d : pattern_3.getRoutes().entrySet()) {
                name3 = d.getValue().getRoute_number();
                directionName3 = d.getValue().getRoute_name();
            }
            for (Map.Entry<String, Route> d : pattern_4.getRoutes().entrySet()) {
                name4 = d.getValue().getRoute_number();
                directionName4 = d.getValue().getRoute_name();
            }
            for (Map.Entry<String, Route> d : pattern_5.getRoutes().entrySet()) {
                name5 = d.getValue().getRoute_number();
                directionName5 = d.getValue().getRoute_name();
            }

           button_to.setVisibility(View.GONE);
            button_from.setVisibility(View.GONE);

            getDepartures(lat, lon, departures_, name1,name2,name3,name4,name5,directionName1,directionName2,directionName3,directionName4,directionName5);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void changeRouteListener(MenuItem menu){
            if(count % 2 == 0){
                //set route type
                route_type = 1;
                menu.setIcon(R.drawable.ic_tram_black_24dp);
                FirebaseStorage storage = FirebaseStorage.getInstance();
                // get suggestions from firebase storage
                StorageReference storageReference = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/bustracker-fce71.appspot.com/o/TramRoutes.txt?alt=media&token=207c1c4b-084d-4877-b1d8-e0b14c6018d7");
                storageReference.getBytes(1024*1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        String s = new String(bytes);

                        s = s.replaceAll("\r\n",":");
                        s = s.replaceAll("\n", ":");
                        String[] parts = s.split(":");
                        if(suggestions.size() > 0){
                            suggestions.clear();
                        }
                        for(int i = 0; i < parts.length; i++){
                            suggestions.add(parts[i]);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
                menu.setTitle("Switch to Bus");
                menu.setChecked(true);
            }else{
                route_type = 2;
                menu.setIcon(R.drawable.ic_directions_bus_black_24dp);
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/bustracker-fce71.appspot.com/o/BusRoutes.txt?alt=media&token=cc9d9017-87e0-4bd3-ae1a-388cc2a79fb6");
                storageReference.getBytes(1024*1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        String s = new String(bytes);

                        s = s.replaceAll("\r\n",":");
                        s = s.replaceAll("\n", ":");
                        String[] parts = s.split(":");
                        if(suggestions.size() > 0){
                            suggestions.clear();
                        }
                        for(int i = 0; i < parts.length; i++){
                            suggestions.add(parts[i]);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
                menu.setTitle("Switch to Tram");
                menu.setChecked(false);

            }

        getDeviceLocation();
            count++;
    }


}
