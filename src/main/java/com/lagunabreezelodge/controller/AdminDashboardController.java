package com.lagunabreezelodge.controller;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.util.Duration;


public class AdminDashboardController {
    private StackPane rootPane;       //  root container to add dropdown
    private VBox hamburgerMenu;       // dropdown menu
    private boolean isMenuOpen = false;

 /*   public static void show(Stage stage) {
        AdminDashboardController controller = new AdminDashboardController();
        controller.showFullScreen(stage);
    }

*/

    public void show(Stage stage) {
        Parent view = getView();
        Scene scene = new Scene(view, 900, 700); // initial size won't matter if fullscreen
        //   System.out.println("Scene Width: " + stage.getScene().getWidth());
        stage.setScene(scene);
        stage.getIcons().add(new Image(AdminDashboardController.class.getResourceAsStream("/images/logo.jpg")));


        stage.setTitle("Admin Dashboard");
        stage.show();
        stage.setMaximized(true);
    }



    public Parent getView() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #e6c8a0;");

        //  custom header instead of plain Label
        root.setTop(createHeader());

        // Logo ImageView
        ImageView logoView = new ImageView(new Image(getClass().getResourceAsStream("/images/logo.jpg")));
        logoView.setFitWidth(180);
        logoView.setPreserveRatio(true);

        VBox logoBox = new VBox(logoView);
        logoBox.setStyle("-fx-padding: 30 10 0 30; -fx-alignment: top-left;");

        Label welcomeLabel = new Label("Welcome✧˖°");
        welcomeLabel.setFont(Font.font("PlayFair Display", FontWeight.BOLD, 35));
        welcomeLabel.setTextFill(Color.web("#336961"));


// Line with zero length initially
        Line line = new Line(0, 0, 0, 0);
        line.setStroke(Color.web("#336961"));
        line.setStrokeWidth(3);

// Put welcome text and line stacked vertically
        VBox welcomeBox = new VBox(5, welcomeLabel, line);  // 5 px spacing between text and line
        welcomeBox.setAlignment(Pos.TOP_LEFT);
        welcomeBox.setPadding(new Insets(150, 0, 0, 0)); // Adjust padding to vertically position nicely

// Layout HBox with logo and welcomeBox side by side
        HBox layoutBox = new HBox(logoBox, welcomeBox);
        layoutBox.setStyle("-fx-alignment: top-left; -fx-spacing: 20;");

        root.setCenter(layoutBox);

        // Footer
        root.setBottom(createFooter());

