package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

    public class Booking {
        private int bookingId;
        private int costumerId;
        private int flightNumber;
        private Date dataeNisjes;
        private String destinacioni;
        private double cmimi;
        private int seatId;

        protected Booking(int bookingId, int costumerId, int flightNumber, Date dataeNisjes, String destinacioni, double cmimi, int seatId) {
            this.bookingId = bookingId;
            this.costumerId = costumerId;
            this.flightNumber = flightNumber;
            this.dataeNisjes = dataeNisjes;
            this.destinacioni = destinacioni;
            this.cmimi = cmimi;
            this.seatId = seatId;
        }

        public static Booking getInstance(ResultSet result) throws SQLException {
            int bookingId = result.getInt("bookingId");
            int costumerId = result.getInt("costumerId");
            int flightNumber = result.getInt("flightNumber");
            Date dataeNisjes = result.getDate("dataeNisjes");
            String destinacioni = result.getString("destinacioni");
            double cmimi = result.getDouble("cmimi");
            int seatId = result.getInt("seatId");

            return new Booking(bookingId, costumerId, flightNumber, dataeNisjes, destinacioni, cmimi, seatId);
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

        public Date getDataeNisjes() {
            return dataeNisjes;
        }

        public String getDestinacioni() {
            return destinacioni;
        }

        public double getCmimi() {
            return cmimi;
        }

        public int getSeatId() {
            return seatId;
        }
    }
