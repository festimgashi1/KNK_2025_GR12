package session;

import model.Tickets;

public class TicketSession {
    private static TicketSession instance;
    private Tickets selectedTicket;
    private String seatNumber;

    private TicketSession() {}

    public static TicketSession getInstance() {
        if (instance == null) {
            instance = new TicketSession();
        }
        return instance;
    }

    public void setSelectedTicket(Tickets ticket) {
        this.selectedTicket = ticket;
    }

    public Tickets getSelectedTicket() {
        return selectedTicket;
    }

    public void clear() {
        selectedTicket = null;
        seatNumber = null;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}