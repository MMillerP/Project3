package project.apartmentmaintenance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="TenantList")
public class Tenant {
    @Id
    private String tenantID;

    private String name;
    private String teleNum;
    private String emailAddr;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private int apartNum; // shared with Request

    public Tenant(String name, String teleNum, String emailAddr, LocalDateTime checkIn, LocalDateTime checkOut, int apartNum){
        this.name = name;
        this.teleNum=teleNum;
        this.emailAddr=emailAddr;
        this.checkIn=checkIn;
        this.checkOut=checkOut;
        this.apartNum=apartNum;
    }

}
