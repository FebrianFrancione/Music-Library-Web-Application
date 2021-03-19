package SOAP.service;

import javax.xml.ws.WebFault;

@WebFault(name = "RepException" , targetNamespace="http://localhost:8080/ws/logs")
public class RepException extends Exception{
    public RepException(String message) {
        super(message);
    }
}
