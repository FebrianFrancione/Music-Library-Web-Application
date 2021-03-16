package com.example.SOAP.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

@Entity
@Table(name = "logs")
public class LogEntryEntity {

    @Id
    @Column(name = "log_id")
    private int logId;

    @Column(name = "time_stamp")
    protected Timestamp timeStampCol;

    @XmlSchemaType(name = "dateTime")
    @Transient
    protected XMLGregorianCalendar timeStamp;

    @Column(name = "ISRC")
    private String isrc;

    @Column(name = "type_of_change")
    private int typeOfChangeCol;

    @XmlSchemaType(name = "string")
    @Transient
    private String typeOfChange;

    public XMLGregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStampCol) throws DatatypeConfigurationException {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(new Date(timeStampCol.getTime()));
        this.timeStamp = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                gc.get(GregorianCalendar.YEAR),
                gc.get(GregorianCalendar.MONTH) + 1,
                gc.get(GregorianCalendar.DAY_OF_MONTH),
                gc.get(GregorianCalendar.HOUR_OF_DAY),
                gc.get(GregorianCalendar.MINUTE),
                gc.get(GregorianCalendar.SECOND), DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);
    }

    public void setTypeOfChange(String typeOfChange) {
        this.typeOfChange = typeOfChange;
    }

    public String getTypeOfChange(){
        return typeOfChange;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public Timestamp getTimeStampCol() {
        return timeStampCol;
    }

    public void setTimeStampCol(Timestamp timeStampCol) {
        this.timeStampCol = timeStampCol;
    }


    public int getTypeOfChangeCol() {
        return typeOfChangeCol;
    }

    public void setTypeOfChangeCol(int typeOfChangeCol) {
        this.typeOfChangeCol = typeOfChangeCol;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }
}
