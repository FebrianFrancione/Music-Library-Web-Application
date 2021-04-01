package com.example.demo.client;

import io.quickchart.QuickChart;

public class QuickChartClient {
    public static void main(String[] args) {
        QuickChart chart = new QuickChart();
        chart.setWidth(500);
        chart.setHeight(300);
        chart.setConfig("{"
                + "    type: 'bar',"
                + "    data: {"
                + "        labels: ['label1', 'Ivan2', 'Label3', 'Ivan4'],"
                + "        datasets: [{"
                + "            label: 'Ivans',"
                + "            data: [9, 88, 31, 475]"
                + "        }]"
                + "    }"
                + "}"
        );

        System.out.println(chart.getShortUrl());

        // Output: https://quickchart.io/chart?w=500&h=300&devicePixelRatio=1.0&bkg=transparent&c=%7B++++type%3A+%27bar%27%2C++++data%3A+%7B++++++++labels%3A+%5B%27Q1%27%2C+%27Q2%27%2C+%27Q3%27%2C+%27Q4%27%5D%2C++++++++datasets%3A+%5B%7B++++++++++++label%3A+%27Users%27%2C++++++++++++data%3A+%5B50%2C+60%2C+70%2C+180%5D++++++++%7D%5D++++%7D%7D
    }
}
