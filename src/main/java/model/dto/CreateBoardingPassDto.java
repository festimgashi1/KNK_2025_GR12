package model.dto;

import java.time.LocalDate;

public class CreateBoardingPassDto {
    private String name;
    private String from;
    private String to;
    private LocalDate flightDate;
    private String flightCode;
    private String gate;
    private String boardingTime;
    private String seat;

    public CreateBoardingPassDto(String name, String from, String to, LocalDate flightDate,
                                 String flightCode, String gate, String boardingTime, String seat) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.flightDate = flightDate;
        this.flightCode = flightCode;
        this.gate = gate;
        this.boardingTime = boardingTime;
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public String getGate() {
        return gate;
    }

    public String getBoardingTime() {
        return boardingTime;
    }

    public String getSeat() {
        return seat;
    }
}
