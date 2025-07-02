package com.lagunabreezelodge.controller;

import com.lagunabreezelodge.db.dao.BookingDAO;
import com.lagunabreezelodge.db.dao.impl.BookingDAOImpl;
import com.lagunabreezelodge.model.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class AllBookingHistoryController {

    private BookingDAO bookingDAO;

    public AllBookingHistoryController() {
        try {
            bookingDAO = new BookingDAOImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show(Stage stage) {
        AllBookingHistoryController controller = new AllBookingHistoryController();
        Scene scene = controller.getAllBookingsScene();
        stage.setScene(scene);

        stage.getIcons().add(new Image(AllBookingHistoryController.class.getResourceAsStream("/images/logo.jpg")));
        stage.setTitle("All Bookings - Admin View");
        stage.show();
    }

    public Scene getAllBookingsScene() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #e6c8a0;");

        Label title = new Label("✧˖°ALL BOOKINGS✧˖°");
        title.setFont(Font.font("Playfair Display", FontWeight.BOLD, 28));
        title.setTextFill(Color.web("#26524b"));

        TableView<Booking> table = new TableView<>();
        table.setItems(getAllBookings());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Booking, Integer> userCol = new TableColumn<>("User ID");
        userCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userCol.setStyle("-fx-alignment: CENTER;");

        TableColumn<Booking, Integer> roomCol = new TableColumn<>("Room ID");
        roomCol.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        roomCol.setStyle("-fx-alignment: CENTER;");

        TableColumn<Booking, Integer> guestsCol = new TableColumn<>("Guests");
        guestsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfGuests"));
        guestsCol.setStyle("-fx-alignment: CENTER;");

        TableColumn<Booking, LocalDate> checkInCol = new TableColumn<>("Check-in");
        checkInCol.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        checkInCol.setStyle("-fx-alignment: CENTER;");

        TableColumn<Booking, LocalDate> checkOutCol = new TableColumn<>("Check-out");
        checkOutCol.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        checkOutCol.setStyle("-fx-alignment: CENTER;");

        TableColumn<Booking, Double> priceCol = new TableColumn<>("Total Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        priceCol.setStyle("-fx-alignment: CENTER;");

        table.getColumns().addAll(userCol, roomCol, guestsCol, checkInCol, checkOutCol, priceCol);

        layout.getChildren().addAll(title, table);

        return new Scene(layout, 900, 500);
    }

    private ObservableList<Booking> getAllBookings() {
        List<Booking> bookings = bookingDAO.getAllBookings(); // You must implement this in DAO
        return FXCollections.observableArrayList(bookings);
    }
}
