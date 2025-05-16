package services;

import model.Costumer;
import repository.CostumerRepository;
import session.CustomerSession;

public class CustomerService {

    private final CostumerRepository repo = new CostumerRepository();

    public boolean changePassword(String current, String newPass) {
        Costumer costumer = CustomerSession.getInstance().getCurrentCostumer();
        if (costumer == null) return false;

        return repo.updatePassword(costumer.getCostumerId(), current, newPass);
    }
}
