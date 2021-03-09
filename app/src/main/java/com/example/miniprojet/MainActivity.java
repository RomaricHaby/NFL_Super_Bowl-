package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.miniprojet.API.Async_task_data;
import com.example.miniprojet.Model.SuperBowl;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private ArrayList<SuperBowl> superBowlArrayList = new ArrayList<>();

    //todo check connexion avant affichage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data();

    }

    public void data(){
        new Async_task_data().execute(superBowlArrayList);
    }
}