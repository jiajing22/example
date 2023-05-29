package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.DonationHistory;
import com.example.demo.entity.FormData;
import com.example.demo.entity.RegFormFields;
import com.example.demo.entity.RegistrationForm;
import com.example.demo.util.Util;
import com.google.cloud.Timestamp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class RegistrationFormService extends GeneralService {
    private static final String COLLECTION_NAME = "registrationForm";
    private static final String FIELD_COLLECTION_NAME = "regFormField";
    private static final String FORM_STATUS = "submitted";

    public String addRegForm (FormData formData) throws ExecutionException, InterruptedException {
        //Set regForm Data
        String lastID = firestoreGetLastID(COLLECTION_NAME);
        formData.getRegForm().setDocumentId(Util.generateId("Registration", lastID));
        formData.getRegForm().setRegFormId(formData.getRegForm().getDocumentId());
        formData.getRegForm().setSubmitTime(Timestamp.now());
        formData.getRegForm().setFormStatus(FORM_STATUS);
        formData.getRegForm().setDonorId(formData.getRegForm().getIc());

        //set formFields Data
        formData.getFormFields().setDocumentId(Util.generateId("RegFormField", lastID));
        formData.getFormFields().setDonorId(formData.getRegForm().getIc());

        //set FK for both data
        formData.getFormFields().setRegFormId(formData.getRegForm().getRegFormId());
        formData.getRegForm().setFormFieldsId(formData.getFormFields().getDocumentId());

        String regFormStatus = firestoreCreate(formData.getRegForm(), COLLECTION_NAME);
        String formFieldStatus = firestoreCreate(formData.getFormFields(), FIELD_COLLECTION_NAME);
        if ( regFormStatus == null && formFieldStatus == null){
            return null;
        }
        return regFormStatus;
    }

    public RegistrationForm getRegForm(String documentId) throws ExecutionException, InterruptedException {
        return (RegistrationForm)firestoreGet(documentId, COLLECTION_NAME, RegistrationForm.class);
    }

    public String updateRegForm (RegistrationForm regForm) throws ExecutionException, InterruptedException {
        regForm.setDocumentId(regForm.getDocumentId());
        return firestoreUpdate(regForm, COLLECTION_NAME);
    }

    public String deleteRegForm(String documentId) throws ExecutionException, InterruptedException {
        return firestoreDelete(documentId, COLLECTION_NAME);
    }

    public List<RegistrationForm> getAllRegForm() throws ExecutionException, InterruptedException {
        return firestoreGetAll(RegistrationForm.class, COLLECTION_NAME);
    }

    public List<FormData> getRegFormByIc(String donorIc) throws ExecutionException, InterruptedException {
        List <RegistrationForm> registrationFormList = firestoreGetByIc(RegistrationForm.class, COLLECTION_NAME, donorIc);
        List <RegFormFields> formFieldsList = firestoreGetByIc(RegFormFields.class, FIELD_COLLECTION_NAME, donorIc);

        Map<String, FormData> formDataMap = new HashMap<>();

        // Associate form fields with registration forms based on regFormId
        for (RegFormFields formFields : formFieldsList) {
            FormData formData = new FormData();
            formData.setFormFields(formFields);
            formDataMap.put(formFields.getRegFormId(), formData);
        }

        List<FormData> formDataList = new ArrayList<>();

        // Combine registration forms with corresponding form fields
        for (RegistrationForm registrationForm : registrationFormList) {
            FormData formData = formDataMap.get(registrationForm.getRegFormId());
            if (formData != null) {
                formData.setRegForm(registrationForm);
                formDataList.add(formData);
            }
        }

        return formDataList;
    }
}
