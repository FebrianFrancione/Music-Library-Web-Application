
package com.example.webservices.logs.client.wsdl;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "LogsService", targetNamespace = "http://service.soap.example.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface LogsService {


    /**
     * 
     * @param changeType
     * @return
     *     returns java.util.List<webservices.logs.client.wsdl.LogEntryEntity>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllLogsByChange", targetNamespace = "http://service.soap.example.com/", className = "webservices.logs.client.wsdl.GetAllLogsByChange")
    @ResponseWrapper(localName = "getAllLogsByChangeResponse", targetNamespace = "http://service.soap.example.com/", className = "webservices.logs.client.wsdl.GetAllLogsByChangeResponse")
    @Action(input = "http://service.soap.example.com/LogsService/getAllLogsByChangeRequest", output = "http://service.soap.example.com/LogsService/getAllLogsByChangeResponse")
    public List<LogEntryEntity> getAllLogsByChange(
        @WebParam(name = "change_type", targetNamespace = "")
        String changeType);

    /**
     * 
     * @param changeType
     * @param from
     * @param to
     * @return
     *     returns java.util.List<webservices.logs.client.wsdl.LogEntryEntity>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllLogsByDatesAndByChange", targetNamespace = "http://service.soap.example.com/", className = "webservices.logs.client.wsdl.GetAllLogsByDatesAndByChange")
    @ResponseWrapper(localName = "getAllLogsByDatesAndByChangeResponse", targetNamespace = "http://service.soap.example.com/", className = "webservices.logs.client.wsdl.GetAllLogsByDatesAndByChangeResponse")
    @Action(input = "http://service.soap.example.com/LogsService/getAllLogsByDatesAndByChangeRequest", output = "http://service.soap.example.com/LogsService/getAllLogsByDatesAndByChangeResponse")
    public List<LogEntryEntity> getAllLogsByDatesAndByChange(
        @WebParam(name = "from", targetNamespace = "")
        String from,
        @WebParam(name = "to", targetNamespace = "")
        String to,
        @WebParam(name = "change_type", targetNamespace = "")
        String changeType);

    /**
     * 
     * @param from
     * @param to
     * @return
     *     returns java.util.List<webservices.logs.client.wsdl.LogEntryEntity>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllLogsByDates", targetNamespace = "http://service.soap.example.com/", className = "webservices.logs.client.wsdl.GetAllLogsByDates")
    @ResponseWrapper(localName = "getAllLogsByDatesResponse", targetNamespace = "http://service.soap.example.com/", className = "webservices.logs.client.wsdl.GetAllLogsByDatesResponse")
    @Action(input = "http://service.soap.example.com/LogsService/getAllLogsByDatesRequest", output = "http://service.soap.example.com/LogsService/getAllLogsByDatesResponse")
    public List<LogEntryEntity> getAllLogsByDates(
        @WebParam(name = "from", targetNamespace = "")
        String from,
        @WebParam(name = "to", targetNamespace = "")
        String to);

    /**
     * 
     * @return
     *     returns java.util.List<webservices.logs.client.wsdl.LogEntryEntity>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllLogs", targetNamespace = "http://service.soap.example.com/", className = "webservices.logs.client.wsdl.GetAllLogs")
    @ResponseWrapper(localName = "getAllLogsResponse", targetNamespace = "http://service.soap.example.com/", className = "webservices.logs.client.wsdl.GetAllLogsResponse")
    @Action(input = "http://service.soap.example.com/LogsService/getAllLogsRequest", output = "http://service.soap.example.com/LogsService/getAllLogsResponse")
    public List<LogEntryEntity> getAllLogs();

}
