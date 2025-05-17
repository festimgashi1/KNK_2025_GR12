package services;

import model.Booking;
import repository.BookingRepository;

public class BookingService {

    private final BookingRepository bookingRepository = new BookingRepository();

    public boolean saveBooking(Booking booking) {
        return bookingRepository.insertBooking(booking);
    }
}
