package com.example.demo.controller;

import com.example.demo.entity.CampaignInfo;
import com.example.demo.entity.Staff;
import com.example.demo.entity.User;
import com.example.demo.service.CampaignInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/eDonor")
public class CampaignInfoController {


    @Autowired
    private CampaignInfoService campaignInfoService;

    @PostMapping("/campaign")
    public String addCampaign(@RequestBody CampaignInfo campaign) throws ExecutionException, InterruptedException {
        return campaignInfoService.addCampaignInfo(campaign);
    }
    @GetMapping("/campaign/{documentId}")
    public CampaignInfo getCampaign(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return campaignInfoService.getCampaignInfo(documentId);
    }
    @PutMapping("/campaign")
    public String updateCampaign(@RequestBody CampaignInfo campaign) throws ExecutionException, InterruptedException {
        return campaignInfoService.updateCampaignInfo(campaign);
    }
    @DeleteMapping("/campaign/{documentId}")
    public String deleteCampaign(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return campaignInfoService.deleteCampaignInfo(documentId);
    }

//    @GetMapping("/campaign/get-all")
//    public List<CampaignInfo> getAllCampaign() throws ExecutionException, InterruptedException {
//        System.out.println("Inside API");
//        return campaignInfoService.getAllCampaignInfo();
//    }

    @RequestMapping(value = "/campaign/get-all", method = RequestMethod.GET)
    public List<CampaignInfo> getAllCampaign() throws ExecutionException, InterruptedException {
        return campaignInfoService.getAllCampaignInfo();
    }
}
