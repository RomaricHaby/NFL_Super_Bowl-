package com.example.miniprojet.API;

import android.os.AsyncTask;

import com.example.miniprojet.Adapter.SuperBowlAdapter;
import com.example.miniprojet.Model.SuperBowl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Async_task_data extends AsyncTask<Object,Void,Boolean>  {
    private ArrayList<SuperBowl>  superBowlArrayList;
    private SuperBowlAdapter adapter;

    public void onPostExecute(Boolean flag){
        adapter.notifyDataSetChanged();
    }

    @Override
    protected Boolean doInBackground(Object... objects) {
       try {
           superBowlArrayList = (ArrayList<SuperBowl>) objects[0];
           adapter = (SuperBowlAdapter) objects[1];
           JSONObject data = getJSONObjectFromURL();

           //Key for parse Json
           JSONObject tmpKey = (JSONObject) data.getJSONObject("parameters");
           ArrayList<String> key = new ArrayList<>();
           for (int j = 0; j < tmpKey.getJSONArray("facet").length(); j++) {
               key.add(tmpKey.getJSONArray("facet").get(j).toString());
           }

           for (int i = 0; i< data.getJSONArray("records").length(); i++) {
               JSONObject tmp = (JSONObject) data.getJSONArray("records").get(i);
               JSONObject tmpSuperBowls = tmp.getJSONObject("fields");

               String winner = tmpSuperBowls.getString(key.get(0));
               String qb_winner = tmpSuperBowls.getString(key.get(1));
               String coach_winner = tmpSuperBowls.getString(key.get(2));

               String loser = tmpSuperBowls.getString(key.get(3));
               String qb_loser = tmpSuperBowls.getString(key.get(4));
               String coach_loser = tmpSuperBowls.getString(key.get(5));

               String city = tmpSuperBowls.getString(key.get(6));
               String state = tmpSuperBowls.getString(key.get(7));
               String stadium = tmpSuperBowls.getString(key.get(8));


               String winning_pts = tmpSuperBowls.getString(key.get(10));
               String losing_pts = tmpSuperBowls.getString(key.get(11));

               String sb = tmpSuperBowls.getString(key.get(12));
               String attendance = tmpSuperBowls.getString(key.get(13));

              SuperBowl superBowl = new SuperBowl( winner,  qb_winner,  coach_winner,  loser,  qb_loser,  coach_loser,  city, state,  stadium,  winning_pts,  losing_pts,  sb, attendance);
              superBowlArrayList.add(superBowl);
           }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static JSONObject getJSONObjectFromURL() throws IOException, JSONException {

        String urlString = "https://public.opendatasoft.com/api/records/1.0/search/?dataset=super-bowl&q=&rows=60&sort=date&facet=winner&facet=qb_winner&facet=coach_winner&facet=loser&facet=qb_loser&facet=coach_loser&facet=city&facet=state&facet=stadium&facet=referee&facet=winning_pts&facet=losing_pts&facet=sb&facet=attendance";
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds*/ );
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        return new JSONObject(jsonString);
    }
}


