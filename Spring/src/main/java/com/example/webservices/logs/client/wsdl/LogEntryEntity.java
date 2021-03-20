
package com.example.webservices.logs.client.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for logEntryEntity complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="logEntryEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="logId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="timeStamp" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/>
 *         &lt;element name="isrc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typeOfChange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "logEntryEntity", propOrder = {
        "logId",
        "timeStamp",
        "isrc",
        "typeOfChange"
})
public class LogEntryEntity {

    protected int logId;
    protected XMLGregorianCalendar timeStamp;
    protected String isrc;
    protected String typeOfChange;

    /**
     * Gets the value of the logId property.
     *
     */
    public int getLogId() {
        return logId;
    }

    /**
     * Sets the value of the logId property.
     *
     */
    public void setLogId(int value) {
        this.logId = value;
    }

    /**
     * Gets the value of the timeStamp property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the value of the timeStamp property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setTimeStamp(XMLGregorianCalendar value) {
        this.timeStamp = value;
    }

    /**
     * Gets the value of the isrc property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIsrc() {
        return isrc;
    }

    /**
     * Sets the value of the isrc property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIsrc(String value) {
        this.isrc = value;
    }

    /**
     * Gets the value of the typeOfChange property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTypeOfChange() {
        return typeOfChange;
    }

    /**
     * Sets the value of the typeOfChange property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTypeOfChange(String value) {
        this.typeOfChange = value;
    }

}
