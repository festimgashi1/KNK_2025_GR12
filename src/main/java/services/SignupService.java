package services;

import model.Costumer;
import model.PendingAirline;
import model.dto.*;
import repository.AirlineRepository;
import repository.CostumerRepository;
import model.Airline;
import repository.PendingAirlineRepository;

public class SignupService {
    private final CostumerRepository costumerRepository = new CostumerRepository();
    private final AirlineRepository airlineRepository = new AirlineRepository();
    private final PendingAirlineRepository pendingAirlineRepository = new PendingAirlineRepository();

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

        if(dto.getFirstName().matches("^[A-Z][a-zA-Z]{1,29}$") || dto.getLastName().matches("^[A-Z][a-zA-Z]{1,29}$")){
            throw new Exception("Name should only contain letters, and start with upper case.");
        }

        if (!dto.getEmail().matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$")) {
            throw new Exception("Email format is not valid.");
        }

        if (!dto.getPhoneNumber().matches("^\\+\\d{10,14}$")) {
            throw new Exception("Phone number must start with '+' and contain 10 to 14 digits.");
        }

        if(dto.getPassword().length()<8){
            throw new Exception("Password should be 8 or more characters.");
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

    public PendingAirline createAirline(PendingAirlineDto dto) throws Exception {
        if (dto.getAirlinename().isEmpty() ||
                dto.getCountry().isEmpty() ||
                dto.getEmail().isEmpty() ||
                dto.getPhoneNumber().isEmpty() ||
                dto.getPassword().isEmpty() ||
                dto.getConfirmPassword().isEmpty()) {
            throw new Exception("Provided information is not valid!");
        }

        if(dto.getAirlinename().matches("^[A-Z][a-zA-Z]$")){
            throw new Exception("Name should only contain letters, and start with upper case.");
        }
        if (!dto.getEmail().matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$")) {
            throw new Exception("Email format is not valid.");
        }
        if (!dto.getPhoneNumber().matches("^\\+\\d{10,14}$")) {
            throw new Exception("Phone number must start with '+' and contain 10 to 14 digits.");
        }
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new Exception("Password should be the same as Confirm Password");
        }
        if(dto.getPassword().length()<8){
            throw new Exception("Password should be 8 or more characters.");
        }

        Airline existing = airlineRepository.getEmail(dto.getEmail());
        if (existing != null) {
            throw new Exception("Airline email already exists!");
        }

        String salt = PasswordHasher.generateSalt();
        String hashPass = PasswordHasher.generateSaltedHash(dto.getPassword(), salt);

        CreatePendingAirlineDto createPendingAirlineDto = new CreatePendingAirlineDto(
                dto.getAirlinename(),
                dto.getCountry(),
                dto.getEmail(),
                hashPass,
                salt,
                dto.getPhoneNumber()
        );

        PendingAirline pendingAirline = pendingAirlineRepository.create(createPendingAirlineDto);
        if (pendingAirline == null) {
            throw new Exception("Airline is not created!");
        }

        return pendingAirline;
    }
}

