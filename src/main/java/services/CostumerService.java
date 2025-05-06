package services;

import model.Costumer;
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
        if (dto.getFirstName().isEmpty() || dto.getLastName().isEmpty() || dto.getEmail().isEmpty() || dto.getBirthDate() == null) {
            throw new Exception("Provided information is not valid!");
        }

        Costumer costumer = this.costumerRepository.create(dto);
        if (costumer == null) {
            throw new Exception("Customer was not created!");
        }

        return costumer;
    }
}
