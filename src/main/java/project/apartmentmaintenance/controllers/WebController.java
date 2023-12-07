package project.apartmentmaintenance.controllers;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import project.apartmentmaintenance.model.Request;
import project.apartmentmaintenance.model.Tenant;
import project.apartmentmaintenance.repository.RequestRepository;
import project.apartmentmaintenance.repository.TenantRepository;
import project.apartmentmaintenance.repository.RequestSearchRepository;
import project.apartmentmaintenance.repository.TenantSearchRepository;

@Controller
public class WebController {

    @Autowired
    TenantRepository tenantRepo;
    @Autowired
    RequestRepository requestRepo;
    @Autowired
    RequestSearchRepository requestSearchRepo;
    @Autowired
    TenantSearchRepository tenantSearchRepo;
    boolean ANumSortASC=false;
    boolean AProblemSortASC = false;
    boolean DTimeSortASC = false;
    boolean statusSortASC= false;

    @RequestMapping(value="/")
    public String blank(){
        return "redirect:home";
    }
    @RequestMapping(value="/home")
    public String branchPage(){// has multiple buttons that branch to other pages, ie; viewing tenant table or request table
        return null;
    }

    //------------------------------------------------------------------------- Methods for Request Form

    @RequestMapping(value="/request_form", method = RequestMethod.POST)
    public String formPage(){
        return "RequestForm";
    }

    @RequestMapping(value = "/submit_form", method=RequestMethod.POST)
    public String submitForm(@ModelAttribute Request request){
        requestRepo.save(request);
        return "RequestForm";
    }

    //------------------------------------------------------------------------- Methods for Request Table

    @RequestMapping(value = "/request_table",method=RequestMethod.POST)
    public String requestTable(Model model){
        model.addAttribute("requestList",requestRepo.findAll());
        return "RequestTable";
    }

    //@{/request_table/change_status/{id}/{apartNum}/{areaProblem}/{descProblem}/{reqTime}/{status} (id = ${req.RequestID},apartNum=${req.apartNum},areaProblem=${req.areaProblem}, descProblem=${req.descProblem},reqTime=${req.reqTime},status=${req.status})}
    @RequestMapping(value = "/request_table/change_status/")
    public String changeStatus(@RequestParam(value = "id") String id) {
        return "RequestTable";
    }

    @RequestMapping(value="/sortANum")
    public String sortANum(Model model){
        if(!ANumSortASC){
            model.addAttribute("requestList",requestSearchRepo.sortANumQueryASC());
            AProblemSortASC=false;
            DTimeSortASC=false;
            statusSortASC=false;
            ANumSortASC=true;
        }
        else{
            model.addAttribute("requestList",requestSearchRepo.sortANumQueryDESC());
            AProblemSortASC=false;
            DTimeSortASC=false;
            statusSortASC=false;
            ANumSortASC=false;
        }
        return "RequestTable";
    }

    @RequestMapping(value="/sortAreaProblem")
    public String sortAreaProblem(Model model){
        if(!AProblemSortASC){
            model.addAttribute("requestList",requestSearchRepo.sortAProblemQueryASC());
            AProblemSortASC=true;
            DTimeSortASC=false;
            statusSortASC=false;
            ANumSortASC=false;
        }
        else{
            model.addAttribute("requestList",requestSearchRepo.sortAProblemQueryDESC());
            AProblemSortASC=false;
            DTimeSortASC=false;
            statusSortASC=false;
            ANumSortASC=false;
        }
        return "RequestTable";
    }

    @RequestMapping(value="/sortDateTime")
    public String sortDateTime(Model model){
        if(!DTimeSortASC){
            model.addAttribute("requestList",requestSearchRepo.sortDateTimeQueryASC());
            AProblemSortASC=false;
            DTimeSortASC=true;
            statusSortASC=false;
            ANumSortASC=false;
        }
        else{
            model.addAttribute("requestList",requestSearchRepo.sortDateTimeQueryDESC());
            AProblemSortASC=false;
            DTimeSortASC=false;
            statusSortASC=false;
            ANumSortASC=false;
        }
        return "RequestTable";
    }
    @RequestMapping(value="/sortStatus")
    public String sortStatus(Model model){
        if(!statusSortASC){
            model.addAttribute("requestList",requestSearchRepo.sortStatusQueryASC());
            AProblemSortASC=false;
            DTimeSortASC=false;
            statusSortASC=true;
            ANumSortASC=false;
        }
        else{
            model.addAttribute("requestList",requestSearchRepo.sortStatusQueryDESC());
            AProblemSortASC=false;
            DTimeSortASC=false;
            statusSortASC=false;
            ANumSortASC=false;
        }
        return "RequestTable";
    }

    //------------------------------------------------------------------------- Methods for Tenant Table

    @RequestMapping(value="/tenant_table")
    public String tenantTable(Model model){
        model.addAttribute("tenantList",tenantRepo.findAll());
        return "TenantTable";
    }

    @RequestMapping(value = "/addTenant", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute Tenant tenant) {
        tenantRepo.save(tenant);
        return "redirect:/tenant_table";
    }

    @RequestMapping(value="/deleteTenant")
    public String deleteTenant(@RequestParam String deleteTenant){
        tenantRepo.deleteById(deleteTenant);
        return "redirect:tenant_table";
    }

    @RequestMapping(value="/editRedirectTenant")
    public String editRedirectTenant(Model model, @RequestParam String search) {
        model.addAttribute("tenantList",tenantSearchRepo.searchTenants(search));
        model.addAttribute("search",search);
        return "Edit";
    }

    @RequestMapping(value="/editTenant", method = RequestMethod.GET)
    public String editTenant(@RequestParam String id, @RequestParam String apartNum){
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/");
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("TenantList");
        Document query = new Document().append("_id",id);
        System.out.println(id);
        Bson updates = Updates.set("apartNum",apartNum);
        System.out.println(apartNum);
        UpdateResult result = collection.updateOne(query,updates);
        System.out.println(result);
        return "redirect:/tenant_table";
    }
}
