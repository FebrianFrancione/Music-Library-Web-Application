package com.example.SOAP.service;

import com.example.SOAP.entity.LogEntryEntity;
import com.example.SOAP.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogsService implements ILogsService{
    @Autowired
    private LogRepository logRepository;

    public List<LogEntryEntity> getAllLogs(){
        List<LogEntryEntity> list = new ArrayList<>();
        logRepository.findAll().forEach(e -> list.add(e));
        List<LogEntryEntity> sortedLogs = list.stream()
                .sorted((a,b) -> a.getTimeStampCol().compareTo(b.getTimeStampCol())).collect(Collectors.toList());
        return sortedLogs;
    }

    public List<LogEntryEntity> getAllLogsByChangeType(int change_type){
        List<LogEntryEntity> list = new ArrayList<>();
        logRepository.findByTypeOfChange(change_type).forEach(e -> list.add(e));
        List<LogEntryEntity> sortedLogs = list.stream()
                .sorted((a,b) -> a.getTimeStampCol().compareTo(b.getTimeStampCol())).collect(Collectors.toList());
        return sortedLogs;
    }

    public List<LogEntryEntity> getAllLogsByDateRange(String from, String to){
        List<LogEntryEntity> list = new ArrayList<>();
        List<LogEntryEntity> sortedLogs = new ArrayList<>();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fromDate = dateFormat.parse(from);
            Date toDate = dateFormat.parse(to);
            Timestamp fromTimestamp = new Timestamp(fromDate.getTime());
            Timestamp toTimestamp = new Timestamp(toDate.getTime());
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(toTimestamp.getTime());
            cal.add(Calendar.HOUR, 24);
            toTimestamp = new Timestamp(cal.getTime().getTime());
            //logRepository.findAllBytimeStampColBetween(fromTimestamp, toTimestamp).forEach(e -> list.add(e));
            logRepository.findBytimeStampCol(fromTimestamp, toTimestamp).forEach(e -> list.add(e));
            sortedLogs = list.stream()
                    .sorted((a,b) -> a.getTimeStampCol().compareTo(b.getTimeStampCol())).collect(Collectors.toList());
            return sortedLogs;
        } catch(Exception e) { //this generic but you can control another types of exception
            System.out.println("Exception with dates");
        }
        return sortedLogs;
    }

    public List<LogEntryEntity> getAllLogsByDateRangeAndChange(String from, String to, int change_type){
        List<LogEntryEntity> list = new ArrayList<>();
        List<LogEntryEntity> sortedLogs = new ArrayList<>();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fromDate = dateFormat.parse(from);
            Date toDate = dateFormat.parse(to);
            Timestamp fromTimestamp = new Timestamp(fromDate.getTime());
            Timestamp toTimestamp = new Timestamp(toDate.getTime());
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(toTimestamp.getTime());
            cal.add(Calendar.HOUR, 24);
            toTimestamp = new Timestamp(cal.getTime().getTime());
            logRepository.findBytimeStampColAndTypeOfChange(fromTimestamp, toTimestamp, change_type).forEach(e -> list.add(e));
            sortedLogs = list.stream()
                    .sorted((a,b) -> a.getTimeStampCol().compareTo(b.getTimeStampCol())).collect(Collectors.toList());
            return sortedLogs;
        } catch(Exception e) { //this generic but you can control another types of exception
            System.out.println("Exception with dates");
        }
        return sortedLogs;
    }
/*
    private void parseDates(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = dateFormat.parse(from);
        Date toDate = dateFormat.parse(to);
        Timestamp fromTimestamp = new java.sql.Timestamp(fromDate.getTime());
        Timestamp toTimestamp = new java.sql.Timestamp(toDate.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(toTimestamp.getTime());
        cal.add(Calendar.HOUR, 24);
        toTimestamp = new Timestamp(cal.getTime().getTime());
    }

 */
}
