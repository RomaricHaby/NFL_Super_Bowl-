package com.example.miniprojet.Activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.miniprojet.Model.SuperBowl;
import com.example.miniprojet.Model.TeamHelmet;
import com.example.miniprojet.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailSuperBowlActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ArrayList<TeamHelmet> teamHelmetArrayList;

    private GoogleMap mMap;
    private SuperBowl superBowl;

    private TextView winner;
    private TextView qb_winner;
    private TextView coach_winner;

    private TextView loser;
    private TextView qb_loser;
    private TextView coach_loser;

    private TextView city;
    private TextView state;

    private TextView winner_point;
    private TextView looser_point;

    private ImageView winnerIMG;
    private ImageView looserIMG;

    private TextView sb;

    public  void getWidget(){
        winner = findViewById(R.id.winnerDetail);
        //qb_winner = findViewById(R.id.winnerDetail);
        //coach_winner = findViewById(R.id.winnerDetail);

        loser = findViewById(R.id.loserDetail);
        //qb_loser = findViewById(R.id.winnerDetail);
        //coach_loser = findViewById(R.id.winnerDetail);

        city = findViewById(R.id.cityDetail);
        state = findViewById(R.id.statesDetail);

        winner_point = findViewById(R.id.scoreWinnerDetail);
        looser_point = findViewById(R.id.scoreLooserDetail);

        winnerIMG = findViewById(R.id.winnerIMGDetail);
        looserIMG = findViewById(R.id.looserIMGDetail);

        sb = findViewById(R.id.sbDetail);
    }

    public void setDataWidget(){
        winner.setText(superBowl.getWinner());

        loser.setText(superBowl.getLoser());

        city.setText(getString(R.string.city) +" : " + superBowl.getCity());
        state.setText(getString(R.string.state) +" : "+ superBowl.getState());

        winner_point.setText(superBowl.getWinning_pts());
        looser_point.setText(superBowl.getLosing_pts());

        sb.setText("Super Bowl " + superBowl.getSb());

        for (int i = 0; i < teamHelmetArrayList.size(); i++){
            if (teamHelmetArrayList.get(i).getName().equals(superBowl.getWinner())){
                winnerIMG.setImageResource(teamHelmetArrayList.get(i).getHelmet());
            }
            else if(teamHelmetArrayList.get(i).getName().equals(superBowl.getLoser())){
                looserIMG.setImageResource(teamHelmetArrayList.get(i).getHelmet());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_super_bowl);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Window window = getWindow();
        window.setNavigationBarColor(Color.BLACK);

        getWidget();
        superBowl = (SuperBowl) getIntent().getSerializableExtra("superBowl");
        teamHelmetArrayList = (ArrayList<TeamHelmet>) getIntent().getSerializableExtra("helmet");

        setDataWidget();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapsDetail);

        mapFragment.getMapAsync(this);
    }

    public void showMaps(){
        try {
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses;
            addresses = geocoder.getFromLocationName(superBowl.getStadium(), 1);

            if(!addresses.isEmpty()) {
                double latitude = addresses.get(0).getLatitude();
                double longitude = addresses.get(0).getLongitude();

                LatLng stadium = new LatLng(latitude, longitude);

                mMap.addMarker(new MarkerOptions()
                        .title(superBowl.getStadium())
                        .snippet("Superbowl " + superBowl.getSb() + "  " + getString(R.string.viewer) + " : " + superBowl.getAttendance())
                        .position(stadium));

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(stadium,15),5000,null);
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