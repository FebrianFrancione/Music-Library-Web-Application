package Soap.service;

import Soap.utils.StudentData;

import javax.jws.WebService;

/**
 * This class holds the implementation of the methods of our SOAP web service
 */
@WebService(endpointInterface = "Soap.service.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	/**
	 * Prints a simple message with input from the client.
	 * @param content
	 * @return
	 */
	@Override
	public String sayHelloWorld(String content) {
		return "Hello " + content + "!";
	}

	@Override
	public StudentData getStudent(String first_name){
		String course_name = "SOEN487";
		String res = "Hello, I am " + first_name + " from " + course_name;
		return new StudentData(first_name, course_name, res);
	}
}