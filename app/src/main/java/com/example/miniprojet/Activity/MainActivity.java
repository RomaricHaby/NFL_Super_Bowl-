package com.example.miniprojet.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.miniprojet.Activity.Chart.ChartActivityCourbe;
import com.example.miniprojet.Adapter.SuperBowlAdapter;
import com.example.miniprojet.Model.SuperBowl;
import com.example.miniprojet.Model.TeamHelmet;
import com.example.miniprojet.R;
import java.util.ArrayList;
import java.util.TreeMap;

import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity {
    private final int requestFiltreTeam = 1;
    private final int requestOption= 2;
    private String TAG = "MainActivity";

    //list view
    public static ArrayList<SuperBowl> superBowlArrayList = new ArrayList<>();
    private SuperBowlAdapter adapter;
    private ListView listView;

    //super bowl filter
    public static ArrayList<SuperBowl> filter = new ArrayList<>();


    //Button menu
    private ConstraintLayout constraintLayout; // change color by team
    private Button option;
    private Button chart;
    private ImageView helmetButton;
    private ImageView cupButton;
    private ImageView mapButton;


    //arraylist of all team
    private ArrayList<TeamHelmet> teamHelmetArrayList = new ArrayList<>();

    //integer to roman
    private TreeMap<Integer, String> map = new TreeMap<Integer, String>();


    //mode dark and light / preferences
    private SharedPreferences sharedPref;
    private String color;
    private TeamHelmet favoriteTeam;


    private ConstraintLayout backgroundListView;


    private  TeamHelmet currentTeam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //recover widget from layout
        setWidget();


        //mode dark light
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        color = sharedPref.getString("modeColor", "null");


        //set up for helmet
        setArraylist();
        setButtonImg();

        //set arraylist from https requete
        superBowlArrayList = (ArrayList<SuperBowl>) getIntent().getSerializableExtra("list");
        currentTeam = teamHelmetArrayList.get(0);


        findTeamHelmetByName(sharedPref.getString("teamFavorite", "null")); //favorite team

        if(favoriteTeam.getName().contains("défaut")){
            setUpList(superBowlArrayList,favoriteTeam.getColor());
            setUIColor(favoriteTeam);
        }
        else{
            filter(favoriteTeam.getName(),favoriteTeam.getColor());
            setUIColor(favoriteTeam);
        }

        //filter superbowl
        setButtonCup();

        //integer to roman
        setTreeMap();

        //Maps all stadium
        setMapButton();

        //Detail super bowl
        setListSuperBowlListener();

        //Option
        setButtonOption();
        setButtonChart();

        // light dark mode
        checKModeColor();

        Log.e(TAG, superBowlArrayList.get(0).getSb() );
    }

    public void checKModeColor(){
        if(color.equals("w")){
            setLightmode();
        }
        else {
            setDarkmode();
        }
    }

    public void setUIColor(TeamHelmet teamHelmet){
        helmetButton.setImageResource(teamHelmet.getHelmet());
        constraintLayout.setBackgroundColor(Color.parseColor(teamHelmet.getColor()));

        Window window = getWindow();
        window.setNavigationBarColor(Color.parseColor(teamHelmet.getColor()));
        window.setStatusBarColor(Color.parseColor(teamHelmet.getColor()));

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor(teamHelmet.getColor()));
        window.setNavigationBarColor(Color.parseColor(teamHelmet.getColor()));
    }


    //setup Dark ligth mode
    public void setDarkmode(){
        backgroundListView.setBackgroundColor(getResources().getColor(R.color.black));
    }
    public void setLightmode() {
        backgroundListView.setBackgroundColor(getResources().getColor(R.color.white));
    }



    //setup main view
    public void setWidget(){
        chart = findViewById(R.id.chartButton);
        helmetButton = findViewById(R.id.helmetImageMain);
        constraintLayout = findViewById(R.id.linearLayout);
        backgroundListView = findViewById(R.id.LayoutMain);
        cupButton = findViewById(R.id.cup);
        mapButton = findViewById(R.id.imgMaps);
        option = findViewById(R.id.option);
    }

    private void setUpList(ArrayList<SuperBowl> list,String colorTeam)
    {
        listView = (ListView) findViewById(R.id.SuperBowlListView);
        adapter = new SuperBowlAdapter(getApplicationContext(), 0, list, teamHelmetArrayList,color,colorTeam);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //conversion to roman number
    public void setTreeMap(){
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }
    public String toRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }


    //helmet
    public void setArraylist(){
        teamHelmetArrayList.add(new TeamHelmet( getString(R.string.Default_team),R.drawable.default_helmet, "#003268"));

        teamHelmetArrayList.add(new TeamHelmet( "Buffalo Bills",R.drawable.bills_buffalo,"#143D75"));
        teamHelmetArrayList.add(new TeamHelmet( "Miami Dolphins",R.drawable.dolphins_miami,"#006C6E"));
        teamHelmetArrayList.add(new TeamHelmet("New England Patriots",R.drawable.patriots_n_angleterre,"#00214A"));
        teamHelmetArrayList.add(new TeamHelmet("New York Jets",R.drawable.jets_new_york,"#055032"));


        teamHelmetArrayList.add(new TeamHelmet("Baltimore Ravens",R.drawable.ravens_baltimore,"#2D3075"));
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
    public void setButtonImg(){
        helmetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelmetTeamActivity.class);
                intent.putExtra("helmet",teamHelmetArrayList);
                intent.putExtra("color",color);
                startActivityForResult(intent,requestFiltreTeam);
            }
        });
    }

    //option
    public void setButtonOption(){
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,OptionActivity.class);
                intent.putExtra("color",color);
                intent.putExtra("helmet",teamHelmetArrayList);
                intent.putExtra("favoriteTeam",favoriteTeam);
                startActivityForResult(intent,requestOption);
            }
        });
    }
    public void setButtonChart(){
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChartActivityCourbe.class);
                intent.putExtra("superBowl",superBowlArrayList);
                startActivity(intent);
            }
        });
    }

    //to detail super bowl
    private void setListSuperBowlListener() {
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
               SuperBowl superBowl = (SuperBowl) adapterView.getItemAtPosition(index);
               Intent intent = new Intent(MainActivity.this, DetailSuperBowlActivity.class);
               intent.putExtra("superBowl",superBowl);
               intent.putExtra("helmet",teamHelmetArrayList);
               intent.putExtra("color",color);
               startActivity(intent);
           }
       });

    }

    //maps
    public void setMapButton(){

        final ArrayList<String> stadium = new ArrayList<>();

        Boolean flag = false;


        for (int i = 0; i < superBowlArrayList.size(); i++){
            for (int j = 0; j < stadium.size(); j++){
                if(stadium.get(j).equals(superBowlArrayList.get(i).getStadium())){
                    flag = true;
                }
            }

            if(!flag){
                stadium.add(superBowlArrayList.get(i).getStadium());
                flag = false;
            }
        }

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                intent.putExtra("stadium", stadium);
                startActivity(intent);
            }
        });
    }

    //filter
    public void setButtonCup(){
        cupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialog_num_superbowl);

                final EditText editText = dialog.findViewById(R.id.numSuperBowl);
                Button submitButton = dialog.findViewById(R.id.submit_button);

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!editText.getText().toString().isEmpty()){
                            Integer numSuperBowl = Integer.valueOf(editText.getText().toString());
                            String numSuperBowlRoman = toRoman(numSuperBowl);

                            Boolean exist = false;

                            for (int i = 0; i < superBowlArrayList.size(); i++){
                                if(numSuperBowlRoman.equals(superBowlArrayList.get(i).getSb())){
                                    Intent intent = new Intent(MainActivity.this, DetailSuperBowlActivity.class);
                                    intent.putExtra("superBowl",superBowlArrayList.get(i));
                                    intent.putExtra("helmet",teamHelmetArrayList);
                                    intent.putExtra("color",color);
                                    startActivity(intent);
                                    exist = true;
                                }
                            }

                            if(!exist){
                                Toast.makeText(MainActivity.this, R.string.superbowl_not_exist, Toast.LENGTH_SHORT).show();
                            }
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
    public void filter(String nameTeam, String colorTeam){
        filter.clear();

        //Colts, Raiders, RAM have changed names over the years so I manage the specific cases to display the correct helmet
        for(int i = 0; i < superBowlArrayList.size(); i++){
            if(nameTeam.equals("Indianapolis Colts")) {
                if (superBowlArrayList.get(i).getWinner().equals("Baltimore Colts") || superBowlArrayList.get(i).getLoser().equals("Baltimore Colts")) {
                    filter.add(superBowlArrayList.get(i));
                }
            }

            if(nameTeam.equals("Oakland Raiders")) {
                if (superBowlArrayList.get(i).getWinner().equals("Los Angeles Raiders") || superBowlArrayList.get(i).getLoser().equals("Los Angeles Raiders")) {
                    filter.add(superBowlArrayList.get(i));
                }
            }

            if(nameTeam.equals("Los Angeles Rams")) {
                if (superBowlArrayList.get(i).getWinner().equals("St. Louis Rams") || superBowlArrayList.get(i).getLoser().equals("St. Louis Rams")) {
                    filter.add(superBowlArrayList.get(i));
                }
            }

            if(superBowlArrayList.get(i).getWinner().equals(nameTeam) || superBowlArrayList.get(i).getLoser().equals(nameTeam) ){
                filter.add(superBowlArrayList.get(i));
            }
        }
        setUpList(filter,colorTeam);
    }

    //preferences
    public void findTeamHelmetByName(String nameTeam){
        if (nameTeam == "null" || nameTeam.contains("défaut")){
            favoriteTeam = teamHelmetArrayList.get(0);
        }
        else{
            for (int i = 0; i < teamHelmetArrayList.size(); i++){
                if(teamHelmetArrayList.get(i).getName().equals(nameTeam)){
                    favoriteTeam = teamHelmetArrayList.get(i);
                }
            }
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case requestFiltreTeam:
                if(resultCode == Activity.RESULT_OK){
                    currentTeam = (TeamHelmet) data.getSerializableExtra("team");

                    setUIColor(currentTeam);

                    if(!currentTeam.getName().equals(getString(R.string.Default_team))){
                        filter.clear();
                        filter(currentTeam.getName(), currentTeam.getColor());
                    }
                    else{
                        filter.clear();
                        currentTeam = teamHelmetArrayList.get(0);
                        setUpList(superBowlArrayList, currentTeam.getColor());
                    }
                }
                break;

            case requestOption:
                if(resultCode == Activity.RESULT_OK){
                    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();

                    TeamHelmet teamHelmet = (TeamHelmet) data.getSerializableExtra("team");

                    editor.putString("modeColor", data.getStringExtra("color"));
                    editor.putString("teamFavorite", teamHelmet.getName());
                    editor.apply();


                    sharedPref = getPreferences(Context.MODE_PRIVATE);
                    color = sharedPref.getString("modeColor", "null");
                    findTeamHelmetByName(sharedPref.getString("teamFavorite", "null"));


                    checKModeColor();

                    filter.clear();
                    currentTeam = teamHelmetArrayList.get(0);
                    setUpList(superBowlArrayList,currentTeam.getColor());

                    setUIColor(currentTeam);
                }
                break;
        }
    }


}