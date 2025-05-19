package session;

import model.Admin;

public class AdminSession {
    private static AdminSession instance;
    private Admin currentAdmin;
    private static String adminName;

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

    public static String getAdminName() {
        return adminName;
    }

    public void clear() {
        this.currentAdmin = null;
    }
}
