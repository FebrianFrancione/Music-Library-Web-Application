package core;

import persistence.helpers.LogEntryType;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogEntry {
    private Calendar time_stamp;
    private String timeStampString;
    private String ISRC;
    private LogEntryType type_of_change;

    public Calendar getTimeStamp() {
        return time_stamp;
    }

    public void setTimeStamp(Calendar time_stamp) {
        this.time_stamp = time_stamp;
    }

    public LogEntry(Calendar time_stamp, String ISRC, LogEntryType type_of_change) {
        this.ISRC = ISRC;
        this.type_of_change = type_of_change;
        this.time_stamp = time_stamp;
        timeStampString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.time_stamp.getTime());
    }

    public String getTimeStampString() {
        return timeStampString;
    }

    public void setTimeStampString(String timeStampString) {
        this.timeStampString = timeStampString;
    }

    public String getISRC() {
        return ISRC;
    }

    public void setISRC(String ISRC) {
        this.ISRC = ISRC;
    }

    public LogEntryType getType_of_change() {
        return type_of_change;
    }

    public void setType_of_change(LogEntryType type_of_change) {
        this.type_of_change = type_of_change;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "ISRC: '" + ISRC + '\'' +
                ", Type of Change: '" + type_of_change + '\'' +
                ", Time Stamp: '" + timeStampString + '\'' +
                '}';
    }
}
