package session;


import model.Costumer;

public class CustomerSession {
    private static CustomerSession instance;
    private Costumer currentCustomer;

    private CustomerSession() {}

    public static CustomerSession getInstance() {
        if (instance == null) {
            instance = new CustomerSession();
        }
        return instance;
    }

    public void setCurrentCustomer(Costumer customer) {
        this.currentCustomer = customer;
    }

    public Costumer getCurrentCustomer() {
        return currentCustomer;
    }

    public void clear() {
        this.currentCustomer = null;
    }


}
