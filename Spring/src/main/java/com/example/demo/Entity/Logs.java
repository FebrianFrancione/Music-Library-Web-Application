package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Calendar;

@Entity
@Table(name = "logs")
public class Logs {

    @Id
    @Column(name = "log_id")
    private int logId;

    @Column(name = "time_stamp")
    private Calendar timeStamp;

    @Column(name = "type_of_change")
    private int typeOfChange;

    @Column(name = "ISRC")
    private String ISRC;

    public Logs() {
    }

    public Logs(int logId, Calendar timeStamp, int type_of_change, String ISRC) {
        this.logId = logId;
        this.timeStamp = timeStamp;
        this.typeOfChange = type_of_change;
        this.ISRC = ISRC;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public Calendar getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Calendar timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getTypeOfChange() {
        return typeOfChange;
    }

    public void setTypeOfChange(int typeOfChange) {
        this.typeOfChange = typeOfChange;
    }

    public String getISRC() {
        return ISRC;
    }

    public void setISRC(String ISRC) {
        this.ISRC = ISRC;
    }
}
