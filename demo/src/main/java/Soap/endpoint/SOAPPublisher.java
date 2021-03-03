package Soap.endpoint;

import Soap.service.HelloWorldImpl;
import javax.xml.ws.Endpoint;

public class SOAPPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8090/helloworld", new HelloWorldImpl());
        System.out.println("Server is running");
    }
}
