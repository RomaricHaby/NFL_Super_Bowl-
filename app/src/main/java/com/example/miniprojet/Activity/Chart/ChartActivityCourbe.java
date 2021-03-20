package com.example.miniprojet.Activity.Chart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.miniprojet.Model.SuperBowl;
import com.example.miniprojet.R;

import java.util.ArrayList;
import java.util.List;

public class ChartActivityCourbe extends AppCompatActivity {
    private ArrayList<SuperBowl> superBowlArrayList;

    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        superBowlArrayList = (ArrayList<SuperBowl>) getIntent().getSerializableExtra("superBowl");

        toggleButton = findViewById(R.id.toggleButtonGraphic);

       courbe();
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChartActivityCourbe.this, ChartActivityBatton.class);
                intent.putExtra("superBowl",superBowlArrayList);
                startActivity(intent);
            }
        });

    }

    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value) {
            super(x, value);
        }
    }

    public void courbe(){
        AnyChartView  anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("Nombre de spectateurs Super Bowls");

        cartesian.xAxis(0).title("Super Bowl");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        cartesian.yScale().minimum(61781d);
        cartesian.yScale().maximum(105000d);


        List<DataEntry> seriesData = new ArrayList<>();

        for (int i = 0; i < superBowlArrayList.size(); i++){
            seriesData.add(new CustomDataEntry(superBowlArrayList.get(i).getSb(), Integer.valueOf(superBowlArrayList.get(i).getAttendance())));
        }

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("Spectateurs");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);


        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anyChartView.setChart(cartesian);
    }
}