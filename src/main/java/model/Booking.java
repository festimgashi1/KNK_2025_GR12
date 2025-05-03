package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

    public class Booking {
        private int bookingId;
        private int costumerId;
        private int flightNumber;
        private Date departureDate;
        private String destination;
        private double price;
        private int seatId;

        protected Booking(int bookingId, int costumerId, int flightNumber, Date departureDate, String destination, double price, int seatId) {
            this.bookingId = bookingId;
            this.costumerId = costumerId;
            this.flightNumber = flightNumber;
            this.departureDate = departureDate;
            this.destination = destination;
            this.price = price;
            this.seatId = seatId;
        }

        public static Booking getInstance(ResultSet result) throws SQLException {
            int bookingId = result.getInt("bookingId");
            int costumerId = result.getInt("costumerId");
            int flightNumber = result.getInt("flightNumber");
            Date departureDate = result.getDate("departureDate");
            String destination = result.getString("destination");
            double price = result.getDouble("price");
            int seatId = result.getInt("seatId");

            return new Booking(bookingId, costumerId, flightNumber, departureDate, destination, price, seatId);
        }

        public int getBookingId() {
            return bookingId;
        }

        public int getCostumerId() {
            return costumerId;
        }

        public int getFlightNumber() {
            return flightNumber;
        }

        public Date getDepartureDate() {
            return departureDate;
        }

        public String getDestination() {
            return destination;
        }

        public double getPrice() {
            return price;
        }

        public int getSeatId() {
            return seatId;
        }
    }
