package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.example.miniprojet.Model.SuperBowl;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import static java.lang.Thread.sleep;

public class DetailSuperBowlActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SuperBowl superBowl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_super_bowl);

        superBowl = (SuperBowl) getIntent().getSerializableExtra("superBowl");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapsDetail);

        mapFragment.getMapAsync(this);




    }

    public void showMaps(){
        try {
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses;
            addresses = geocoder.getFromLocationName(superBowl.getStadium(), 1);

            if(addresses.size() > 0) {
                double latitude = addresses.get(0).getLatitude();
                double longitude = addresses.get(0).getLongitude();

                LatLng stadium = new LatLng(latitude, longitude);

                mMap.addMarker(new MarkerOptions()
                        .title(superBowl.getStadium())
                        .snippet("Superbowl " + superBowl.getSb())
                        .position(stadium));

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(stadium,15),6000,null);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        showMaps();
    }
}