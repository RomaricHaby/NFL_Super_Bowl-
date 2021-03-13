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
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private final int requestFiltreTeam = 1;


    private SuperBowlAdapter adapter;
    public static ArrayList<SuperBowl> superBowlArrayList = new ArrayList<>();
    public static ArrayList<SuperBowl> filter = new ArrayList<>();
    private ListView listView;

    private String TAG = "MainActivity";


    //Button menue
    private ConstraintLayout constraintLayout;
    private Button option;
    private ImageView helmetButton;
    private ImageView cupButton;
    private ImageView mapButton;


    private TreeMap<Integer, String> map = new TreeMap<Integer, String>();
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


    //todo check connexion avant affichage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Window window = getWindow();
        window.setNavigationBarColor(Color.BLACK);


        setWidget();

        superBowlArrayList = (ArrayList<SuperBowl>) getIntent().getSerializableExtra("list");
        setUpList(superBowlArrayList);

        setButtonImg();
        setButtonCup();
        setTreeMap();
        setMapButton();
        setListSuperBowlListeneer();
        setButtonOption();
    }

    public void setWidget(){
        helmetButton = findViewById(R.id.helmetImageMain);
        constraintLayout = findViewById(R.id.linearLayout);
        cupButton = findViewById(R.id.cup);
        mapButton = findViewById(R.id.imgMaps);
        option = findViewById(R.id.option);
    }

    public void setButtonOption(){
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }

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

    public void setButtonImg(){
        helmetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelmetTeamActivity.class);
                startActivityForResult(intent,requestFiltreTeam);
            }
        });
    }

    private void setUpList(ArrayList<SuperBowl> list)
    {
        listView = (ListView) findViewById(R.id.SuperBowlListView);
        adapter = new SuperBowlAdapter(getApplicationContext(), 0, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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