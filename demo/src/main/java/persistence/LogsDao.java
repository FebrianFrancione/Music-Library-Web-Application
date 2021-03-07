package persistence;

import core.LogEntry;
import persistence.helpers.LogEntryType;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class LogsDao {

    private PreparedStatement statement = null;
    private ArrayList<LogEntry> logs = new ArrayList<>();
    private JDBConfig jdbc = new JDBConfig();

    public void addLogEntry(String ISRC, int type_of_change) {
        /*ZonedDateTime date = ZonedDateTime.now();
        String time_stamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'UTC'").format(date);*/
        java.util.Date utilDate = new java.util.Date();
        Timestamp time_stamp = new Timestamp(utilDate.getTime());
        String sql = "insert into logs (time_stamp, type_of_change, ISRC) values (?,?,?)";
        statement = jdbc.prepareStatement(sql);
        try {
            //statement.setString(1, time_stamp);
            statement.setTimestamp(1, time_stamp);
            statement.setInt(2, type_of_change);
            statement.setString(3, ISRC);
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.close();
        }
    }

    public ArrayList<LogEntry> getLogEntries(){

        String sql = "select * from logs ORDER BY time_stamp";

        try {
            statement = jdbc.prepareStatement(sql);
            executeSelectQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }

        return logs;
    }

    public ArrayList<LogEntry> filterLogsByDates(String from_date, String to_date){
        String sql = setQueryParams(from_date, to_date);

        try {
            statement = jdbc.prepareStatement(sql);

            if(!from_date.isEmpty() && !to_date.isEmpty()){
                statement.setString(1, from_date);
                statement.setString(2, to_date);
            }

            else if(!from_date.isEmpty())
                statement.setString(1, from_date);

            else if(!to_date.isEmpty())
                statement.setString(1, to_date);

            executeSelectQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }

        return logs;
    }

    public ArrayList<LogEntry> filterLogsByChange(int type_of_change){

        if(type_of_change < LogEntryType.CREATE.getValue() || type_of_change > LogEntryType.DELETE.getValue())
            return null;

        String sql = sql = "select * from logs WHERE type_of_change = ? ORDER BY time_stamp";

        try {
            statement = jdbc.prepareStatement(sql);
            statement.setInt(1, type_of_change);
            executeSelectQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }

        return logs;
    }

    public String setQueryParams(String from_date, String to_date){
        String sql = "";

        if(!from_date.isEmpty() && !to_date.isEmpty()){
            sql = "select * from logs WHERE DATE(time_stamp) BETWEEN ? AND ? ORDER BY time_stamp";
        }

        else if(!from_date.isEmpty())
            sql = "select * from logs WHERE DATE(time_stamp) >= ? ORDER BY time_stamp";

        else if(!to_date.isEmpty())
            sql = "select * from logs WHERE DATE(time_stamp) <= ? ORDER BY time_stamp";

        return sql;
    }


    public void executeSelectQuery(String sql) {
        logs.clear();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String ISRC = resultSet.getString("ISRC");
                int type_of_change = resultSet.getInt("type_of_change");
                Timestamp time_stamp = resultSet.getTimestamp("time_stamp");
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                calendar.setTimeInMillis(time_stamp.getTime());
                LogEntry newLogEntry = new LogEntry(calendar, ISRC, LogEntryType.valueOf(type_of_change));
                logs.add(newLogEntry);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