        root.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.windowProperty().addListener((obsWin, oldWin, newWin) -> {
                    if (newWin != null) {
                        newWin.showingProperty().addListener((obsShow, wasShowing, isShowing) -> {
                            if (isShowing) {
                                double width = rootPane.getWidth();
                                Timeline timeline = new Timeline(
                                        new KeyFrame(Duration.ZERO, new KeyValue(line.endXProperty(), 0)),
                                        new KeyFrame(Duration.seconds(1.2), new KeyValue(line.endXProperty(), width - 5))
                                );
                                timeline.play();
                            }
                        });
                    }
                });
            }
        });



        Button viewBookingsBtn = createUnicodeButton("\uD83D\uDD0D", "View All Bookings", "#73c4c7"); // 🔍
        viewBookingsBtn.setOnAction(e -> {
            AllBookingHistoryController.show(new Stage());
        });

        Button setRoomStatusBtn = createUnicodeButton("\uD83D\uDCCB", "View Users", "#91c4be");
        setRoomStatusBtn.setOnAction(e -> {
        AllRegisteredUsersViewerController.show();
        });
        Button assignAdminBtn = createUnicodeButton("\uD83D\uDC64", "Admin Registration", "#84c4b3");
        assignAdminBtn.setOnAction(e -> {
            // Create popup stage
            Stage popup = new Stage();
            popup.setTitle("Assign Role");
            popup.setResizable(false);
            popup.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.jpg")));

            // Checkbox
            CheckBox roleCheckBox = new CheckBox("Make Admin?");
            roleCheckBox.setSelected(false);

            // Label
            Label label = new Label("Assign role:");
            label.setFont(Font.font("Playfair Display", FontWeight.SEMI_BOLD, 14));
            roleCheckBox.setFont(Font.font("Playfair Display", 13));

            // Button
            Button continueBtn = new Button("Continue to Register");
            continueBtn.setFont(Font.font("Playfair Display", 14));
            continueBtn.setStyle(
                    "-fx-background-color: #4d663b;" +
                            "-fx-text-fill: white;" +
                            "-fx-padding: 8 16;" +
                            "-fx-background-radius: 5;"
            );
            continueBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    continueBtn.setStyle(
                            "-fx-background-color: #7a8c6d; " +
                                    "-fx-text-fill: white;" +
                                    "-fx-padding: 8 16;" +
                                    "-fx-background-radius: 5;"
                    );
                }
            });

            continueBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    continueBtn.setStyle(
                            "-fx-background-color: #4d663b; " +
                                    "-fx-text-fill: white;" +
                                    "-fx-padding: 8 16;" +
                                    "-fx-background-radius: 5;"
                    );
                }
            });
            // Layout
            VBox layout = new VBox(15, label, roleCheckBox, continueBtn);
            layout.setAlignment(Pos.CENTER);
            layout.setPadding(new Insets(20));
            layout.setStyle("-fx-background-color: #E4C590; -fx-border-color: #6e3f0d; -fx-border-width: 2px;");

            // Scene
            Scene scene = new Scene(layout, 280, 180);
            popup.setScene(scene);
            popup.show();

            // Button action
            continueBtn.setOnAction(event -> {
                popup.close();
                if (roleCheckBox.isSelected()) {
                    AdminRegController.show(); // Show admin registration form
                } else {
                    RegisterController.show(); // Show regular user registration form
                }
            });
        });




        HBox buttonBox = new HBox(30, viewBookingsBtn, setRoomStatusBtn, assignAdminBtn);
        buttonBox.setPadding(new Insets(40));
        buttonBox.setAlignment(Pos.CENTER);

        VBox mainContent = new VBox(50, layoutBox, buttonBox);
        mainContent.setPadding(new Insets(0, 20, 40, 20));

        root.setCenter(mainContent);
        root.setBottom(createFooter());
        rootPane = new StackPane();
        rootPane.getChildren().add(root);

        return rootPane;
    }
    // Helper method to create a styled button with icon and custom background color
    private Button createUnicodeButton(String unicodeSymbol, String text, String bgColor) {
        // Icon as Text
        Text iconText = new Text(unicodeSymbol);
        iconText.setFont(Font.font("Segoe UI Emoji", FontWeight.BOLD, 45)); // Large emoji
        iconText.setFill(Color.web("#336961"));

        // Label text styled like the title
        Text labelText = new Text(text);
        labelText.setFont(Font.font("Playfair Display", FontWeight.BOLD, 25));
        labelText.setFill(Color.web("#26524b"));

        VBox content = new VBox(10, iconText, labelText);
        content.setAlignment(Pos.CENTER);

        Button btn = new Button();
        btn.setGraphic(content);
        btn.setPrefSize(300, 300);
        btn.setStyle(
                "-fx-background-color: " + bgColor + ";" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-border-color: #557755;" +
                        "-fx-border-width: 2px;" +
                        "-fx-cursor: hand;"
        );

        // Hover
        btn.setOnMouseEntered(e -> btn.setStyle(
                "-fx-background-color: derive(" + bgColor + ", -10%);" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-border-color: #4f6b4f;" +
                        "-fx-border-width: 2px;" +
                        "-fx-cursor: hand;"
        ));

        // Pressed
        btn.setOnMousePressed(e -> btn.setStyle(
                "-fx-background-color: derive(" + bgColor + ", 30%);" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-border-color: #557755;" +
                        "-fx-border-width: 2px;" +
                        "-fx-cursor: hand;"
        ));

        // Exit
        btn.setOnMouseExited(e -> btn.setStyle(
                "-fx-background-color: " + bgColor + ";" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-border-color: #557755;" +
                        "-fx-border-width: 2px;" +
                        "-fx-cursor: hand;"
        ));
        btn.setOnMouseReleased(e -> btn.setStyle(
                "-fx-background-color: " + bgColor + ";" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-border-color: #557755;" +
                        "-fx-border-width: 2px;" +
                        "-fx-cursor: hand;"
        ));

        return btn;
    }


    private Node createHeader() {
        HBox header = new HBox();
        header.setPadding(new Insets(10, 20, 10, 20));
        header.setSpacing(10);

        header.setPrefHeight(100);

        // Paths and styles
        String imagePath = getClass().getResource("/images/header.jpg").toExternalForm();
        String solidColorStyle =
                "-fx-background-color: #9caf88; " +  // Sage green hex code
                        "-fx-border-color: transparent transparent white transparent; " +
                        "-fx-border-width: 0 0 1 0;";
        String imageBackgroundStyle =
                "-fx-background-image: url('" + imagePath + "'); " +
                        "-fx-background-repeat: no-repeat; " +
                        "-fx-background-size: cover; " +
                        "-fx-border-color: transparent transparent white transparent; " +
                        "-fx-border-width: 0 0 1 0;";

        // Initially set solid color background
        header.setStyle(solidColorStyle);

        // Create Title
        Text title = new Text("✧˖°Laguna Breeze Lodge - Virtual Hotel Room Booking✧˖°");
        title.setFont(Font.font("PlayFair Display", FontWeight.BOLD, 24));
        title.setFill(Color.web("#fff"));





// Create the hamburger icon
        VBox hamburger = createHamburgerIcon();
        hamburger.setPadding(new Insets(10));
        hamburger.setAlignment(Pos.CENTER);

// Ensure all rectangles inside the hamburger are white
        for (Node node : hamburger.getChildren()) {
            if (node instanceof Rectangle rect) {
                rect.setFill(Color.WHITE); // Make each line white
                rect.setArcWidth(3);       // Optional: rounded edges
                rect.setArcHeight(3);
            }
        }

// Hover effect: subtle white background overlay
        hamburger.setOnMouseEntered(e -> {
            hamburger.setStyle("-fx-background-color: rgba(255, 255, 255, 0.15); -fx-cursor: hand;");
        });
        hamburger.setOnMouseExited(e -> {
            hamburger.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        });

// Click action
        hamburger.setOnMouseClicked(e -> toggleHamburgerMenu(hamburger));



        // Create spacers
        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);


        // Final layout: search on left, title centered, hamburger on right

        header.getChildren().addAll(leftSpacer, title, rightSpacer, hamburger);

        header.setAlignment(Pos.CENTER); // Ensures all elements vertically aligned
        HBox.setMargin(hamburger, new Insets(0, 0, 0, 0));
        HBox.setMargin(title, new Insets(0, 0, 0, 20)); // 20 px left margin



        // Add hover effect: on mouse enter, set background image, on exit revert to solid color
        header.setOnMouseEntered(e -> header.setStyle(imageBackgroundStyle));
        header.setOnMouseExited(e -> header.setStyle(solidColorStyle));

        return header;

    }
    private Node createFooter() {
        HBox footer = new HBox();
        footer.setPadding(new Insets(15));
        footer.setAlignment(Pos.CENTER);
        footer.setStyle("-fx-background-color: #9caf88; -fx-border-color: #ddd; -fx-border-width: 1 0 0 0;");

        Label footerText = new Label("© 2025 Laguna Breeze Lodge. All rights reserved.");
        footerText.setFont(Font.font("Playfair Display", FontWeight.BOLD, 15));
        footerText.setTextFill(Color.web("#fff"));

        footer.getChildren().add(footerText);
        return footer;
    }

    private VBox createHamburgerIcon() {
        VBox box = new VBox(5);
        for (int i = 0; i < 3; i++) {
            Rectangle rect = new Rectangle(25, 3);
            rect.setArcWidth(3);
            rect.setArcHeight(3);
            rect.setFill(Color.WHITE);
            box.getChildren().add(rect);
        }
        return box;
    }

    private void toggleHamburgerMenu(Node hamburgerIcon) {
        if (isMenuOpen) {
            rootPane.getChildren().remove(hamburgerMenu);
            isMenuOpen = false;
        } else {
            hamburgerMenu = new VBox();
            hamburgerMenu.setPadding(new Insets(10));
            hamburgerMenu.setSpacing(5);
            hamburgerMenu.setStyle("""
            -fx-background-color: #c5effc;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0.1, 0, 4);
        """);

            hamburgerMenu.setPrefWidth(120);
            hamburgerMenu.setMaxHeight(Region.USE_PREF_SIZE);
            hamburgerMenu.setMaxWidth(Region.USE_PREF_SIZE);

            Label logoutLabel = new Label("Log Out");
            logoutLabel.setStyle("""
            -fx-padding: 8 12 8 12;
            -fx-font-family: 'Playfair Display';
            -fx-font-weight: 600;
            -fx-font-size: 14px;
            -fx-text-fill: #26524b;
            -fx-alignment: center-left;
        """);

            logoutLabel.setOnMouseEntered(e -> logoutLabel.setStyle(logoutLabel.getStyle() +
                    "-fx-background-color: #e8f9ff; -fx-cursor: hand;"));
            logoutLabel.setOnMouseExited(e -> logoutLabel.setStyle("""
            -fx-padding: 8 12 8 12;
            -fx-font-family: 'Playfair Display';
            -fx-font-weight: 600;
            -fx-font-size: 14px;
            -fx-text-fill: #26524b;
            -fx-alignment: center-left;
        """));

            logoutLabel.setOnMouseClicked(e -> {
                HomeController.show(new Stage());
                rootPane.getChildren().remove(hamburgerMenu);
                isMenuOpen = false;
            });

            hamburgerMenu.getChildren().add(logoutLabel);

            // Position dropdown right under the hamburger icon
            StackPane.setAlignment(hamburgerMenu, Pos.TOP_RIGHT);

            // Margin to place dropdown slightly below and right of the hamburger icon
            StackPane.setMargin(hamburgerMenu, new Insets(80, 20, 0, 0));
            rootPane.getChildren().add(hamburgerMenu);
            isMenuOpen = true;

            // Close dropdown if user clicks outside the menu and hamburger icon
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
}