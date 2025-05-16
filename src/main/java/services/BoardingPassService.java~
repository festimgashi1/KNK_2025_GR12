package services;

import model.BoardingPass;
import model.dto.CreateBoardingPassDto;
import repository.BoardingPassRepository;

public class BoardingPassService {

    private final BoardingPassRepository repository;

    public BoardingPassService() {
        this.repository = new BoardingPassRepository();
    }

    public BoardingPass create(CreateBoardingPassDto dto) throws Exception {
        return repository.save(dto);
    }
}
