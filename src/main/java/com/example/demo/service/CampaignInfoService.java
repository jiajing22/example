package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.CampaignInfo;
import com.example.demo.util.Util;
import com.google.cloud.Timestamp;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CampaignInfoService extends GeneralService {
    private static final String COLLECTION_NAME = "campaignInfo";

    public String addCampaignInfo (CampaignInfo info) throws ExecutionException, InterruptedException {
        String lastID = firestoreGetLastID(COLLECTION_NAME);
        info.setDocumentId(Util.generateId("Campaign",lastID));
        info.setCampaignId(info.getDocumentId());
        info.setPostTime(Timestamp.now());
        return firestoreCreate(info, COLLECTION_NAME);
    }

    public CampaignInfo getCampaignInfo(String documentId) throws ExecutionException, InterruptedException {
        return (CampaignInfo)firestoreGet(documentId, COLLECTION_NAME, CampaignInfo.class);
    }

    public String updateCampaignInfo (CampaignInfo info) throws ExecutionException, InterruptedException {
        CampaignInfo getPost = getCampaignInfo(info.getCampaignId());
        if( getPost == null){
            return null;
        }
        getPost.setTitle(info.getTitle());
        getPost.setEventDate(info.getEventDate());
        getPost.setLocation(info.getLocation());
        getPost.setDescription(info.getDescription());
        return firestoreUpdate(getPost, COLLECTION_NAME);
    }

    public String deleteCampaignInfo(String documentId) throws ExecutionException, InterruptedException {
        return firestoreDelete(documentId, COLLECTION_NAME);
    }

    public List<CampaignInfo> getAllCampaignInfo() throws ExecutionException, InterruptedException {
        return firestoreGetAll(CampaignInfo.class, COLLECTION_NAME);
    }
}
