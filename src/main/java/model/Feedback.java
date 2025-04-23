package model;

public class Feedback {
    private int fb_id;
    private int costumerId;
    private int flightNumber;
    private int rating;
    private String comments;
    private String status;

    public Feedback(int fb_id, int costumerId, int flightNumber, int rating, String comments, String status) {
        this.fb_id = fb_id;
        this.costumerId = costumerId;
        this.flightNumber = flightNumber;
        this.rating = rating;
        this.comments = comments;
        this.status = status;
    }

    public int getFb_id() {
        return fb_id;
    }

    public int getCostumerId() {
        return costumerId;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public int getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

    public String getStatus() {
        return status;
    }

    public void setFb_id(int fb_id) {
        this.fb_id = fb_id;
    }

    public void setCostumerId(int costumerId) {
        this.costumerId = costumerId;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
