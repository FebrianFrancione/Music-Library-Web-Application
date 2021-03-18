package com.example.demo.persistence;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import com.example.demo.persistence.helpers.LogEntryType;
import com.example.demo.Entity.Logs;

public class LogsDao {
    private PreparedStatement statement = null;
    private ArrayList<Logs> logs = new ArrayList<>();
    private JDBConfig jdbc = new JDBConfig();

    public void addLogEntry(String ISRC, int type_of_change) {
        java.util.Date utilDate = new java.util.Date();
        Timestamp time_stamp = new Timestamp(utilDate.getTime());
        String sql = "insert into logs (time_stamp, type_of_change, ISRC) values (?,?,?)";
        statement = jdbc.prepareStatement(sql);
        try {
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

    public ArrayList<Logs> getLogEntries(){

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

    public ArrayList<Logs> filterLogsByDates(String from_date, String to_date){
        String sql = setDateQueryParams(from_date, to_date);

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

    public ArrayList<Logs> filterLogsByChange(int type_of_change){

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

    public ArrayList<Logs> filterLogsByDatesAndChange(String from_date, String to_date, int type_of_change){
        String sql = "select * from logs WHERE DATE(time_stamp) BETWEEN ? AND ? AND type_of_change = ? ORDER BY time_stamp";

        try {
            statement = jdbc.prepareStatement(sql);

            if(!from_date.isEmpty() && !to_date.isEmpty()){
                statement.setString(1, from_date);
                statement.setString(2, to_date);
                statement.setInt(3, type_of_change);
            }

            executeSelectQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }

        return logs;
    }

    public String setDateQueryParams(String from_date, String to_date){
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
                int log_id = resultSet.getInt("log_id");
                String ISRC = resultSet.getString("ISRC");
                int type_of_change_col = resultSet.getInt("type_of_change");
                Timestamp time_stamp = resultSet.getTimestamp("time_stamp");
                XMLGregorianCalendar timeStamp = setTimeStamp(time_stamp);
                String type_of_change = String.valueOf(LogEntryType.valueOf(type_of_change_col));
                Logs newLogEntry = new Logs(log_id, timeStamp, ISRC, type_of_change);
                logs.add(newLogEntry);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public XMLGregorianCalendar setTimeStamp(Timestamp timeStampCol) throws DatatypeConfigurationException {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(new Date(timeStampCol.getTime()));
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(
                gc.get(GregorianCalendar.YEAR),
                gc.get(GregorianCalendar.MONTH) + 1,
                gc.get(GregorianCalendar.DAY_OF_MONTH),
                gc.get(GregorianCalendar.HOUR_OF_DAY),
                gc.get(GregorianCalendar.MINUTE),
                gc.get(GregorianCalendar.SECOND), DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);
    }
}
