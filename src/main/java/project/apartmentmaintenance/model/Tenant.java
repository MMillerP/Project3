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
    private String apartNum; // shared with Request

    public Tenant(String name, String teleNum, String emailAddr, String apartNum){
        this.name = name;
        this.teleNum=teleNum;
        this.emailAddr=emailAddr;
        checkIn= LocalDateTime.now();;
        checkOut= checkIn.plusMonths(6);
        this.apartNum=apartNum;
    }

    public String getTenantID(){
        return tenantID;
    }
    public String getName(){
        return name;
    }
    public String getTeleNum(){
        return teleNum;
    }
    public String getEmailAddr(){
        return emailAddr;
    }
    public LocalDateTime getCheckIn(){
        return checkIn;
    }
    public LocalDateTime getCheckOut(){
        return checkOut;
    }
    public String getApartNum(){
        return apartNum;
    }

    public void setTenantID(String tenantID) {
        this.tenantID = tenantID;
    }
}
