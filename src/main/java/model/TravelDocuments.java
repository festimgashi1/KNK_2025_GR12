package model;

import java.util.Date;

public class TravelDocuments {
    private int documentId;
    private int costumerId;
    private Integer bookingId; // E lejojme si nullable
    private Date issueDate;
    private Date expiryDate;
    private String isValid;
    private String fileAttachment;

    public TravelDocuments(int documentId, int costumerId, Integer bookingId,
                           Date issueDate, Date expiryDate, String isValid, String fileAttachment) {
        this.documentId = documentId;
        this.costumerId = costumerId;
        this.bookingId = bookingId;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.isValid = isValid;
        this.fileAttachment = fileAttachment;
    }

    public int getDocumentId() {
        return documentId;
    }

    public int getCostumerId() {
        return costumerId;
    }

    public Integer getBookingId() {
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


    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public void setFileAttachment(String fileAttachment) {
        this.fileAttachment = fileAttachment;
    }
}
