package com.example.SOAP.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;
import com.example.demo.Entity.LogEntryEntity;

@WebService
@SOAPBinding
public interface LogsService {
    @WebMethod(operationName = "getAllLogs")
    List<LogEntryEntity> getAllLogs();

    @WebMethod(operationName = "getAllLogsByChange")
    List<LogEntryEntity> getAllLogsByChange(@WebParam(name="change_type") String change);

    @WebMethod(operationName = "getAllLogsByDates")
    List<LogEntryEntity> getAllLogsByDates(@WebParam(name="from") String from, @WebParam(name="to") String to);

    @WebMethod(operationName = "getAllLogsByDatesAndByChange")
    List<LogEntryEntity> getAllLogsByDatesAndByChange(@WebParam(name="from") String from, @WebParam(name="to") String to,
                                                      @WebParam(name="change_type") String change);
}