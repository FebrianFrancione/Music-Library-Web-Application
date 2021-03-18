package com.example.SOAP.endpoints;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.jaxws.JaxwsHandler;
import com.example.SOAP.service.LogsServiceImpl;
import java.io.IOException;

/**
 * This class publishes our web service to be accessed by a client
 */
public class ServicePublisher {

    static int port = 8090;
    static String servicePath = "/ws/logs";
    static String BASE_URI = "http://localhost:" + port + servicePath;

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        //Endpoint.publish(BASE_URI, new LogsServiceImpl());
        System.out.println("SOAP Service listening on " + BASE_URI + "?wsdl");

        NetworkListener networkListener = new NetworkListener("jaxws-listener", "0.0.0.0", port);
        HttpHandler httpHandler = new JaxwsHandler(new LogsServiceImpl());

        HttpServer httpServer = new HttpServer();
        httpServer.getServerConfiguration().addHttpHandler(httpHandler, servicePath);
        httpServer.addListener(networkListener);

        httpServer.start();
        System.in.read();
        httpServer.stop();
    }
}