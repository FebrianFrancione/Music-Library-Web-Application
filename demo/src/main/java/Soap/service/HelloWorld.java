package Soap.service;

import Soap.utils.StudentData;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface for our HelloWorld web service
 */
@WebService
public interface HelloWorld {
    @WebMethod
    String sayHelloWorld(String content);
    @WebMethod
    StudentData getStudent(String first_name);
}
