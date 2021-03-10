package com.example.miniprojet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.miniprojet.Model.TeamHelmet;
import com.example.miniprojet.R;

import java.util.List;

public class HelmetTeamAdapter extends ArrayAdapter<TeamHelmet> {

    public HelmetTeamAdapter(Context context, int resource, List<TeamHelmet> TeamHelmet)
    {
        super(context,resource, TeamHelmet);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TeamHelmet helmet = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.team_helmet_cell, parent, false);
        }

        TextView teamName = (TextView) convertView.findViewById(R.id.nameTeam_cell);
        ImageView helmetImg = (ImageView) convertView.findViewById(R.id.helmet_cel);


        teamName.setText(helmet.getName());
        helmetImg.setImageResource(helmet.getHelmet());

        return convertView;
    }
}
