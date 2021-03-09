package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.miniprojet.API.Async_task_data;
import com.example.miniprojet.Adapter.SuperBowlAdapter;
import com.example.miniprojet.Model.SuperBowl;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private SuperBowlAdapter adapter;
    public static ArrayList<SuperBowl> superBowlArrayList = new ArrayList<>();
    private ListView listView;

    //todo check connexion avant affichage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpList();
        data();
    }

    public void data(){
        new Async_task_data().execute(superBowlArrayList,adapter);
    }

    private void setUpList()
    {
        listView = (ListView) findViewById(R.id.SuperBowlListView);
        adapter = new SuperBowlAdapter(getApplicationContext(), 0, superBowlArrayList);
        listView.setAdapter(adapter);
    }
}