package services;

import model.BoardingPass;
import repository.BoardingPassRepository;

public class BoardingPassService {

    private final BoardingPassRepository repository;

    public BoardingPassService() {
        this.repository = new BoardingPassRepository();
    }

    public BoardingPass getBoardingPass(int reservationId) {
        return repository.getBoardingPassByReservationId(reservationId);
    }
}
