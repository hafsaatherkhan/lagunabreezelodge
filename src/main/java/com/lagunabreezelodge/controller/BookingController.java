
 package com.lagunabreezelodge.controller;

import com.lagunabreezelodge.db.dao.BookingDAO;
import com.lagunabreezelodge.db.dao.impl.BookingDAOImpl;
import com.lagunabreezelodge.model.Booking;
import com.lagunabreezelodge.model.Room;
import com.lagunabreezelodge.service.AuthService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

 public class BookingController {
    private final Room room;
    private final BookingDAO bookingDAO;

    public BookingController(Room room) {
        this.room = room;
        try {
            this.bookingDAO = new BookingDAOImpl();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize BookingDAO", e);
        }
    }

    public Scene getBookingScene() {
        VBox rootLayout = new VBox(30);
        rootLayout.setPadding(new Insets(40));
        rootLayout.setAlignment(Pos.CENTER);
        rootLayout.setStyle("-fx-background-color: #e6c8a0;");

        Text titleLabel = new Text("✧˖°BOOKING FORM✧˖°");
        titleLabel.setFont(Font.font("Playfair Display", FontWeight.BOLD, 35));
        titleLabel.setFill(Color.web("#26524b")); // Tropical teal

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setHgap(15);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);

        TextField nameField = createStyledTextField();
        TextField roomField = createStyledTextField();
        roomField.setText(String.valueOf(room.getId()));
        roomField.setEditable(false);

        Spinner<Integer> guestsSpinner = new Spinner<>(1, 10, 1);
        guestsSpinner.setStyle("-fx-font-family: 'Playfair Display'; -fx-font-size: 16px;");

        DatePicker checkInDate = new DatePicker(LocalDate.now());
        DatePicker checkOutDate = new DatePicker(LocalDate.now().plusDays(1));
        List<Booking> bookings = bookingDAO.getBookingsByRoomId(room.getId());
        checkInDate.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) return;

                boolean isBooked = bookings.stream().anyMatch(b ->
                        !date.isBefore(b.getCheckInDate()) && date.isBefore(b.getCheckOutDate())
                );

                if (isBooked) {
                    setDisable(true);
                    setTextFill(Color.RED); //  Mark booked dates
                }
            }
        });

        checkOutDate.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) return;

                boolean isBooked = bookings.stream().anyMatch(b ->
                        !date.isBefore(b.getCheckInDate()) && date.isBefore(b.getCheckOutDate())
                );

                if (isBooked) {
                    setDisable(true);
                    setTextFill(Color.RED); //  Mark booked dates
                }
            }
        });

        addToGrid(grid, "Name:", nameField, 0);
        addToGrid(grid, "Room ID:", roomField, 1);
        addToGrid(grid, "Guests:", guestsSpinner, 2);
        addToGrid(grid, "Check-in:", checkInDate, 3);
        addToGrid(grid, "Check-out:", checkOutDate, 4);

        VBox whiteBox = new VBox(grid);
        whiteBox.setAlignment(Pos.CENTER);
        whiteBox.setPadding(new Insets(30));
        whiteBox.setStyle(
                "-fx-background-color: #f3ddbb;" + // Light cream
                        "-fx-background-radius: 15;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 10, 0, 0, 4);"
        );

        Button bookButton = new Button("Book Now");

        // ✅ KEEPING YOUR BUTTON STYLES AS THEY WERE
        bookButton.setStyle(
                "-fx-background-color: #4d663b;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-family: 'Playfair Display';" +
                        "-fx-padding: 10 20 10 20;" +
                        "-fx-background-radius: 5;" +
                        "-fx-cursor: hand;" +
                        "-fx-border-color: transparent;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 5;"
        );
        bookButton.setOnMouseEntered(e -> bookButton.setStyle(
                "-fx-background-color: #7a8c6d;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-family: 'Playfair Display';" +
                        "-fx-padding: 10 20 10 20;" +
                        "-fx-background-radius: 5;" +
                        "-fx-cursor: hand;" +
                        "-fx-border-color: #5a6a4f;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 5;"
        ));
        bookButton.setOnMouseExited(e -> bookButton.setStyle(
                "-fx-background-color: #4d663b;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-family: 'Playfair Display';" +
                        "-fx-padding: 10 20 10 20;" +
                        "-fx-background-radius: 5;" +
                        "-fx-cursor: hand;" +
                        "-fx-border-color: transparent;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 5;"
        ));

        bookButton.setOnAction(e -> handleBooking(nameField, roomField, guestsSpinner, checkInDate, checkOutDate));

        VBox outer = new VBox(20, titleLabel, whiteBox, bookButton);
        outer.setAlignment(Pos.CENTER);
        outer.setPadding(new Insets(40));
        outer.setStyle("-fx-background-color: #e6c8a0;");

        return new Scene(outer, 600, 600);
    }

    private void addToGrid(GridPane grid, String labelText, Control input, int row) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Playfair Display", FontWeight.SEMI_BOLD, 18));
        label.setTextFill(Color.web("#6e3f0d"));
        grid.add(label, 0, row);
        grid.add(input, 1, row);
    }

    private TextField createStyledTextField() {
        TextField tf = new TextField();
        tf.setPrefWidth(250);
        tf.setStyle("-fx-background-color: #ffffff; -fx-border-color: #6e3f0d; -fx-border-radius: 10; -fx-font-size: 16px; -fx-padding: 10;");
        return tf;
    }

    private void stylePrimaryButton(Button button) {
        button.setStyle("-fx-background-color: #4d663b; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-family: 'Playfair Display'; -fx-padding: 10 20; -fx-background-radius: 8; -fx-cursor: hand;");

        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #7a8c6d; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-family: 'Playfair Display'; -fx-padding: 10 20; -fx-background-radius: 8; -fx-cursor: hand;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #4d663b; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-family: 'Playfair Display'; -fx-padding: 10 20; -fx-background-radius: 8; -fx-cursor: hand;"));
    }

    private void handleBooking(TextField nameField, TextField roomField, Spinner<Integer> guestsSpinner, DatePicker checkIn, DatePicker checkOut) {
        if (nameField.getText().isEmpty()) {
            showAlert("Error", "Name cannot be empty.");
            return;
        }
        if (checkIn.getValue().isAfter(checkOut.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Check-out must be after check-in.");

            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setStyle("-fx-font-family: 'Playfair Display'; -fx-background-color: #E4C590; -fx-border-color: #6e3f0d;");

            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.jpg")));

            alert.showAndWait();
            return;
        }

        Booking booking = new Booking(
                AuthService.getLoggedInUser().getId(),
                room.getId(),
                checkIn.getValue(),
                checkOut.getValue(),
                guestsSpinner.getValue(),
                calculateTotalPrice(guestsSpinner.getValue(), checkIn.getValue(), checkOut.getValue())
        );

        // Instead of saving immediately, open confirmation dialog
        openCancelBookingWindow(booking);
    }

    private void openCancelBookingWindow(Booking booking) {
        Stage cancelStage = new Stage();
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #e6c8a0;");

        Label label = new Label("Are you sure you want to confirm your booking?");
        label.setStyle("-fx-font-family: 'Playfair Display'; -fx-font-size: 16px; -fx-font-weight: 600; -fx-text-fill: #6e3f0d;");
        label.setWrapText(true);
        label.setMaxWidth(400);

        Button confirmButton = new Button("Confirm Booking");
        Button cancelButton = new Button("Cancel");

        // Confirm button styling & hover effects
        confirmButton.setStyle(
                "-fx-background-color: #4d663b; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 18px; " +
                        "-fx-font-family: 'Playfair Display'; " +
                        "-fx-padding: 10 20 10 20; " +
                        "-fx-background-radius: 5; " +
                        "-fx-cursor: hand;"
        );
        confirmButton.setOnMouseEntered(e -> confirmButton.setStyle(
                "-fx-background-color: #7a8c6d; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 18px; " +
                        "-fx-font-family: 'Playfair Display'; " +
                        "-fx-padding: 10 20 10 20; " +
                        "-fx-background-radius: 5; " +
                        "-fx-cursor: hand; " +
                        "-fx-border-color: #5a6a4f; " +
                        "-fx-border-width: 1px; " +
                        "-fx-border-radius: 5;"
        ));
        confirmButton.setOnMouseExited(e -> confirmButton.setStyle(
                "-fx-background-color: #4d663b; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 18px; " +
                        "-fx-font-family: 'Playfair Display'; " +
                        "-fx-padding: 10 20 10 20; " +
                        "-fx-background-radius: 5; " +
                        "-fx-cursor: hand;"
        ));

        // Confirm booking action
        confirmButton.setOnAction(e -> {
            boolean success = bookingDAO.addBooking(booking);
            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Booking Confirmed");
                alert.setHeaderText(null);
                alert.setContentText("Your booking has been successfully created!");

                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setStyle("-fx-font-family: 'Playfair Display'; -fx-background-color: #E4C590; -fx-border-color: #6e3f0d;");

                Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.jpg")));

                alert.showAndWait();

                CancelTimerController.scheduleCancellationNotification(booking.getRoomId());
                cancelStage.close();
                RoomListController.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Database Error");
                alert.setHeaderText(null);
                alert.setContentText("Booking could not be saved.");

                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setStyle("-fx-font-family: 'Playfair Display'; -fx-background-color: #E4C590; -fx-border-color: #6e3f0d;");

                Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.jpg")));

                alert.showAndWait();
            }
        });

        // Cancel button styling & hover effects
        cancelButton.setStyle(
                "-fx-background-color: #d9534f; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 18px; " +
                        "-fx-font-family: 'Playfair Display'; " +
                        "-fx-padding: 10 20 10 20; " +
                        "-fx-background-radius: 5; " +
                        "-fx-cursor: hand;"
        );
        cancelButton.setOnMouseEntered(e -> cancelButton.setStyle(
                "-fx-background-color: #c9302c; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 18px; " +
                        "-fx-font-family: 'Playfair Display'; " +
                        "-fx-padding: 10 20 10 20; " +
                        "-fx-background-radius: 5; " +
                        "-fx-cursor: hand; " +
                        "-fx-border-color: #5a6a4f; " +
                        "-fx-border-width: 1px; " +
                        "-fx-border-radius: 5;"
        ));
        cancelButton.setOnMouseExited(e -> cancelButton.setStyle(
                "-fx-background-color:#d9534f ; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 18px; " +
                        "-fx-font-family: 'Playfair Display'; " +
                        "-fx-padding: 10 20 10 20; " +
                        "-fx-background-radius: 5; " +
                        "-fx-cursor: hand;"
        ));

        cancelButton.setOnAction(e -> {
            cancelStage.close();
            RoomListController.show();
        });

        HBox buttonBox = new HBox(20, cancelButton, confirmButton);
        buttonBox.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(label, buttonBox);
        Scene scene = new Scene(layout, 400, 200);
        cancelStage.getIcons().add(new Image(BookingController.class.getResourceAsStream("/images/logo.jpg")));
        cancelStage.setTitle("Confirm Booking");
        cancelStage.setScene(scene);
        cancelStage.setResizable(false);
        cancelStage.show();
    }


    private double calculateTotalPrice(int guests, LocalDate in, LocalDate out) {
        return guests * 100 * (out.toEpochDay() - in.toEpochDay());
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void show(Stage stage, Room room) {
        BookingController controller = new BookingController(room);
        Scene scene = controller.getBookingScene();
        stage.setScene(scene);
        stage.setTitle("Booking - " + room.getName());
        stage.getIcons().add(new Image(BookingController.class.getResourceAsStream("/images/logo.jpg")));
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }
}