package model;

public class Feedback {
    private int fb_id;
    private int costumerId;
    private int flightNumber;
    private String rating;
    private String comments;


    public Feedback(int costumerId, int flightNumber, String rating, String comments) {
        this.costumerId = costumerId;
        this.flightNumber = flightNumber;
        this.rating = rating;
        this.comments = comments;
    }


    public Feedback(int fb_id, int costumerId, int flightNumber, String rating, String comments) {
        this.fb_id = fb_id;
        this.costumerId = costumerId;
        this.flightNumber = flightNumber;
        this.rating = rating;
        this.comments = comments;
    }

    public int getFb_id() {
        return fb_id;
    }

    public void setFb_id(int fb_id) {
        this.fb_id = fb_id;
    }

    public int getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(int costumerId) {
        this.costumerId = costumerId;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
