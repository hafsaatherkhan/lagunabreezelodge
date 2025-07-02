package com.lagunabreezelodge.controller;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AllRegisteredUsersViewerController {


        public static void show() {
            Stage stage = new Stage();
            stage.setTitle("Role Selection - Laguna Breeze Lodge");

            // Title text
            Text title = new Text("Which table do you want to view?");
            title.setFont(Font.font("Playfair Display", FontWeight.BOLD, 22));
            title.setFill(Color.web("#5C4033"));
            stage.getIcons().add(new Image(RegisterController.class.getResourceAsStream("/images/logo.jpg")));

            // Buttons
            Button usersBtn = new Button("View All Users");
            Button adminsBtn = new Button("View All Admins");

            usersBtn.setFont(Font.font("Playfair Display", 16));
            adminsBtn.setFont(Font.font("Playfair Display", 16));

            usersBtn.setStyle("-fx-background-color: #4d663b; -fx-text-fill: White;");
            adminsBtn.setStyle("-fx-background-color: #4d663b; -fx-text-fill: White;");

            usersBtn.setOnAction(e -> {
                UsersViewController.show(new Stage());
                stage.close();
            });
            usersBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    usersBtn.setStyle(
                            "-fx-background-color: #7a8c6d; " +
                                    "-fx-text-fill: white;" +
                                    "-fx-background-radius: 5;"
                    );
                }
            });

            usersBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    usersBtn.setStyle(
                            "-fx-background-color: #4d663b;" +
                                    "-fx-text-fill: white;" +
                                    "-fx-background-radius: 5;"
                    );
                }
            });
            adminsBtn.setOnAction(e -> {
                AdminsViewController.show(new Stage());
                stage.close();
            });
            adminsBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    adminsBtn.setStyle(
                            "-fx-background-color: #7a8c6d; " +
                                    "-fx-text-fill: white;" +
                                   "-fx-background-radius: 5;"
                    );
                }
            });

            adminsBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    adminsBtn.setStyle(
                            "-fx-background-color: #4d663b;" +
                                    "-fx-text-fill: white;" +
                                    "-fx-background-radius: 5;"
                    );
                }
            });
            // Layout
            HBox buttonBox = new HBox(20, usersBtn, adminsBtn);
            buttonBox.setAlignment(Pos.CENTER);

            VBox layout = new VBox(25, title, buttonBox);
            layout.setAlignment(Pos.CENTER);
            layout.setPadding(new Insets(30));
            layout.setStyle("-fx-background-color:  #e6c8a0;");

            Scene scene = new Scene(layout, 400, 200);
            stage.setScene(scene);
            stage.show();
        }
    }


