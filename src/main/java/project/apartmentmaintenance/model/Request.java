package project.apartmentmaintenance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;
import java.util.Date;

@Document(collection="MaintenanceRequests")
public class Request {
    @Id
    private String RequestID;

    private int apartNum;   // one apartment per person, can only submit for own apartment
    private String areaProblem; // dropdown menu?
    private String descProblem;  //Only one problem allowed per request
    private Date dateTime; //check this object type documentation
    private Image photoProblem;// One Phto, if I can get it to work
    private boolean status; // T - completed, F - pending

}