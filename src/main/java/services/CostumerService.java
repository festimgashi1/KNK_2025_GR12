package services;

import model.Costumer;
import model.dto.CostumerDto;
import model.dto.CreateCostumerDto;
import repository.CostumerRepository;

public class CostumerService {
    private CostumerRepository costumerRepository;

    public CostumerService() {
        this.costumerRepository = new CostumerRepository();
    }

    public Costumer getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID is not valid!");
        }

        Costumer costumer = this.costumerRepository.getById(id);
        if (costumer == null) {
            throw new Exception("No customer found with ID: " + id);
        }

        return costumer;
    }

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
        if(!(dto.getPassword().equals(dto.getConfirmPassword()))){
            throw new Exception("Password should be the same as Confirm Password");
        }

        Costumer costumerExist = this.costumerRepository.getByEmail(dto.getEmail());
        if (costumerExist != null) {
            throw new Exception("Email already exists!");
        }

        String salt=PasswordHasher.generateSalt();
        String hashPass=PasswordHasher.generateSaltedHash(dto.getPassword(),salt);
        // duhet me bo ni funksion qe ta kthen id e adrese nga adresa edhe me ja qu si parameter te dto e re
        CostumerDto costumerDto=new CostumerDto(dto.getFirstName(),
                dto.getLastName(), dto.getEmail(), dto.getAddress(),dto.getBirthDate(), salt,hashPass, dto.getPhoneNumber());


        Costumer costumer =this.costumerRepository.create(costumerDto);
        if(costumer == null){
            throw new Exception("User is not created!");
        }

        return costumer;
    }
}
