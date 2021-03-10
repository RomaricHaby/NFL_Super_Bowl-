package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.miniprojet.API.Async_task_data;
import com.example.miniprojet.Adapter.SuperBowlAdapter;
import com.example.miniprojet.Model.SuperBowl;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private SuperBowlAdapter adapter;
    public static ArrayList<SuperBowl> superBowlArrayList = new ArrayList<>();
    public static ArrayList<SuperBowl> filter = new ArrayList<>();
    private ListView listView;

    private ImageView helmetButton;

    //todo check connexion avant affichage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpList();
        data();

        setButtonImg();
        //filter();
    }

    public void setButtonImg(){
        helmetButton = findViewById(R.id.helmetImageMain);

        helmetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelmetTeamActivity.class);
                startActivity(intent);
            }
        });
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
    public void filter(){
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(superBowlArrayList.size());
        for(int i = 0; i < superBowlArrayList.size(); i++){

            if(superBowlArrayList.get(i).getWinner().equals("Kansas City Chiefs") || superBowlArrayList.get(i).getLoser().equals("Kansas City Chiefs") ){
                filter.add(superBowlArrayList.get(i));
                System.out.println(superBowlArrayList.get(i).toString());
                System.out.println(i);
            }
        }

        listView = (ListView) findViewById(R.id.SuperBowlListView);
        adapter = new SuperBowlAdapter(getApplicationContext(), 0, filter);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}