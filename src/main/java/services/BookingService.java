package services;

import model.dto.CreateBookingDto;
import repository.BookingRepository;

public class BookingService {
    private final BookingRepository repository = new BookingRepository();

    public boolean createBooking(CreateBookingDto dto) {
        return repository.createBooking(dto);
    }
}
