package SOAP.endpoint;

import SOAP.service.LogsServiceImpl;
import javax.xml.ws.Endpoint;
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
      Endpoint.publish(BASE_URI, new LogsServiceImpl());
      System.out.println("SOAP Service listening on " + BASE_URI + "?wsdl");
}
}
