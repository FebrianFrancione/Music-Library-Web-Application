package com.example.demo.Service;

import org.springframework.stereotype.Service;
import io.quickchart.QuickChart;

@Service
public class ChartServiceImpl implements ChartService{

    @Override
    public String getChart(){

        //For now return a static url
        return "https://quickchart.io/chart/render/zf-47776625-e5bd-44c0-a2af-9a9ab91efdc9";
    }

    @Override
    public String createChart(int width, int height, String type){

        //For now I create a random chart
        QuickChart chart = new QuickChart();
        chart.setWidth(width);
        chart.setHeight(height);
        chart.setConfig("{"
                + "    type: '"+ type +"',"
                + "    data: {"
                + "        labels: ['label1', 'label2', 'Label3', 'label4'],"
                + "        datasets: [{"
                + "            label: 'Title',"
                + "            data: [9, 88, 31, 475]"
                + "        }]"
                + "    }"
                + "}"
        );

        return chart.getShortUrl();
    }
}
