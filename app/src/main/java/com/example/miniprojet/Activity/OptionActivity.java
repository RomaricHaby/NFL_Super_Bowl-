package com.example.miniprojet.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.miniprojet.Model.TeamHelmet;
import com.example.miniprojet.R;

import java.util.ArrayList;

public class OptionActivity extends AppCompatActivity {
    private final int requestFavoriteTeam = 1;

    private ToggleButton button;
    private ConstraintLayout layout;
    private TextView textView;
    private TextView favoriteTeamTextView;
    private ImageView gearOption;
    private SharedPreferences preferences;

    private ImageView favoriteTeamButton;


    private ArrayList<TeamHelmet> teamHelmetArrayList;

    private Button save;


    //data to return
    private Intent returnIntent = new Intent();
    private TeamHelmet teamHelmetFavorite;
    private String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        getWidget();

        teamHelmetArrayList = (ArrayList<TeamHelmet>) getIntent().getSerializableExtra("helmet");

        //teamHelmetFavorite = teamHelmetArrayList.get(0); // default team
        save.setBackgroundColor(Color.parseColor(teamHelmetArrayList.get(0).getColor()));

        color = getIntent().getStringExtra("color");

        teamHelmetFavorite = (TeamHelmet) getIntent().getSerializableExtra("favoriteTeam");
        favoriteTeamButton.setImageResource(teamHelmetFavorite.getHelmet());


        if(color.equals("w")){
            setLightmode();
        }
        else{
            setDarkmode();
        }

        setModeColorButton();

        setFavoriteTeamButton();
        setSaveButton();

    }

    public void setSaveButton(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnIntent.putExtra("color",color);
                returnIntent.putExtra("team",teamHelmetFavorite);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }

    public void setModeColorButton(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button.isChecked()){ //on light off dark
                    setLightmode();
                    color = "w";
                }
                else{
                    setDarkmode();
                    color = "b";
                }
            }
        });
    }

    public void setFavoriteTeamButton(){
        favoriteTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionActivity.this,HelmetTeamActivity.class);
                intent.putExtra("helmet", teamHelmetArrayList);
                intent.putExtra("color", getIntent().getStringExtra("color"));
                startActivityForResult(intent,requestFavoriteTeam);
            }
        });
    }

    public void setDarkmode(){

        Window window = getWindow();
        window.setNavigationBarColor(getResources().getColor(R.color.black));


        layout.setBackgroundColor(getResources().getColor(R.color.black));
        button.setTextColor(getResources().getColor(R.color.black));
        button.setChecked(false);
        textView.setTextColor(getResources().getColor(R.color.white));
        favoriteTeamTextView.setTextColor(getResources().getColor(R.color.white));
        gearOption.setImageResource(R.drawable.gear_option);
    }
    public void setLightmode(){
        Window window = getWindow();
        window.setNavigationBarColor(getResources().getColor(R.color.white));


        layout.setBackgroundColor(getResources().getColor(R.color.white));
        button.setTextColor(getResources().getColor(R.color.white));
        button.setChecked(true);
        textView.setTextColor(getResources().getColor(R.color.black));
        favoriteTeamTextView.setTextColor(getResources().getColor(R.color.black));
        gearOption.setImageResource(R.drawable.gear_option_black);
    }


    public void getWidget(){
        save = findViewById(R.id.buttonSaveOption);
        favoriteTeamTextView = findViewById(R.id.teamFavorite);
        favoriteTeamButton = findViewById(R.id.helmet);
        button = findViewById(R.id.toggleButton);
        layout = findViewById(R.id.layoutOption);
        textView = findViewById(R.id.textOption);
        gearOption = findViewById(R.id.gearOption);
        preferences = getPreferences(MODE_PRIVATE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case requestFavoriteTeam:
                if(resultCode == Activity.RESULT_OK) {
                    teamHelmetFavorite = (TeamHelmet) data.getSerializableExtra("team");
                    favoriteTeamButton.setImageResource(teamHelmetFavorite.getHelmet());
                    save.setBackgroundColor(Color.parseColor(teamHelmetFavorite.getColor()));
                }
                break;
        }
    }

}