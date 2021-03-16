package com.example.SOAP.endpoints;

import com.example.SOAP.entity.LogEntryEntity;
import com.example.SOAP.service.ILogsService;
import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import persistence.helpers.LogEntryType;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;
import java.util.List;

@Endpoint
public class LogsEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @Autowired
    private ILogsService logsService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllLogsRequest")
    @ResponsePayload
    public GetAllLogsResponse getAllLogs() throws DatatypeConfigurationException {
        GetAllLogsResponse response = new GetAllLogsResponse();
        List<LogEntry> LogEntryList = new ArrayList<>();
        List<LogEntryEntity> logs = logsService.getAllLogs();
        getLogs(logs, LogEntryList);
        response.getLogEntry().addAll(LogEntryList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllLogsByDatesRequest")
    @ResponsePayload
    public GetAllLogsByDatesResponse getAllLogsByDates(@RequestPayload GetAllLogsByDatesRequest request) throws DatatypeConfigurationException {
        String from = request.getFrom();
        String to = request.getTo();
        GetAllLogsByDatesResponse response = new GetAllLogsByDatesResponse();
        List<LogEntry> LogEntryList = new ArrayList<>();
        List<LogEntryEntity> logs = logsService.getAllLogsByDateRange(from, to);
        getLogs(logs, LogEntryList);
        response.getLogEntry().addAll(LogEntryList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllLogsByChangeRequest")
    @ResponsePayload
    public GetAllLogsByChangeResponse getAllLogsByChange(@RequestPayload GetAllLogsByChangeRequest request) throws DatatypeConfigurationException {
        String change = request.getChangeType();
        GetAllLogsByChangeResponse response = new GetAllLogsByChangeResponse();
        List<LogEntry> LogEntryList = new ArrayList<>();
        List<LogEntryEntity> logs = logsService.getAllLogsByChangeType(LogEntryType.getValue(change.toUpperCase()));
        getLogs(logs, LogEntryList);
        response.getLogEntry().addAll(LogEntryList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllLogsByByDateAndChangeRequest")
    @ResponsePayload
    public GetAllLogsByByDateAndChangeResponse getAllLogsByDatesAndByChange(@RequestPayload GetAllLogsByByDateAndChangeRequest request) throws DatatypeConfigurationException {
        String from = request.getFrom();
        String to = request.getTo();
        String change = request.getChangeType();
        GetAllLogsByByDateAndChangeResponse response = new GetAllLogsByByDateAndChangeResponse();
        List<LogEntry> LogEntryList = new ArrayList<>();
        List<LogEntryEntity> logs = logsService.getAllLogsByDateRangeAndChange(from, to, LogEntryType.getValue(change.toUpperCase()));
        getLogs(logs, LogEntryList);
        response.getLogEntry().addAll(LogEntryList);
        return response;
    }

    //Private helper to loop through logs and map manually each log entity with the XML log schema
    private void getLogs(List<LogEntryEntity> logs, List<LogEntry> LogEntryList) throws DatatypeConfigurationException {
        for (int i = 0; i < logs.size(); i++) {
            LogEntry logSchema = new LogEntry();
            LogEntryEntity logEntity = logs.get(i);
            logEntity.setTimeStamp(logEntity.getTimeStampCol());
            String type_of_change = String.valueOf(LogEntryType.valueOf(logEntity.getTypeOfChangeCol()));
            logEntity.setTypeOfChange(type_of_change);
            logSchema.setLogId(logEntity.getLogId());
            logSchema.setISRC(logEntity.getIsrc());
            logSchema.setTimeStamp(logEntity.getTimeStamp());
            logSchema.setTypeOfChange(logEntity.getTypeOfChange());
            LogEntryList.add(logSchema);
        }
    }
}
