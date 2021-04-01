package com.example.demo.Service;

import org.springframework.web.bind.annotation.RequestParam;

public interface ChartService {
    public String getChart();
    public String createChart(int width, int height, String type);
}
