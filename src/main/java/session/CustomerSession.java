package session;

import model.Costumer;



public class CustomerSession {
    private static CustomerSession instance;
    private Costumer currentCostumer;

    private CustomerSession() {}

    public static CustomerSession getInstance() {
        if (instance == null) {
            instance = new CustomerSession();
        }
        return instance;
    }

    public void setCurrentCostumer(Costumer costumer) {
        this.currentCostumer = costumer;
    }

    public Costumer getCurrentCostumer() {
        return currentCostumer;
    }

    public void clear() {
        this.currentCostumer = null;
    }

}

