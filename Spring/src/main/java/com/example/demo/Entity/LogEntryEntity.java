package com.example.demo.Entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LogEntryEntity {

    @XmlSchemaType(name = "int")
    private int logId;
    @XmlSchemaType(name = "dateTime")
    private XMLGregorianCalendar timeStamp;
    @XmlSchemaType(name = "string")
    private String isrc;
    @XmlSchemaType(name = "string")
    private String typeOfChange;

    public LogEntryEntity(){}

    public LogEntryEntity(int logId, XMLGregorianCalendar timeStamp, String isrc, String typeOfChange) {
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