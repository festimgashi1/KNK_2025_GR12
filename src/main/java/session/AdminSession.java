package session;

import model.Admin;

public class AdminSession {
    private static AdminSession instance;
    private Admin currentAdmin;

    private AdminSession() {}

    public static AdminSession getInstance() {
        if (instance == null) {
            instance = new AdminSession();
        }
        return instance;
    }

    public void setCurrentAdmin(Admin admin) {
        this.currentAdmin = admin;
    }

    public Admin getCurrentAdmin() {
        return currentAdmin;
    }

    public void clear() {
        this.currentAdmin = null;
    }
}
