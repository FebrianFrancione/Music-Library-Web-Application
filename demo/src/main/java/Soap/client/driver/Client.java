package Soap.client.driver;

import Soap.client.service.*;

public class Client {

    public static void main(String[] args) {
        HelloWorldImplService service = new HelloWorldImplService();
        HelloWorld port = service.getHelloWorldImplPort();
        String result = port.sayHelloWorld("World");
        System.out.println(result);
        StudentData student = port.getStudent("John Doe");
        System.out.println(student.getServerMessage());
    }
}
