package model;

public class Feedback {
    private int costumerId;
    private String rating;
    private String comment;

    public Feedback(int costumerId, String rating, String comment) {
        this.costumerId = costumerId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(int costumerId) {
        this.costumerId = costumerId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

