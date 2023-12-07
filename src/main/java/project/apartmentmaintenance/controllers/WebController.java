package project.apartmentmaintenance.controllers;

import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import project.apartmentmaintenance.model.Request;
import project.apartmentmaintenance.model.Tenant;
import project.apartmentmaintenance.repository.RequestRepository;
import project.apartmentmaintenance.repository.TenantRepository;
import project.apartmentmaintenance.repository.RequestSearchRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    TenantRepository tenantRepo;
    @Autowired
    RequestRepository requestRepo;
    @Autowired
    RequestSearchRepository requestSearchRepo;
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
    @RequestMapping(value="/request_form", method = RequestMethod.POST)
    public String formPage(){
        return "RequestForm";
    }

    @RequestMapping(value = "/submit_form", method=RequestMethod.POST)
    public String submitForm(@ModelAttribute Request request){
        requestRepo.save(request);
        return "RequestForm";
    }

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
}
