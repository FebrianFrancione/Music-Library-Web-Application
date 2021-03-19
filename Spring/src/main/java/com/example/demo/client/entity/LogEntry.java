package com.example.demo.client.entity;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlRootElement(name = "LogEntry")
@XmlAccessorType(XmlAccessType.FIELD)
public class LogEntry {

    @XmlElement(name = "log_id")
    protected int logId;
    @XmlElement(name = "ISRC", required = true)
    protected String isrc;
    @XmlElement(name = "time_stamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeStamp;
    @XmlElement(name = "type_of_change", required = true)
    protected String typeOfChange;

    public LogEntry(){}

    public LogEntry(int logId, XMLGregorianCalendar timeStamp, String isrc, String typeOfChange) {
        this.logId = logId;
        this.timeStamp = timeStamp;
        this.isrc = isrc;
        this.typeOfChange = typeOfChange;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public XMLGregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(XMLGregorianCalendar timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getTypeOfChange() {
        return typeOfChange;
    }

    public void setTypeOfChange(String typeOfChange) {
        this.typeOfChange = typeOfChange;
    }
}
