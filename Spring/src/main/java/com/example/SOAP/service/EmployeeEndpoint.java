package com.example.SOAP.service;

//import com.jpworks.employee.*;
import com.example.demo.Entity.Album;
import com.jpworks.album.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.feature.Features;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Features(features = "org.apache.cxf.ext.logging.LoggingFeature")
public class EmployeeEndpoint implements AlbumServicePortType {

    BackendService backendService;

    public EmployeeEndpoint (BackendService backendService){
        this.backendService = backendService;
    }

    @Override
    public Album getAlbumByISRC(AlbumByISRCRequest parameters) throws Exception {
        Album albumResponse = backendService.getAlbumByISRC(parameters.getISRC());
        return albumResponse;
    }


    @Override
    public AlbumsResponse getAlbumsByName(AlbumByNameRequest parameters) {
        return null;
    }

//    add the other methods for LOGS

//    @Override
//    public AlbumsResponse getAlbumsByName(AlbumByNameRequest parameters) {
//        return null;
//    }

//    @Override
//    public AlbumsResponse getAlbumsByName(AlbumByNameRequest parameters){
//        AlbumsResponse albumsResponse = new AlbumsResponse();
//        try{
//            albumsResponse.getAlbum().addAll(b)
//        }
//    }

//    @Override
//    public EmployeesResponse getEmployeesByName(EmployeeByNameRequest parameters) {
//        EmployeesResponse employeesResponse = new EmployeesResponse();
//        try{
//            employeesResponse.getEmployee().addAll(backendService.getEmployeesByName(parameters.getFirstname(), parameters.getLastname()));
//        }
//        catch (Exception e){
////            throw new GeneralError();
//            log.error("Error while setting values for employee object", e);
//        }
//        return employeesResponse;
//    }
//
//    @Override
//    public EmployeeResponse getEmployeeById(EmployeeByIdRequest parameters) {
////        if(parameters.getId() > 3){
////            throw new GeneralError();
////        }
//        EmployeeResponse employeeResponse = new EmployeeResponse();
//        try{
//            employeeResponse.setEmployee(backendService.getEmployeeById(parameters.getId()));
//        }
//        catch (Exception e){
////            throw new GeneralError();
//            log.error("Error while setting values for employee object", e);
//        }
//        return employeeResponse;
//    }
}
