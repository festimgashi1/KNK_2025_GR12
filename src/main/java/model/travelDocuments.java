package model;

import java.util.Date;

public class travelDocuments {
    private int documentId;
    private int klientId;
    private int bookingId;
    private Date issueDate;
    private Date expiryDate;
    private String isValid;
    private String fileAttachment;

    public travelDocuments(int documentId, int klientId, int bookingId, Date issueDate, Date expiryDate, String isValid, String fileAttachment) {
        this.documentId = documentId;
        this.klientId = klientId;
        this.bookingId = bookingId;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.isValid = isValid;
        this.fileAttachment = fileAttachment;
    }

    public int getDocumentId() {
        return documentId;
    }

    public int getKlientId() {
        return klientId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getIsValid() {
        return isValid;
    }

    public String getFileAttachment() {
        return fileAttachment;
    }
}
