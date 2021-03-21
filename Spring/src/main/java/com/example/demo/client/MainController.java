package com.example.demo.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.webservices.logs.client.wsdl.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.client.entity.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping(value = "/logs", method = {RequestMethod.GET, RequestMethod.POST})
public class MainController  implements WebMvcConfigurer {

    private final static String STR_URL_ = "http://localhost:8090/ws/logs?wsdl";
    private boolean fromDateSet = false;
    private boolean toDateSet = false;
    private boolean changeTypeSet = false;
    private boolean clearLogsSet = false;
    private String from = "";
    private String to = "";
    private String changeType = "";

    @GetMapping( "/")
    public String showHomePage() {
        return "Logs";
    }

    @GetMapping( "/getLogs")
    public String getLogsHtml(Model model) {
        LogsService port = logsServiceInit();

        if(port!=null){
            NewLogSet logs = filterLogs(port);
            model.addAttribute("logs",logs.getLogEntryList());

        }else{
            return "Error";
        }

        return "LogsFiltered";
    }

    @GetMapping( "/getLogsByDate")
    public String getLogsByDate(Model model, @RequestParam String from, @RequestParam String to) {
        if (from != null && !from.isEmpty()) {
            this.fromDateSet = true;
            this.from = from;
        }

        if(to != null && !to.isEmpty()){
            this.toDateSet = true;
            this.to = to;
        }

        LogsService port=logsServiceInit();

        if(port!=null){
            NewLogSet logs = filterLogs(port);
            model.addAttribute("logs",logs.getLogEntryList());

        }else{
            return "Error";
        }

        return "LogsFiltered";
    }

    @GetMapping("/getLogsByChange")
    public String getLogsByChangeType(Model model, @RequestParam String change) {
        if (change != null && !change.isEmpty()) {
            this.changeTypeSet = true;
            this.changeType = change;
        }

        LogsService port=logsServiceInit();

        if(port!=null){
            NewLogSet logs = filterLogs(port);
            model.addAttribute("logs",logs.getLogEntryList());

        }else{
            return "Error";
        }

        return "LogsFiltered";
    }

    @GetMapping( "/getLogsByDateAndChange")
    public String getLogsByDateAndChange(Model model, @RequestParam String from, @RequestParam String to, @RequestParam String change) {
        if (from != null && !from.isEmpty()) {
            this.fromDateSet = true;
            this.from = from;
        }

        if(to != null && !to.isEmpty()){
            this.toDateSet = true;
            this.to = to;
        }

        if(change != null && !change.isEmpty()){
            this.changeTypeSet = true;
            this.changeType = change;
        }

        LogsService port=logsServiceInit();

        if(port!=null){
            NewLogSet logs = filterLogs(port);
            model.addAttribute("logs",logs.getLogEntryList());

        }else{
            return "Error";
        }

        return "LogsFiltered";
    }

    @GetMapping( "/clearLogs")
    public String clearLogs(Model model) throws RepException_Exception {
        LogsService port = logsServiceInit();

        if(port!=null){
            try {
                port.clearLogs();
            } catch (RepException_Exception e) {
                System.out.println("Clear Logs not yet implemented exception " + e.getFaultInfo().getMessage());
                model.addAttribute("log_message", e.getFaultInfo().getMessage());
            }

        }else{
            return "LogsError";
        }

        return "LogsError";
    }

    private LogsService logsServiceInit(){
        LogsService port=null;
        try {
            URL url=new URL(STR_URL_);
            LogsServiceImplService logServiceImpl=new LogsServiceImplService(url);
            port=logServiceImpl.getLogsServicePort();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Error getting the wsdl port type for Logs");
        }

        return port;

    }

    private NewLogSet filterLogs(LogsService port) {
        List<LogEntry> logsList = new ArrayList<>();
        List<LogEntryEntity> xmlLogs;
        NewLogSet logs = new NewLogSet();

        if(fromDateSet && toDateSet && changeTypeSet)
            xmlLogs = port.getAllLogsByDatesAndByChange(from, to, changeType);

        else if(fromDateSet && toDateSet)
            xmlLogs = port.getAllLogsByDates(from, to);

        else if(fromDateSet)
            xmlLogs = port.getAllLogsByDates(from, "");

        else if(toDateSet)
            xmlLogs = port.getAllLogsByDates("", to);

        else if(changeTypeSet)
            xmlLogs = port.getAllLogsByChange(changeType);

        else
            xmlLogs = port.getAllLogs();

        for (LogEntryEntity log : xmlLogs){
            LogEntry logEntry = new LogEntry(log.getLogId(), log.getTimeStamp(), log.getIsrc(), log.getTypeOfChange());
            logsList.add(logEntry);
        }

        logs.setLogEntryList(logsList);
        resetFlags();
        return logs;
    }

    private void resetFlags(){
        this.toDateSet = false;
        this.fromDateSet = false;
        this.changeTypeSet = false;
        this.clearLogsSet = false;
    }
}
