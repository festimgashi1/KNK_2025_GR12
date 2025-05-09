package services;

import model.Costumer;
import model.dto.AirlineDto;
import model.dto.CostumerDto;
import model.dto.CreateCostumerDto;
import repository.AirlineRepository;
import repository.CostumerRepository;
import model.Airline;

public class SignupService {
    private final CostumerRepository costumerRepository = new CostumerRepository();
    private final AirlineRepository airlineRepository = new AirlineRepository();


    public Costumer create(CreateCostumerDto dto) throws Exception {
        if (dto.getFirstName().isEmpty() ||
                dto.getLastName().isEmpty() ||
                dto.getEmail().isEmpty() ||
                dto.getPhoneNumber().isEmpty() ||
                dto.getBirthDate() == null ||
                dto.getPassword().isEmpty() ||
                dto.getAddress().isEmpty() ||
                dto.getConfirmPassword().isEmpty()) {
            throw new Exception("Provided information is not valid!");
        }

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new Exception("Password should be the same as Confirm Password");
        }

        Costumer costumerExist = this.costumerRepository.getByEmail(dto.getEmail());
        if (costumerExist != null) {
            throw new Exception("Email already exists!");
        }

        String salt = PasswordHasher.generateSalt();
        String hashPass = PasswordHasher.generateSaltedHash(dto.getPassword(), salt);

        CostumerDto costumerDto = new CostumerDto(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getBirthDate(),
                salt,
                hashPass,
                dto.getPhoneNumber()
        );

        Costumer costumer = this.costumerRepository.create(costumerDto);
        if (costumer == null) {
            throw new Exception("User is not created!");
        }

        return costumer;
    }
    public Airline createAirline(AirlineDto dto) throws Exception {
        if (dto.getAirlinename().isEmpty() ||
                dto.getEmail().isEmpty() ||
                dto.getPhoneNumber().isEmpty() ||
                dto.getHashpass().isEmpty()) {
            throw new Exception("Provided information is not valid!");
        }

        Airline existing = airlineRepository.getEmail(dto.getEmail());
        if (existing != null) {
            throw new Exception("Airline email already exists!");
        }

        String salt = PasswordHasher.generateSalt();
        String hashPass = PasswordHasher.generateSaltedHash(dto.getHashpass(), salt);
        dto.setSalt(salt);
        dto.setHashpass(hashPass);

        Airline airline = airlineRepository.create(dto);
        if (airline == null) {
            throw new Exception("Airline is not created!");
        }

        return airline;
    }
}
