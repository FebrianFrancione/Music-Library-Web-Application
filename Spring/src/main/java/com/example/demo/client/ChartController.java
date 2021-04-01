package com.example.demo.client;

import com.example.demo.Entity.Album;
import com.example.demo.Service.AlbumService;
import com.example.demo.Service.ChartService;
import com.example.demo.Service.ImageUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.demo.persistence.helpers.LogEntryType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/chart", produces = {MediaType.APPLICATION_JSON_VALUE}, method = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class ChartController implements WebMvcConfigurer {

    private ChartService chartService;

    @Autowired
    public ChartController(ChartService chartService) {
        this.chartService = chartService;
    }

    @GetMapping( "/")
    public String showHomePage() {
        return "ChartHome";
    }

    @GetMapping("/Get")
    public String getChart(Model model) {
        String chartUrl = chartService.getChart();
        model.addAttribute("get", true);
        model.addAttribute("chart", chartUrl);
        return "Chart";
    }

    @GetMapping("/Post")
    public String showPost(Model model){
        return "PostChart";
    }


    @PostMapping( "/create")
    public String createNewAlbum(Model model, @RequestParam int width, @RequestParam int height, @RequestParam String chartType) throws IOException {

        String chartUrl = chartService.createChart(width, height, chartType);
        model.addAttribute("posted", true);
        model.addAttribute("chartUrl", chartUrl);
        return "PostChart";

    }
}
