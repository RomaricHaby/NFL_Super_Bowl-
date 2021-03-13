package com.example.miniprojet;

import androidx.fragment.app.FragmentActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Window;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<String> stadiumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Window window = getWindow();
        window.setNavigationBarColor(Color.BLACK);

        stadiumList = getIntent().getStringArrayListExtra("stadium");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapsAllStadium);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        for (int i = 0; i < stadiumList.size(); i++){
            try {
                Geocoder geocoder = new Geocoder(this);
                List<Address> addresses;
                addresses = geocoder.getFromLocationName(stadiumList.get(i), 1);

                if(!addresses.isEmpty()) {
                    double latitude = addresses.get(0).getLatitude();
                    double longitude = addresses.get(0).getLongitude();

                    LatLng stadium = new LatLng(latitude, longitude);

                    mMap.addMarker(new MarkerOptions()
                            .title(getString(R.string.Stadium))
                            .snippet(stadiumList.get(i))
                            .position(stadium));


                    LatLng usa = new LatLng(37.2755783,-104.6571311);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(usa,3),5000,null);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}