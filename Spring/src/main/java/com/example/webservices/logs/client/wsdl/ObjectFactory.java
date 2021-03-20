
package com.example.webservices.logs.client.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.webservices.logs.client.wsdl package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _LogEntryEntity_QNAME = new QName("http://service.SOAP/", "logEntryEntity");
    private final static QName _GetAllLogsByChange_QNAME = new QName("http://service.SOAP/", "getAllLogsByChange");
    private final static QName _GetAllLogsByDatesAndByChange_QNAME = new QName("http://service.SOAP/", "getAllLogsByDatesAndByChange");
    private final static QName _GetAllLogsByDatesResponse_QNAME = new QName("http://service.SOAP/", "getAllLogsByDatesResponse");
    private final static QName _GetAllLogsByDatesAndByChangeResponse_QNAME = new QName("http://service.SOAP/", "getAllLogsByDatesAndByChangeResponse");
    private final static QName _GetAllLogsByDates_QNAME = new QName("http://service.SOAP/", "getAllLogsByDates");
    private final static QName _GetAllLogs_QNAME = new QName("http://service.SOAP/", "getAllLogs");
    private final static QName _GetAllLogsByChangeResponse_QNAME = new QName("http://service.SOAP/", "getAllLogsByChangeResponse");
    private final static QName _GetAllLogsResponse_QNAME = new QName("http://service.SOAP/", "getAllLogsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.webservices.logs.client.wsdl
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllLogs }
     *
     */
    public GetAllLogs createGetAllLogs() {
        return new GetAllLogs();
    }

    /**
     * Create an instance of {@link GetAllLogsByChangeResponse }
     *
     */
    public GetAllLogsByChangeResponse createGetAllLogsByChangeResponse() {
        return new GetAllLogsByChangeResponse();
    }

    /**
     * Create an instance of {@link GetAllLogsResponse }
     *
     */
    public GetAllLogsResponse createGetAllLogsResponse() {
        return new GetAllLogsResponse();
    }

    /**
     * Create an instance of {@link GetAllLogsByChange }
     *
     */
    public GetAllLogsByChange createGetAllLogsByChange() {
        return new GetAllLogsByChange();
    }

    /**
     * Create an instance of {@link GetAllLogsByDatesAndByChange }
     *
     */
    public GetAllLogsByDatesAndByChange createGetAllLogsByDatesAndByChange() {
        return new GetAllLogsByDatesAndByChange();
    }

    /**
     * Create an instance of {@link LogEntryEntity }
     *
     */
    public LogEntryEntity createLogEntryEntity() {
        return new LogEntryEntity();
    }

    /**
     * Create an instance of {@link GetAllLogsByDates }
     *
     */
    public GetAllLogsByDates createGetAllLogsByDates() {
        return new GetAllLogsByDates();
    }

    /**
     * Create an instance of {@link GetAllLogsByDatesAndByChangeResponse }
     *
     */
    public GetAllLogsByDatesAndByChangeResponse createGetAllLogsByDatesAndByChangeResponse() {
        return new GetAllLogsByDatesAndByChangeResponse();
    }

    /**
     * Create an instance of {@link GetAllLogsByDatesResponse }
     *
     */
    public GetAllLogsByDatesResponse createGetAllLogsByDatesResponse() {
        return new GetAllLogsByDatesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogEntryEntity }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://service.SOAP/", name = "logEntryEntity")
    public JAXBElement<LogEntryEntity> createLogEntryEntity(LogEntryEntity value) {
        return new JAXBElement<LogEntryEntity>(_LogEntryEntity_QNAME, LogEntryEntity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLogsByChange }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://service.SOAP/", name = "getAllLogsByChange")
    public JAXBElement<GetAllLogsByChange> createGetAllLogsByChange(GetAllLogsByChange value) {
        return new JAXBElement<GetAllLogsByChange>(_GetAllLogsByChange_QNAME, GetAllLogsByChange.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLogsByDatesAndByChange }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://service.SOAP/", name = "getAllLogsByDatesAndByChange")
    public JAXBElement<GetAllLogsByDatesAndByChange> createGetAllLogsByDatesAndByChange(GetAllLogsByDatesAndByChange value) {
        return new JAXBElement<GetAllLogsByDatesAndByChange>(_GetAllLogsByDatesAndByChange_QNAME, GetAllLogsByDatesAndByChange.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLogsByDatesResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://service.SOAP/", name = "getAllLogsByDatesResponse")
    public JAXBElement<GetAllLogsByDatesResponse> createGetAllLogsByDatesResponse(GetAllLogsByDatesResponse value) {
        return new JAXBElement<GetAllLogsByDatesResponse>(_GetAllLogsByDatesResponse_QNAME, GetAllLogsByDatesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLogsByDatesAndByChangeResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://service.SOAP/", name = "getAllLogsByDatesAndByChangeResponse")
    public JAXBElement<GetAllLogsByDatesAndByChangeResponse> createGetAllLogsByDatesAndByChangeResponse(GetAllLogsByDatesAndByChangeResponse value) {
        return new JAXBElement<GetAllLogsByDatesAndByChangeResponse>(_GetAllLogsByDatesAndByChangeResponse_QNAME, GetAllLogsByDatesAndByChangeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLogsByDates }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://service.SOAP/", name = "getAllLogsByDates")
    public JAXBElement<GetAllLogsByDates> createGetAllLogsByDates(GetAllLogsByDates value) {
        return new JAXBElement<GetAllLogsByDates>(_GetAllLogsByDates_QNAME, GetAllLogsByDates.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLogs }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://service.SOAP/", name = "getAllLogs")
    public JAXBElement<GetAllLogs> createGetAllLogs(GetAllLogs value) {
        return new JAXBElement<GetAllLogs>(_GetAllLogs_QNAME, GetAllLogs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLogsByChangeResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://service.SOAP/", name = "getAllLogsByChangeResponse")
    public JAXBElement<GetAllLogsByChangeResponse> createGetAllLogsByChangeResponse(GetAllLogsByChangeResponse value) {
        return new JAXBElement<GetAllLogsByChangeResponse>(_GetAllLogsByChangeResponse_QNAME, GetAllLogsByChangeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLogsResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://service.SOAP/", name = "getAllLogsResponse")
    public JAXBElement<GetAllLogsResponse> createGetAllLogsResponse(GetAllLogsResponse value) {
        return new JAXBElement<GetAllLogsResponse>(_GetAllLogsResponse_QNAME, GetAllLogsResponse.class, null, value);
    }

}
