package com.example.miniprojet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.miniprojet.Adapter.HelmetTeamAdapter;
import com.example.miniprojet.Adapter.SuperBowlAdapter;
import com.example.miniprojet.Model.TeamHelmet;
import com.example.miniprojet.R;

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

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Window window = getWindow();
        window.setNavigationBarColor(Color.BLACK);

        teamHelmetArrayList = (ArrayList<TeamHelmet>) getIntent().getSerializableExtra("helmet");

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

}