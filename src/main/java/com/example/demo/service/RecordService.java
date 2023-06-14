package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.DonationHistory;
import com.example.demo.entity.Donor;
import com.example.demo.entity.Record;
import com.example.demo.util.Util;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class RecordService extends GeneralService {
    private static final String COLLECTION_NAME = "record";
    private static final String COLLECTION_NAME_HISTORY = "donationHistory";

    private DonationHistoryService historyService;

    public String addRecord (Record record) throws Exception {
        String lastID = firestoreGetLastID(COLLECTION_NAME);
        record.setDocumentId(Util.generateId("Record",lastID));
        record.setRecordId(record.getDocumentId());
        String status = firestoreCreate(record, COLLECTION_NAME);

        DonationHistory history = new DonationHistory();
        String lastHID = firestoreGetLastID(COLLECTION_NAME_HISTORY);
        history.setDocumentId(Util.generateId("History",lastHID));
        history.setHistoryId(history.getDocumentId());
        history.setBloodSerialNo(record.getBloodSerialNo());
        history.setDonateDate(record.getRegDate());
        history.setAmount(record.getVolume());
        history.setdHospital(record.getBloodCentre());
        history.setDonorId(record.getDonorIc());
        history.setRecordId(record.getRecordId());

        if (record.getRecRemark() != null ){
            history.setRecRemark(record.getRecRemark());
        }

        String insertHistory = firestoreCreate(history, COLLECTION_NAME_HISTORY);

        if (insertHistory == null || status == null){
            return null;
        }

        Donor donorInfo = getDonor(record.getDonorIc());
        if (donorInfo !=null ){
            List <DonationHistory> list = getHistoryRecordByIc(record.getDonorIc());
            donorInfo.setDonationTimes(list.size());
            String update = firestoreUpdate(donorInfo, "donor");
        }
        return record.getRecordId();
    }

    public Donor getDonor(String donorId) throws ExecutionException, InterruptedException {
        return (Donor)firestoreGetByUserId(donorId, "donor", Donor.class);
    }

    public Record getRecord(String documentId) throws ExecutionException, InterruptedException {
        return (Record)firestoreGet(documentId, COLLECTION_NAME, Record.class);
    }

    public String updateRecord (Record record) throws ExecutionException, InterruptedException {
        Record oriRecord = getRecord(record.getDocumentId());
        if (oriRecord == null){
            return null;
        }
        record.setRecordId(oriRecord.getDocumentId());
        record.setStaffId(oriRecord.getStaffId());
        DonationHistory history = getDonationHistoryByRecId(record.getDocumentId());
        if (history == null){
            return "history";
        }
        history.setAmount(record.getVolume());
        history.setBloodSerialNo(record.getBloodSerialNo());
        history.setdHospital(record.getBloodCentre());
        history.setDonateDate(record.getRegDate());
        //TODO: delete the line below
        history.setDonorId(record.getDonorIc());
        if(record.getRecRemark() != null ){
            history.setRecRemark(record.getRecRemark());
        }
        String update = firestoreUpdate(history, COLLECTION_NAME_HISTORY);
        return firestoreUpdate(record, COLLECTION_NAME);
    }

    public String deleteRecord(String documentId) throws ExecutionException, InterruptedException {
        return firestoreDelete(documentId, COLLECTION_NAME);
    }

    public List<Record> getAllRecord() throws ExecutionException, InterruptedException {
        return firestoreGetAll(Record.class, COLLECTION_NAME);
    }

    public List<DonationHistory> getHistoryRecordByIc(String donorIc) throws ExecutionException, InterruptedException {
        return firestoreGetByIc(DonationHistory.class, COLLECTION_NAME_HISTORY, donorIc);
    }

    public DonationHistory getDonationHistoryByRecId(String documentId) throws ExecutionException, InterruptedException {
        return getHistoryById(documentId, COLLECTION_NAME_HISTORY);
    }
}
