package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.miniprojet.Adapter.HelmetTeamAdapter;
import com.example.miniprojet.Adapter.SuperBowlAdapter;
import com.example.miniprojet.Model.TeamHelmet;

import java.util.ArrayList;

public class HelmetTeamActivity extends AppCompatActivity {
    private ArrayList<TeamHelmet> teamHelmetArrayList = new ArrayList<>();
    private ListView listView;
    private HelmetTeamAdapter adapter;
    private Intent returnIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helmet_team);

        setArraylist();
        setUpList();
        setUpOnclickListener();
    }

    public void setUpOnclickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                TeamHelmet team = (TeamHelmet) adapterView.getItemAtPosition(index);
               returnIntent.putExtra("team", team);
               setResult(Activity.RESULT_OK,returnIntent);
               finish();
            }
        });
    }

    private void setUpList()
    {
        listView = (ListView) findViewById(R.id.helmetListView);
        adapter = new HelmetTeamAdapter(getApplicationContext(), 0, teamHelmetArrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setArraylist(){
        teamHelmetArrayList.add(new TeamHelmet( "Default team",R.drawable.default_helmet, "#003268"));

        teamHelmetArrayList.add(new TeamHelmet( "Buffalo Bills",R.drawable.bills_buffalo,"#143D75"));
        teamHelmetArrayList.add(new TeamHelmet( "Miami Dolphins",R.drawable.dolphins_miami,"#006C6E"));
        teamHelmetArrayList.add(new TeamHelmet("New England Patriots",R.drawable.patriots_n_angleterre,"#00214A"));
        teamHelmetArrayList.add(new TeamHelmet("New York Jets",R.drawable.jets_new_york,"#055032"));


        teamHelmetArrayList.add(new TeamHelmet("Baltimore Colts",R.drawable.ravens_baltimore,"#2D3075"));
        teamHelmetArrayList.add(new TeamHelmet("Cincinnati Bengals",R.drawable.bengals_cincinnati,"#F36A26"));
        teamHelmetArrayList.add(new TeamHelmet("Cleveland Browns",R.drawable.browns_cleveland,"#F36A24"));
        teamHelmetArrayList.add(new TeamHelmet("Pittsburgh Steelers",R.drawable.steelers_pittsburgh,"#FFC30D"));

        teamHelmetArrayList.add(new TeamHelmet("Houston Texans",R.drawable.texans_houston,"#B6061D"));
        teamHelmetArrayList.add(new TeamHelmet("Indianapolis Colts",R.drawable.colts_indianapolis,"#003689"));
        teamHelmetArrayList.add(new TeamHelmet("Jacksonville Jaguars",R.drawable.jaguars_jacksonville,"#01839B"));
        teamHelmetArrayList.add(new TeamHelmet("Tennessee Titans",R.drawable.titans_tennessee,"#32A3DB"));

        teamHelmetArrayList.add(new TeamHelmet("Denver Broncos",R.drawable.broncos_denver,"#F36A24"));
        teamHelmetArrayList.add(new TeamHelmet("Kansas City Chiefs",R.drawable.chiefs_kansas_city,"#C20010"));
        teamHelmetArrayList.add(new TeamHelmet("San Diego Chargers",R.drawable.chargers_los_angeles,"#002859"));
        teamHelmetArrayList.add(new TeamHelmet("Oakland Raiders",R.drawable.raiders_las_vegas,"#000000"));


        teamHelmetArrayList.add(new TeamHelmet("Dallas Cowboys",R.drawable.cowboys_dallas,"#0D2A52"));
        teamHelmetArrayList.add(new TeamHelmet("New York Giants",R.drawable.giants_new_york,"#003B7D"));
        teamHelmetArrayList.add(new TeamHelmet("Philadelphia Eagles",R.drawable.eagles_philadelphie,"#004049"));
        teamHelmetArrayList.add(new TeamHelmet("Washington Redskins",R.drawable.footbal_team_washington,"#7C1415"));


        teamHelmetArrayList.add(new TeamHelmet("Chicago Bears",R.drawable.bears_chicago,"#EA6626"));
        teamHelmetArrayList.add(new TeamHelmet("Detroit Lions",R.drawable.lions_detroit,"#006BAF"));
        teamHelmetArrayList.add(new TeamHelmet("Green Bay Packers",R.drawable.packers_green_bay,"#244729"));
        teamHelmetArrayList.add(new TeamHelmet("Minnesota Vikings",R.drawable.vinkings_minnesota,"#1A065B"));

        teamHelmetArrayList.add(new TeamHelmet("Atlanta Falcons",R.drawable.falcons_atlanta,"#B40D1F"));
        teamHelmetArrayList.add(new TeamHelmet("Carolina Panthers",R.drawable.panthers_caroline,"#0084C4"));
        teamHelmetArrayList.add(new TeamHelmet("New Orleans Saints",R.drawable.saints_nouvelle_orleans,"#C0A647"));
        teamHelmetArrayList.add(new TeamHelmet("Tampa Bay Buccaneers",R.drawable.buccaners_tampa_bay,"#BD152C"));

        teamHelmetArrayList.add(new TeamHelmet("Arizona Cardinals",R.drawable.cardinals_arizona,"#B00539"));
        teamHelmetArrayList.add(new TeamHelmet("Los Angeles Rams",R.drawable.rams_los_angeles,"#002859"));
        teamHelmetArrayList.add(new TeamHelmet("San Francisco 49ers",R.drawable.a49ers_san_francisco,"#C7001F"));
        teamHelmetArrayList.add(new TeamHelmet("Seattle Seahawks",R.drawable.seahawks_seattle,"#2C587D"));
    }
}