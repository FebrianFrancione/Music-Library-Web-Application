package com.example.SOAP.service;

import com.example.demo.Entity.Logs;
import com.example.demo.persistence.LogsDao;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogsService implements ILogsService{
    private LogsDao logRepository = new LogsDao();
    private List<Logs> logs = new ArrayList<>();

    @Override
    public List<Logs> getAllLogs(){
        logs = logRepository.getLogEntries();
        return logs;
    }

    @Override
    public List<Logs> getAllLogsByChange(int change_type){
        logs = logRepository.filterLogsByChange(change_type);
        return logs;
    }

    @Override
    public List<Logs> getAllLogsByDates(String from, String to){
        logs = logRepository.filterLogsByDates(from, to);
        return logs;
    }

    @Override
    public List<Logs> getAllLogsByDatesAndByChange(String from, String to, int change_type){
        logs = logRepository.filterLogsByDatesAndChange(from, to, change_type);
        return logs;
    }
}
