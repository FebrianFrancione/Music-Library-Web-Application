package SOAP.service;

import javax.xml.ws.WebFault;

@WebFault(name = "RepException" , targetNamespace="http://service.SOAP/")
public class RepException extends Exception{
    public RepException(String message) {
        super(message);
    }
}
