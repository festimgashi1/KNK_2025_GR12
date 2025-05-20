package services;

import model.AirportStaff;
import repository.AirportStaffRepository;

import java.util.List;

public class AirportStaffService {
    private final AirportStaffRepository repo = new AirportStaffRepository();

    public List<AirportStaff> getAllStaff() {
        return repo.getAll();
    }

    public List<AirportStaff> getStaffByShift(String shift) {
        return repo.getByShift(shift);
    }

    public boolean insertStaff(AirportStaff staff) {
        return repo.insert(staff);
    }

    public boolean deleteStaffById(int id) {
        return repo.deleteById(id);
    }
}
