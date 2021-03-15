package com.example.SOAP.service;

import com.jpworks.employee.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.feature.Features;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Features(features = "org.apache.cxf.ext.logging.LoggingFeature")
public class EmployeeEndpoint implements EmployeeServicePortType{

    BackendService backendService;

    public EmployeeEndpoint (BackendService backendService){
        this.backendService = backendService;
    }

    @Override
    public EmployeesResponse getEmployeesByName(EmployeeByNameRequest parameters) {
        EmployeesResponse employeesResponse = new EmployeesResponse();
        try{
            employeesResponse.getEmployee().addAll(backendService.getEmployeesByName(parameters.getFirstname(), parameters.getLastname()));
        }
        catch (Exception e){
//            throw new GeneralError();
            log.error("Error while setting values for employee object", e);
        }
        return employeesResponse;
    }

    @Override
    public EmployeeResponse getEmployeeById(EmployeeByIdRequest parameters) {
//        if(parameters.getId() > 3){
//            throw new GeneralError();
//        }
        EmployeeResponse employeeResponse = new EmployeeResponse();
        try{
            employeeResponse.setEmployee(backendService.getEmployeeById(parameters.getId()));
        }
        catch (Exception e){
//            throw new GeneralError();
            log.error("Error while setting values for employee object", e);
        }
        return employeeResponse;
    }
}
