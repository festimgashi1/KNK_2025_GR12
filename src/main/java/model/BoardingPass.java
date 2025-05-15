package model;

import java.time.LocalDate;

public class BoardingPass {
    private int reservationId;
    private String name;
    private String from;
    private String to;
    private LocalDate flightDate;
    private String flightCode;
    private String gate;
    private String boardingTime;
    private String seat;

    public BoardingPass(int id, String name, String from, String to, LocalDate flightDate,
                        String flightCode, String gate, String boardingTime, String seat) {
        this.reservationId = id;
        this.name = name;
        this.from = from;
        this.to = to;
        this.flightDate = flightDate;
        this.flightCode = flightCode;
        this.gate = gate;

        this.boardingTime = boardingTime;
        this.seat = seat;
    }


    public String getBoardingTime() {
        return boardingTime;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public String getFrom() {
        return from;
    }

    public String getGate() {
        return gate;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getName() {
        return name;
    }

    public String getSeat() {
        return seat;
    }

    public String getTo() {
        return to;
    }
}