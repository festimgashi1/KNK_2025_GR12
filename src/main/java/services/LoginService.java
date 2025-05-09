package services;

import model.Admin;
import model.Airline;
import model.Costumer;
import repository.AdminRepository;
import repository.AirlineRepository;
import repository.CostumerRepository;

public class LoginService {
    private final CostumerRepository costumerRepository = new CostumerRepository();
    private final AdminRepository adminRepository = new AdminRepository();
    private final AirlineRepository airlineRepository = new AirlineRepository();

    public Object login(String email, String password) throws Exception {
        if (email.isEmpty() || password.isEmpty()) {
            throw new Exception("Email or password cannot be empty.");
        }

        Costumer customer = this.costumerRepository.getByEmail(email);
        if (customer != null &&
                PasswordHasher.compareSaltedHash(password, customer.getSalt(), customer.getPassword())) {
            return customer;
        }

        Admin admin = adminRepository.getEmail(email);
        if (admin != null &&
                PasswordHasher.compareSaltedHash(password, admin.getSalt(), admin.getHashpass())) {
            return admin;
        }

        Airline airline = airlineRepository.getEmail(email);
        if (airline != null &&
                PasswordHasher.compareSaltedHash(password, airline.getSalt(), airline.getPassword())) {
            return airline;
        }

        throw new Exception("Invalid email or password.");
    }
}
