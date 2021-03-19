package com.example.demo.client.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "NewLogSet")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewLogSet {
    @XmlElement(name = "LogEntry")
    private List<LogEntry> logs;

    public List<LogEntry> getLogEntryList() {
        return logs;
    }

    public void setLogEntryList(List<LogEntry> logs) {
        this.logs = logs;
    }
}
