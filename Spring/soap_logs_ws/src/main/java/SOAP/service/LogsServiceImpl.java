package SOAP.service;

import SOAP.entity.LogEntryEntity;
import SOAP.persistence.LogsDao;
import SOAP.persistence.helpers.LogEntryType;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;


/**
 * This class holds the implementation of the methods of our SOAP web service
 */
@WebService(name="LogsService", endpointInterface = "SOAP.service.LogsService")
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

    @Override
    public void clearLogs() throws RepException{
        throw new RepException("The method is not yet supported.");
    }
}
