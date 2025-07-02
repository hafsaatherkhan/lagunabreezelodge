package com.lagunabreezelodge.controller;



import com.lagunabreezelodge.controller.*;
import com.lagunabreezelodge.model.Room;
import com.lagunabreezelodge.service.AuthService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.web;

public class RoomListController {
    private VBox hamburgerMenu;
    private boolean isMenuOpen = false;
    private StackPane rootPane;
    private final BorderPane root;

    public RoomListController() {
        root = new BorderPane();
        root.setTop(createHeader());
        root.setCenter(createContent());
        root.setBottom(createFooter());
        root.setStyle("-fx-background-color: #b5ead7;");
        rootPane = new StackPane();
        rootPane.getChildren().add(root);
    }

    public static void show() {
        RoomListController controller = new RoomListController();
        Scene scene = new Scene(controller.getRoot(), 800, 600);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(RoomListController.class.getResourceAsStream("/images/logo.jpg")));
        stage.setTitle("Explore Rooms");
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
    }

    private Node createContent() {
        VBox content = new VBox(20);
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-padding: 30; -fx-background-color: #f3ddbb;");

        // 3-star rooms
        Label head1 = new Label("3-Star Rooms – Comfort with a Coastal Touch");
        head1.setFont(Font.font("PlayFair Display", FontWeight.BOLD, 30));
        head1.setStyle("-fx-text-fill: #26524b;");
        content.getChildren().add(head1);
        content.getChildren().add(createCategorySection(getThreeStarRooms(), getThreeStarFeatures()));

        // 4-star rooms
        Label head2 = new Label("4-Star Rooms – Enhanced Relaxation by the Sea");
        head2.setFont(Font.font("PlayFair Display", FontWeight.BOLD, 30));
        head2.setStyle("-fx-text-fill: #26524b;");
        content.getChildren().add(head2);
        content.getChildren().add(createCategorySection(getFourStarRooms(), getFourStarFeatures()));

        // 5-star rooms
        Label head3 = new Label("5-Star Rooms – Luxury with a Breathtaking Oceanfront Experience");
        head3.setFont(Font.font("PlayFair Display", FontWeight.BOLD, 30));
        head3.setStyle("-fx-text-fill: #26524b;");
        content.getChildren().add(head3);
        content.getChildren().add(createCategorySection(getFiveStarRooms(), getFiveStarFeatures()));

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));
        scrollPane.setStyle("-fx-background: transparent;");
        return scrollPane;
    }

    private List<Room> getThreeStarRooms() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(301, "Standard Room", "3-Star", new BigDecimal("150.00"), "Partial beach view, simple décor, free Wi-Fi", "suite6.jpg", 2));
        rooms.add(new Room(302, "Deluxe Room", "3-Star", new BigDecimal("180.00"), "Private balcony, ocean tones, tea/coffee station", "suite5.jpg", 2));
        rooms.add(new Room(303, "Family Room", "3-Star", new BigDecimal("220.00"), "Spacious, garden view, handcrafted toiletries", "suite3.jpg", 4));
        return rooms;
    }

    private List<Room> getFourStarRooms() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(401, "Superior Room", "4-Star", new BigDecimal("250.00"), "Sea-facing balcony, premium linens, mini-bar", "suite7.jpg", 2));
        rooms.add(new Room(402, "Junior Suite", "4-Star", new BigDecimal("320.00"), "Spa-style bathroom, sun loungers, smart TV", "suite8.jpg", 2));
        rooms.add(new Room(403, "Executive Room", "4-Star", new BigDecimal("350.00"), "Work desk, complimentary Wi-Fi, coastal décor", "suite9.jpg", 2));
        return rooms;
    }

    private List<Room> getFiveStarRooms() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(501, "Luxury Sea View Room", "5-Star", new BigDecimal("450.00"), "Pretty ocean view, infinity bath, butler service", "suite10.jpg", 2));
        rooms.add(new Room(502, "Presidential Suite", "5-Star", new BigDecimal("700.00"), "Private terrace, mood lighting, concierge service", "suite4.jpg", 2));
        rooms.add(new Room(503, "Penthouse Suite", "5-Star", new BigDecimal("900.00"), "Royal beachfront suite, spa access, premium amenities", "suite11.jpeg", 2));
        return rooms;
    }

    private List<String> getThreeStarFeatures() {
        return List.of(
                "Partial beach view or garden view",
                "Private balcony (in deluxe/family rooms)",
                "Simple, breezy décor with ocean tones",
                "Ceiling fan or basic air conditioning",
                "Free Wi-Fi, flat-screen TV",
                "Compact workspace",
                "Tea/coffee station",
                "Local handcrafted toiletries"
        );
    }

    private List<String> getFourStarFeatures() {
        return List.of(
                "Full or partial sea-facing balcony",
                "Coastal-inspired décor (driftwood, shells, nautical accents)",
                "King/queen bed with premium linens",
                "Mini-bar with beach refreshments",
                "Air conditioning with personal climate control",
                "Spa-style bathroom (rain shower, larger vanity)",
                "Work desk, complimentary Wi-Fi",
                "Smart TV with beach lounge music channel",
                "Sun loungers on balcony (in suites)",
                "Room service (12–16 hrs)"
        );
    }

    private List<String> getFiveStarFeatures() {
        return List.of(
                "Full panoramic beach or ocean view",
                "Private balcony or terrace with lounge beds",
                "In-room Jacuzzi or infinity bath",
                "Beach butler service",
                "Beach-themed luxury interiors",
                "Complimentary Nespresso machine, mini wine fridge",
                "Designer bathroom amenities",
                "Free unlimited gym access + spa discount",
                "Personal concierge or butler",
                "Mood lighting, smart curtains, voice-controlled room features",
                "Daily tropical fruit platter and premium welcome drink",
                "24/7 room service"
        );
    }

    private VBox createCategorySection(List<Room> rooms, List<String> features) {
        VBox categoryBox = new VBox(15);
        categoryBox.setMaxWidth(1300);
        categoryBox.setAlignment(Pos.CENTER_LEFT);
        categoryBox.setStyle("""
            -fx-background-color: #e6c8a0;
            -fx-border-color: #4d2919;
            -fx-border-radius: 10;
            -fx-padding: 15;
        """);

        for (Room room : rooms) {
            Label roomCategoryLabel = new Label(room.getName());
            roomCategoryLabel.setFont(Font.font("PlayFair Display SemiBold", 20));
            roomCategoryLabel.setStyle("-fx-text-fill: #26524b;");
            categoryBox.getChildren().add(roomCategoryLabel);
            categoryBox.getChildren().add(createRoomBox(room));
        }

        VBox featuresBox = new VBox(5);
        Label featureTitle = new Label("Room Features:");
        featureTitle.setFont(Font.font("PlayFair Display Bold", 20));
        featureTitle.setStyle("-fx-text-fill: #6e3f0d;");
        featuresBox.getChildren().add(featureTitle);

        for (String feature : features) {
            Label featureLabel = new Label("• " + feature);
            featureLabel.setFont(Font.font("PlayFair Display", 18));
            featureLabel.setStyle("-fx-text-fill: #6e3f0d;");
            featuresBox.getChildren().add(featureLabel);
        }
        categoryBox.getChildren().add(featuresBox);

        return categoryBox;
    }

    private VBox createRoomBox(Room room) {
        VBox roomBox = new VBox(20);
        roomBox.setMaxWidth(1200);
        roomBox.setMaxHeight(400);
        roomBox.setPadding(new Insets(20));
        roomBox.setAlignment(Pos.CENTER);
        roomBox.setStyle("""
            -fx-background-color: #fff8e7;
            -fx-border-color: #caa974;
            -fx-border-radius: 12;
            -fx-background-radius: 12;
            -fx-padding: 20;
        """);

        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.rgb(170, 150, 100, 0.3)); // warm beige-brown tone with some transparency
        shadow.setRadius(12);  // a bit larger radius for smoothness
        shadow.setSpread(0.1); // less spread for softer edges
        shadow.setOffsetX(3);  // subtle horizontal offset
        shadow.setOffsetY(3);  // subtle vertical offset
        roomBox.setEffect(shadow);


        URL imageUrl = getClass().getResource(room.getImagePath());
        ImageView roomImage = new ImageView();
        if (imageUrl != null) {
            roomImage.setImage(new Image(imageUrl.toExternalForm()));
        }

        roomImage.setFitWidth(400);
        roomImage.setFitHeight(250);
        Rectangle clip = new Rectangle(400, 250);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        roomImage.setClip(clip);

        VBox roomDetails = new VBox(10);
        Label idLabel = new Label("Room ID: " + room.getId());
        idLabel.setFont(Font.font("PlayFair Display", 18));
        idLabel.setStyle("-fx-text-fill: #6e3f0d;");

        Label detailsLabel = new Label(room.getRoomDetails());
        detailsLabel.setFont(Font.font("PlayFair Display", 18));
        detailsLabel.setStyle("-fx-text-fill: #6e3f0d;");
        detailsLabel.setWrapText(true);

        Label descriptionLabel = new Label(room.getDescription());
        descriptionLabel.setFont(Font.font("PlayFair Display", 18));
        descriptionLabel.setStyle("-fx-text-fill: #6e3f0d;");

        Button bookNowButton = new Button("Proceed To Booking");
        bookNowButton.setStyle("""
            -fx-background-color: #4d663b;
            -fx-text-fill: white;
            -fx-font-size: 18px;
            -fx-font-family: 'Playfair Display';
            -fx-padding: 10 30 10 20;
            -fx-background-radius: 5;
            -fx-cursor: hand;
            -fx-border-color: transparent;
            -fx-border-width: 1px;
            -fx-border-radius: 5;
        """);

        bookNowButton.setOnMouseEntered(e -> bookNowButton.setStyle("""
            -fx-background-color: #7a8c6d;
            -fx-text-fill: white;
            -fx-font-size: 18px;
            -fx-font-family: 'Playfair Display';
            -fx-padding: 10 30 10 20;
            -fx-background-radius: 5;
            -fx-cursor: hand;
            -fx-border-color: #5a6a4f;
            -fx-border-width: 1px;
            -fx-border-radius: 5;
        """));

        bookNowButton.setOnMouseExited(e -> bookNowButton.setStyle("""
            -fx-background-color: #4d663b;
            -fx-text-fill: white;
            -fx-font-size: 18px;
            -fx-font-family: 'Playfair Display';
            -fx-padding: 10 30 10 20;
            -fx-background-radius: 5;
            -fx-cursor: hand;
            -fx-border-color: transparent;
            -fx-border-width: 1px;
            -fx-border-radius: 5;
        """));

        bookNowButton.setOnMousePressed(e -> bookNowButton.setStyle("""
            -fx-background-color: #6a7b5f;
            -fx-text-fill: white;
            -fx-font-size: 18px;
            -fx-font-family: 'Playfair Display';
            -fx-padding: 10 30 10 20;
            -fx-background-radius: 5;
            -fx-cursor: hand;
            -fx-border-color: #5a6a4f;
            -fx-border-width: 1px;
            -fx-border-radius: 5;
        """));

        bookNowButton.setOnAction(e -> {
            if (AuthService.isLoggedIn()) {
                BookingController.show((Stage) bookNowButton.getScene().getWindow(), room);
            } else {
                LoginController.show();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Required");
                alert.setHeaderText(null);
                alert.setContentText("Please log in to book a room.");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setStyle("-fx-font-family: 'Playfair Display'; -fx-background-color: #E4C590; -fx-border-color: #6e3f0d;");
                Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.jpg")));
                alert.showAndWait();
            }
        });

        HBox topRow = new HBox(20);
        topRow.setAlignment(Pos.CENTER_LEFT);

        VBox rightColumn = new VBox();
        rightColumn.setPrefHeight(250);
        rightColumn.setPrefWidth(700);
        rightColumn.setPadding(new Insets(5, 10, 5, 10));
        rightColumn.setSpacing(10);
        rightColumn.setAlignment(Pos.TOP_LEFT);

        VBox detailsBox = new VBox(8, idLabel, detailsLabel, descriptionLabel);
        detailsBox.setAlignment(Pos.TOP_LEFT);
        detailsBox.setMaxHeight(180);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        HBox buttonBox = new HBox(bookNowButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        rightColumn.getChildren().addAll(detailsBox, spacer, buttonBox);
        topRow.getChildren().addAll(roomImage, rightColumn);
        roomBox.getChildren().add(topRow);

        return roomBox;
    }

    private Node createHeader() {
        HBox header = new HBox();
        header.setPadding(new Insets(10, 20, 10, 20));
        header.setSpacing(10);
        header.setPrefHeight(100);
        header.setStyle("""
            -fx-background-color: #9caf88;
            -fx-border-color: transparent transparent white transparent;
            -fx-border-width: 0 0 1 0;
        """);

        Text title = new Text("✧˖°Laguna Breeze Lodge - Virtual Hotel Room Booking✧˖°");
        title.setFont(Font.font("PlayFair Display", FontWeight.BOLD, 24));
        title.setFill(Color.WHITE);

        Button searchBtn = new Button("\uD83D\uDD0D");
        searchBtn.setFont(Font.font(18));
        searchBtn.setTextFill(Color.WHITE);
        searchBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        searchBtn.setOnMouseEntered(e -> searchBtn.setStyle(
                "-fx-background-color: rgba(255,255,255,0.2); -fx-cursor: hand; -fx-text-fill: white;"));
        searchBtn.setOnMouseExited(e -> searchBtn.setStyle(
                "-fx-background-color: transparent; -fx-cursor: hand; -fx-text-fill: white;"));
        searchBtn.setOnAction(e -> RoomListController.show());

        VBox hamburger = createHamburgerIcon();
        hamburger.setPadding(new Insets(10));
        hamburger.setAlignment(Pos.CENTER);

        for (Node node : hamburger.getChildren()) {
            if (node instanceof Rectangle rect) {
                rect.setFill(Color.WHITE);
                rect.setArcWidth(3);
                rect.setArcHeight(3);
            }
        }

        hamburger.setOnMouseEntered(e -> {
            hamburger.setStyle("-fx-background-color: rgba(255, 255, 255, 0.15); -fx-cursor: hand;");
        });
        hamburger.setOnMouseExited(e -> {
            hamburger.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        });
        hamburger.setOnMouseClicked(e -> toggleHamburgerMenu(hamburger));

        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        header.getChildren().addAll(searchBtn, leftSpacer, title, rightSpacer, hamburger);
        header.setAlignment(Pos.CENTER);
        HBox.setMargin(hamburger, new Insets(0, 0, 0, 0));
        HBox.setMargin(searchBtn, new Insets(0, 18, 0, 0));

        String imagePath = getClass().getResource("/images/header.jpg").toExternalForm();
        String imageBackgroundStyle = "-fx-background-image: url('" + imagePath + "'); " +
                "-fx-background-repeat: no-repeat; " +
                "-fx-background-size: cover; ";

        header.setOnMouseEntered(e -> header.setStyle(imageBackgroundStyle));
        header.setOnMouseExited(e -> header.setStyle("""
            -fx-background-color: #9caf88;
            -fx-border-color: transparent transparent white transparent;
            -fx-border-width: 0 0 1 0;
        """));

        return header;
    }

    private VBox createHamburgerIcon() {
        VBox box = new VBox(5);
        box.setCursor(Cursor.HAND);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(6, 0, 0, 0));

        for (int i = 0; i < 3; i++) {
            Region line = new Region();
            line.setPrefSize(20, 2);
            line.setStyle("-fx-background-color: #fff;");
            box.getChildren().add(line);
        }

        return box;
    }

    private void toggleHamburgerMenu(Node hamburgerIcon) {
        if (isMenuOpen) {
            rootPane.getChildren().remove(hamburgerMenu);
            isMenuOpen = false;
        } else {
            hamburgerMenu = new VBox(10);
            hamburgerMenu.setPadding(new Insets(12));
            hamburgerMenu.setStyle("""
            -fx-background-color: #c5effc;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0.1, 0, 4);
        """);

            hamburgerMenu.setPrefWidth(230);
            hamburgerMenu.setMaxHeight(Region.USE_PREF_SIZE);
            hamburgerMenu.setMaxWidth(Region.USE_PREF_SIZE);

            List<String> menuItems = new ArrayList<>(List.of("Home", "Explore", "Book Now", "FAQs(Bot Buddy Breeze)"));

            if (AuthService.isLoggedIn()) {
                menuItems.add("Booking History");
                menuItems.add("Logout");
            } else {
                menuItems.add("Login");
                menuItems.add("Register");
            }

            for (String itemText : menuItems) {
                Label item = new Label(itemText);
                item.setStyle("""
                -fx-padding: 8 12 8 12;
                -fx-font-family: 'Playfair Display';
                -fx-font-weight: 600; /* For semi-bold */
                -fx-font-size: 14px;
                -fx-text-fill: #26524b;
                -fx-alignment: center-left;
            """);

                // Hover effect
                item.setOnMouseEntered(e -> item.setStyle(item.getStyle() +
                        "-fx-background-color: #e8f9ff; -fx-cursor: hand;"));
                item.setOnMouseExited(e -> item.setStyle("""
                -fx-padding: 8 12 8 12;
                -fx-font-family: 'Playfair Display';
                -fx-font-weight: 600; /* For semi-bold */
                -fx-font-size: 14px;
                -fx-text-fill: #26524b;
                -fx-alignment: center-left;
            """));

                // Actions on click
                item.setOnMouseClicked(e -> {
                    switch (itemText) {
                        case "Home" -> HomeController.show(new Stage());

                        case "Explore" -> RoomListController.show();

                        case "Book Now" -> {
                            if (AuthService.isLoggedIn()) {
                                RoomListController.show();
                            } else {
                                LoginController.show();
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Login Required");
                                alert.setHeaderText(null);
                                alert.setContentText("Please log in to book a room.");
                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.setStyle("-fx-font-family: 'Playfair Display'; -fx-background-color: #E4C590; -fx-border-color: #6e3f0d;");
                                Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                                alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.jpg")));
                                alert.showAndWait();
                            }
                        }

                        case "Booking History" -> {
                            BookingHistoryController.show(new Stage());
                        }

                        case "FAQs(Bot Buddy Breeze)" -> {
                            ChatbotController chatbotController = new ChatbotController();
                            chatbotController.showChatbotWindow();
                        }

                        case "Login" -> LoginController.show();

                        case "Register" -> RegisterController.show();

                        case "Logout" -> {
                            AuthService.logout();
                            toggleHamburgerMenu(hamburgerIcon); // Refresh the menu
                            return; // Exit early so it doesn’t remove hamburger below
                        }
                    }

                    // Close hamburger after action (except logout which reopens)
                    rootPane.getChildren().remove(hamburgerMenu);
                    isMenuOpen = false;
                });

                hamburgerMenu.getChildren().add(item);
            }

            StackPane.setAlignment(hamburgerMenu, Pos.TOP_RIGHT);
            StackPane.setMargin(hamburgerMenu, new Insets(100, 20, 0, 0));
            rootPane.getChildren().add(hamburgerMenu);
            isMenuOpen = true;

            rootPane.setOnMousePressed(e -> {
                if (isMenuOpen &&
                        !hamburgerMenu.getBoundsInParent().contains(e.getX(), e.getY()) &&
                        !hamburgerIcon.getBoundsInParent().contains(e.getX(), e.getY())) {
                    rootPane.getChildren().remove(hamburgerMenu);
                    isMenuOpen = false;
                }
            });
        }
    }


    private Node createFooter() {
        HBox footer = new HBox();
        footer.setPadding(new Insets(15));
        footer.setAlignment(Pos.CENTER);
        footer.setStyle("""
            -fx-background-color: #9caf88;
            -fx-border-color: #ddd;
            -fx-border-width: 1 0 0 0;
        """);

        Label footerText = new Label("© 2025 Laguna Breeze Lodge. All rights reserved.");
        footerText.setFont(Font.font("Playfair Display Bold", 15));
        footerText.setTextFill(Color.WHITE);

        footer.getChildren().add(footerText);
        return footer;
    }

    public Parent getRoot() {
        return rootPane;
    }
}