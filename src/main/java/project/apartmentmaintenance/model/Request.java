package project.apartmentmaintenance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;
import java.time.LocalDateTime;


@Document(collection="MaintenanceRequests")
public class Request {
    @Id
    private String RequestID;

    private String apartNum;   // one apartment per person, can only submit for own apartment
    private String areaProblem; // dropdown menu?
    private String descProblem;  //Only one problem allowed per request
    private LocalDateTime reqTime; //check this object type documentation

    private boolean status; // T - completed, F - pending

    public Request(String apartNum,  String areaProblem, String descProblem){
        this.apartNum = apartNum;
        this.areaProblem = areaProblem;
        this.descProblem = descProblem;

        reqTime = LocalDateTime.now();
        status = false;
    }

    public String getRequestID(){
        return RequestID;
    }
    public String getApartNum(){
        return apartNum;
    }
    public String getAreaProblem(){
        return areaProblem;
    }
    public String getDescProblem(){
        return areaProblem;
    }

    public LocalDateTime getReqTime(){
        return reqTime;
    }

    public boolean getStatus(){
        return status;
    }
    public void setStatus(boolean status){
        this.status = status;
    }
    public void setRequestID(String RequestID){
        this.RequestID = RequestID;
    }
    public void setReqTime(LocalDateTime reqTime){
        this.reqTime = reqTime;
    }

    }