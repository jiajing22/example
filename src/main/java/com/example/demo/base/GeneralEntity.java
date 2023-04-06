package com.example.demo.base;

public class GeneralEntity {

    private String documentId;

    public GeneralEntity() {
        // Default constructor required by Firestore
    }

    public String getDocumentId(){
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
