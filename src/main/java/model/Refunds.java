package model;

import java.util.Date;

public class Refunds {
    private int refundId;
    private int paymentid;
    private int bookingId;
    private int flightId;
    private int refundAmount;
    private String refundStatus;
    private String refundReason;
    private Date transactionDate;

    public Refunds(int refundId, int paymentid, int bookingId, int flightId, int refundAmount, String refundStatus, String refundReason, Date transactionDate) {
        this.refundId = refundId;
        this.paymentid = paymentid;
        this.bookingId = bookingId;
        this.flightId = flightId;
        this.refundAmount = refundAmount;
        this.refundStatus = refundStatus;
        this.refundReason = refundReason;
        this.transactionDate = transactionDate;
    }

    public int getRefundId() {
        return refundId;
    }

    public int getPaymentid() {
        return paymentid;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getFlightId() {
        return flightId;
    }

    public int getRefundAmount() {
        return refundAmount;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }
}

