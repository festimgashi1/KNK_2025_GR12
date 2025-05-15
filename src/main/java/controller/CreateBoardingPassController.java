//package controller;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.DatePicker;
//import javafx.scene.control.TextField;
//import model.BoardingPass;
//import model.dto.CreateBoardingPassDto;
//import services.BoardingPassService;
//import services.SceneManager;
//
//public class CreateBoardingPassController {
//
//    @FXML private TextField txtname;
//    @FXML private TextField txtFrom;
//    @FXML private TextField txtTo;
//    @FXML private DatePicker dpDate;
//    @FXML private TextField txtGate;
//    @FXML private TextField txtTime;
//    @FXML private TextField txtSeat;
//
//    private final BoardingPassService reservationService;
//
//    public CreateBoardingPassController() {
//        this.reservationService = new BoardingPassService();
//    }
//
//    @FXML
//    private void handleCancelClick() {
//        clearFields();
//    }
//
//    @FXML
//    private void handleSaveClick() {
//        try {
//            CreateBoardingPassDto dto = getReservationInput();
//            BoardingPass reservation = reservationService.create(dto);
//            System.out.println("Reservation inserted successfully!");
//            System.out.println("Reservation ID: " + reservation.getReservationId());
//
//            ReservationHolder.set(reservation);
//
//            SceneManager.load(SceneLocator.BOARDING_PASS_PAGE);
//        } catch (Exception e) {
//            System.out.println("Error inserting reservation: " + e.getMessage());
//        }
//    }
//
//    private CreateBoardingPassDto getReservationInput() {
//        return new CreateBoardingPassDto(
//                txtname.getText(),
//                txtFrom.getText(),
//                txtTo.getText(),
//                dpDate.getValue(),
//                "FL123",  // Hardcoded flight code - you can replace with user input later
//                txtGate.getText(),
//                txtTime.getText(),
//                txtSeat.getText()
//        );
//    }
//
//    private void clearFields() {
//        txtname.clear();
//        txtFrom.clear();
//        txtTo.clear();
//        dpDate.setValue(null);
//        txtGate.clear();
//        txtTime.clear();
//        txtSeat.clear();
//    }
//
//    public static class ReservationHolder {
//        private static BoardingPass reservation;
//
//        public static void set(BoardingPass r) {
//            reservation = r;
//        }
//
//        public static BoardingPass get() {
//            return reservation;
//        }
//
//        public static void clear() {
//            reservation = null;
//        }
//    }
//}
