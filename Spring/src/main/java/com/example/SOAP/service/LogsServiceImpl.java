package com.example.SOAP.service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.Entity.LogEntryEntity;
import com.example.demo.persistence.helpers.LogEntryType;
import com.example.demo.persistence.LogsDao;

@WebService(name="LogsService", endpointInterface = "com.example.SOAP.service.LogsService")
public class LogsServiceImpl implements LogsService {
    private LogsDao logRepository = new LogsDao();
    private List<LogEntryEntity> logs = new ArrayList<>();


    @Override
    public List<LogEntryEntity> getAllLogs(){
        logs = logRepository.getLogEntries();
        return logs;
    }

    @Override
    public List<LogEntryEntity> getAllLogsByChange(String change){
        LogEntryType change_type = LogEntryType.valueOf(change.toUpperCase());
        logs = logRepository.filterLogsByChange(change_type.getValue());
        return logs;
    }

    @Override
    public List<LogEntryEntity> getAllLogsByDates(String from, String to){
        logs = logRepository.filterLogsByDates(from, to);
        return logs;
    }

    @Override
    public List<LogEntryEntity> getAllLogsByDatesAndByChange(String from, String to, String change){
        LogEntryType change_type = LogEntryType.valueOf(change.toUpperCase());
        logs = logRepository.filterLogsByDatesAndChange(from, to, change_type.getValue());
        return logs;
    }
}
