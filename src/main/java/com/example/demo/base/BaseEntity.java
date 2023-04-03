package com.example.demo.base;

public class BaseEntity {

    private String documentId;

    public BaseEntity() {
        // Default constructor required by Firestore
    }

    public String getDocumentId(){
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
