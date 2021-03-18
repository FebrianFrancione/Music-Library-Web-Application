package com.example.SOAP.service;

import com.example.demo.Entity.Logs;

import java.util.List;

public interface ILogsService {
    List<Logs> getAllLogs();
    List<Logs> getAllLogsByChange(int change_type);
    List<Logs> getAllLogsByDates(String from, String to);
    List<Logs> getAllLogsByDatesAndByChange(String from, String to, int change_type);
}
