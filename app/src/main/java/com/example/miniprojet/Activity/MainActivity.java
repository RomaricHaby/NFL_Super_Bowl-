package com.example.miniprojet.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.miniprojet.Adapter.SuperBowlAdapter;
import com.example.miniprojet.Model.SuperBowl;
import com.example.miniprojet.Model.TeamHelmet;
import com.example.miniprojet.R;
import java.util.ArrayList;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    private final int requestFiltreTeam = 1;
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
    private ImageView helmetButton;
    private ImageView cupButton;
    private ImageView mapButton;


    //arraylist of all team
    private ArrayList<TeamHelmet> teamHelmetArrayList = new ArrayList<>();

    //integer to roman
    private TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setNavigationBarColor(Color.BLACK);
        }

        //recover widget from layout
        setWidget();

        //set arraylist from https requete
        superBowlArrayList = (ArrayList<SuperBowl>) getIntent().getSerializableExtra("list");
        setUpList(superBowlArrayList);

        //set up for helmet
        setArraylist();
        setButtonImg();

        //filter superbowl
        setButtonCup();

        //integer to roman
        setTreeMap();

        //Maps all stadium
        setMapButton();

        //Detail super bowl
        setListSuperBowlListeneer();

        //Option
        setButtonOption();

    }

    //setup main view
    public void setWidget(){
        helmetButton = findViewById(R.id.helmetImageMain);
        constraintLayout = findViewById(R.id.linearLayout);
        cupButton = findViewById(R.id.cup);
        mapButton = findViewById(R.id.imgMaps);
        option = findViewById(R.id.option);
    }
    private void setUpList(ArrayList<SuperBowl> list)
    {
        listView = (ListView) findViewById(R.id.SuperBowlListView);
        adapter = new SuperBowlAdapter(getApplicationContext(), 0, list);
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
    public void setButtonImg(){
        helmetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelmetTeamActivity.class);
                intent.putExtra("helmet",teamHelmetArrayList);
                startActivityForResult(intent,requestFiltreTeam);
            }
        });
    }

    //option
    public void setButtonOption(){
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }

    //to detail super bowl
    private void setListSuperBowlListeneer() {
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
               SuperBowl superBowl = (SuperBowl) adapterView.getItemAtPosition(index);
               Intent intent = new Intent(MainActivity.this, DetailSuperBowlActivity.class);
               intent.putExtra("superBowl",superBowl);
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
    public void filter(String nameTeam){
        filter.clear();
        for(int i = 0; i < superBowlArrayList.size(); i++){
            if(superBowlArrayList.get(i).getWinner().equals(nameTeam) || superBowlArrayList.get(i).getLoser().equals(nameTeam) ){
                filter.add(superBowlArrayList.get(i));
            }
        }
        setUpList(filter);
    }

    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case requestFiltreTeam:
                if(resultCode == Activity.RESULT_OK){
                    TeamHelmet team = (TeamHelmet) data.getSerializableExtra("team");
                    helmetButton.setImageResource(team.getHelmet());
                    constraintLayout.setBackgroundColor(Color.parseColor(team.getColor()));
                   /* Window window = getWindow();
                    window.setStatusBarColor(Color.parseColor(team.getColor()));*/

                    //this.getApplication().setTheme(R.style.AppTheme_NFL);

                    //window.setNavigationBarColor(Color.parseColor(team.getColor()));


                    if(!team.getName().equals(getString(R.string.Default_team))){
                        filter.clear();
                        filter(team.getName());
                    }
                    else{
                        setUpList(superBowlArrayList);
                    }
                }
                break;
        }
    }
}