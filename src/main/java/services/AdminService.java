package services;

import model.Admin;
import repository.AdminRepository;
import session.AdminSession;

public class AdminService {

    private final AdminRepository repo = new AdminRepository();

    public boolean changePassword(String current, String newPass) {
        Admin currentAdmin = AdminSession.getInstance().getCurrentAdmin();
        if (currentAdmin == null) return false;

        int adminId = currentAdmin.getId();
        return repo.updatePassword(adminId, current, newPass);
    }
}
