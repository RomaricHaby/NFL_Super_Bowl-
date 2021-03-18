package com.example.miniprojet.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.miniprojet.R;

public class OptionActivity extends AppCompatActivity {
    private ToggleButton button;
    private ConstraintLayout layout;
    private TextView textView;
    private ImageView gearOption;
    private SharedPreferences preferences;

    private Intent returnIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        getWidget();

        if(getIntent().getStringExtra("color").equals("w")){
            setLightmode();
        }
        else{
            setDarkmode();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button.isChecked()){ //on light off dark
                    setLightmode();

                    returnIntent.putExtra("color","w");
                    setResult(Activity.RESULT_OK,returnIntent);
                }
                else{
                    setDarkmode();

                    returnIntent.putExtra("color","b");
                    setResult(Activity.RESULT_OK,returnIntent);
                }
            }
        });

    }

    public void setDarkmode(){
        layout.setBackgroundColor(getResources().getColor(R.color.black));
        button.setTextColor(getResources().getColor(R.color.black));
        button.setChecked(false);
        textView.setTextColor(getResources().getColor(R.color.white));
        gearOption.setImageResource(R.drawable.gear_option);
    }
    public void setLightmode(){
        layout.setBackgroundColor(getResources().getColor(R.color.white));
        button.setTextColor(getResources().getColor(R.color.white));
        button.setChecked(true);
        textView.setTextColor(getResources().getColor(R.color.black));
        gearOption.setImageResource(R.drawable.gear_option_black);
    }


    public void getWidget(){
        button = findViewById(R.id.toggleButton);
        layout = findViewById(R.id.layoutOption);
        textView = findViewById(R.id.textOption);
        gearOption = findViewById(R.id.gearOption);
        preferences = getPreferences(MODE_PRIVATE);
    }
}