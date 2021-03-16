package com.example.SOAP.service;

import com.example.SOAP.entity.LogEntryEntity;

import java.util.List;

public interface ILogsService {
    List<LogEntryEntity> getAllLogs();
    List<LogEntryEntity> getAllLogsByChangeType(int change_type);
    List<LogEntryEntity> getAllLogsByDateRange(String from, String to);
    List<LogEntryEntity> getAllLogsByDateRangeAndChange(String from, String to, int change_type);
}
