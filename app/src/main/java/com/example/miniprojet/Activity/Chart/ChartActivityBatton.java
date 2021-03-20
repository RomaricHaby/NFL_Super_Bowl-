package com.example.miniprojet.Activity.Chart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.miniprojet.Model.SuperBowl;
import com.example.miniprojet.R;

import java.util.ArrayList;
import java.util.List;

public class ChartActivityBatton extends AppCompatActivity {
    private ArrayList<SuperBowl> superBowlArrayList;

    private ToggleButton toggleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        superBowlArrayList = (ArrayList<SuperBowl>) getIntent().getSerializableExtra("superBowl");

        toggleButton = findViewById(R.id.toggleButtonGraphic);
        toggleButton.setChecked(false);



        baton();
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void baton(){
        AnyChartView  anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();

        for (int i = 0; i < superBowlArrayList.size(); i++){
            data.add(new ValueDataEntry(superBowlArrayList.get(i).getSb(), Integer.valueOf(superBowlArrayList.get(i).getAttendance())));
        }

        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");


        cartesian.animation(true);
        cartesian.title("Nombre de spectateurs Super Bowls");


        cartesian.yScale().minimum(55000d);
        cartesian.yScale().maximum(105000d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Super Bowl");
        cartesian.yAxis(0).title("Spectateurs");

        anyChartView.setChart(cartesian);

    }
}
