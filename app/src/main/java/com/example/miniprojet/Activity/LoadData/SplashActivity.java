package com.example.miniprojet.Activity.LoadData;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Window;
import android.widget.Toast;
import com.example.miniprojet.API.Async_task_data;
import com.example.miniprojet.Activity.MainActivity;
import com.example.miniprojet.Model.SuperBowl;
import com.example.miniprojet.R;

import java.util.ArrayList;

public class SplashActivity extends Activity {
    public static ArrayList<SuperBowl> superBowlArrayList = new ArrayList<>();

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 4500;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Window window = getWindow();
        window.setNavigationBarColor(Color.BLACK);
        window.setStatusBarColor(Color.BLACK);


        if (haveInternetConnection() == true)
        {
            new Async_task_data().execute(superBowlArrayList);
        }
        else
        {
            Toast.makeText(SplashActivity.this, "Pas de connexion internet", Toast.LENGTH_SHORT).show();
        }


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                mainIntent.putExtra("list", superBowlArrayList);

                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private boolean haveInternetConnection(){
        // Fonction haveInternetConnection : return true si connect??, return false dans le cas contraire
        NetworkInfo network = ((ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (network == null || !network.isConnected())
        {
            showDialog();
            return false;
        }
        if (network.isRoaming())
        {
            // Si tu as besoin d???ex??cuter une tache sp??ciale si le p??riph??rique est connect?? ?? Internet en roaming (pour afficher un message pr??venant des surco??ts op??rateurs par exemple)
            // Si inutile, supprime la condition
        }
        // Le p??riph??rique est connect?? ?? Internet
        return true;
    }


    private void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Connect to wifi or quit")
                .setCancelable(false)
                .setPositiveButton("Connect to WIFI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }




}