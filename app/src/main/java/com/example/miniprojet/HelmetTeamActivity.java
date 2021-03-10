package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.miniprojet.Adapter.HelmetTeamAdapter;
import com.example.miniprojet.Adapter.SuperBowlAdapter;
import com.example.miniprojet.Model.TeamHelmet;

import java.util.ArrayList;

public class HelmetTeamActivity extends AppCompatActivity {
    private ArrayList<TeamHelmet> teamHelmetArrayList = new ArrayList<>();
    private ListView listView;
    private HelmetTeamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helmet_team);

        setArraylist();
        setUpList();
    }

    private void setUpList()
    {
        listView = (ListView) findViewById(R.id.helmetListView);
        adapter = new HelmetTeamAdapter(getApplicationContext(), 0, teamHelmetArrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setArraylist(){
        teamHelmetArrayList.add(new TeamHelmet( "Default team",R.drawable.default_helmet));

        teamHelmetArrayList.add(new TeamHelmet( "Buffalo Bills",R.drawable.bills_buffalo));
        teamHelmetArrayList.add(new TeamHelmet( "Miami Dolphins",R.drawable.dolphins_miami));
        teamHelmetArrayList.add(new TeamHelmet("New England Patriots",R.drawable.patriots_n_angleterre));
        teamHelmetArrayList.add(new TeamHelmet("New York Jets",R.drawable.jets_new_york));


        teamHelmetArrayList.add(new TeamHelmet("Baltimore Colts",R.drawable.ravens_baltimore));
        teamHelmetArrayList.add(new TeamHelmet("Cincinnati Bengals",R.drawable.bengals_cincinnati));
        teamHelmetArrayList.add(new TeamHelmet("Cleveland Browns",R.drawable.browns_cleveland));
        teamHelmetArrayList.add(new TeamHelmet("Pittsburgh Steelers",R.drawable.steelers_pittsburgh));

        teamHelmetArrayList.add(new TeamHelmet("Houston Texans",R.drawable.texans_houston));
        teamHelmetArrayList.add(new TeamHelmet("Indianapolis Colts",R.drawable.colts_indianapolis));
        teamHelmetArrayList.add(new TeamHelmet("Jacksonville Jaguars",R.drawable.jaguars_jacksonville));
        teamHelmetArrayList.add(new TeamHelmet("Tennessee Titans",R.drawable.titans_tennessee));

        teamHelmetArrayList.add(new TeamHelmet("Denver Broncos",R.drawable.broncos_denver));
        teamHelmetArrayList.add(new TeamHelmet("Kansas City Chiefs",R.drawable.chiefs_kansas_city));
        teamHelmetArrayList.add(new TeamHelmet("San Diego Chargers",R.drawable.chargers_los_angeles));
        teamHelmetArrayList.add(new TeamHelmet("Oakland Raiders",R.drawable.raiders_las_vegas));


        teamHelmetArrayList.add(new TeamHelmet("Dallas Cowboys",R.drawable.cowboys_dallas));
        teamHelmetArrayList.add(new TeamHelmet("New York Giants",R.drawable.giants_new_york));
        teamHelmetArrayList.add(new TeamHelmet("Philadelphia Eagles",R.drawable.eagles_philadelphie));
        teamHelmetArrayList.add(new TeamHelmet("Washington Redskins",R.drawable.footbal_team_washington));


        teamHelmetArrayList.add(new TeamHelmet("Chicago Bears",R.drawable.bears_chicago));
        teamHelmetArrayList.add(new TeamHelmet("Detroit Lions",R.drawable.lions_detroit));
        teamHelmetArrayList.add(new TeamHelmet("Green Bay Packers",R.drawable.packers_green_bay));
        teamHelmetArrayList.add(new TeamHelmet("Minnesota Vikings",R.drawable.vinkings_minnesota));

        teamHelmetArrayList.add(new TeamHelmet("Atlanta Falcons",R.drawable.falcons_atlanta));
        teamHelmetArrayList.add(new TeamHelmet("Carolina Panthers",R.drawable.panthers_caroline));
        teamHelmetArrayList.add(new TeamHelmet("New Orleans Saints",R.drawable.saints_nouvelle_orleans));
        teamHelmetArrayList.add(new TeamHelmet("Tampa Bay Buccaneers",R.drawable.buccaners_tampa_bay));

        teamHelmetArrayList.add(new TeamHelmet("Arizona Cardinals",R.drawable.cardinals_arizona));
        teamHelmetArrayList.add(new TeamHelmet("Los Angeles Rams",R.drawable.rams_los_angeles));
        teamHelmetArrayList.add(new TeamHelmet("San Francisco 49ers",R.drawable.a49ers_san_francisco));
        teamHelmetArrayList.add(new TeamHelmet("Seattle Seahawks",R.drawable.seahawks_seattle));
    }
}