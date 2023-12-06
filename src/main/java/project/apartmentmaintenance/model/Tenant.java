package project.apartmentmaintenance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="TenantList")
public class Tenant {
    @Id
    private String tenantID;
    private String name;
    private String teleNum;
    private String emailAddr;
    private Date checkIn;
    private Date checkOut;
    private int apartNum; // shared with Request
}
