package com.example.demo.controller;

import com.example.demo.entity.CampaignInfo;
import com.example.demo.service.CampaignInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
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

    @GetMapping("/campaign")
    public List<CampaignInfo> getAllCampaign() throws ExecutionException, InterruptedException {
        return campaignInfoService.getAllCampaignInfo();
    }
}
