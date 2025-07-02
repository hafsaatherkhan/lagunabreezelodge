package com.lagunabreezelodge.controller;

import com.lagunabreezelodge.db.dao.UserDAO;
import com.lagunabreezelodge.db.dao.impl.UserDAOImpl;
import com.lagunabreezelodge.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;

public class UsersViewController {

    public static void show(Stage stage) {

        UserDAO userDAO = new UserDAOImpl();
        List<User> userList = userDAO.findAllUsers();
        ObservableList<User> users = FXCollections.observableArrayList(userList);

        // TableView
        TableView<User> tableView = new TableView<>(users);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefHeight(300);

        TableColumn<User, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableView.getColumns().addAll(idCol, nameCol, emailCol);

        // Title
        Label title = new Label("✧˖°ALL USERS✧˖°");
        title.setFont(Font.font("Playfair Display", FontWeight.BOLD, 28));
        title.setTextFill(Color.web("#26524b"));

        // Shadow wrapper
        StackPane tableWrapper = new StackPane(tableView);
        tableWrapper.setStyle("-fx-background-color: white; -fx-background-radius: 8;");
        tableWrapper.setPadding(new Insets(20));
        tableWrapper.setEffect(new DropShadow(10, Color.GRAY));
        tableWrapper.setMaxWidth(600);

        // Layout
        VBox root = new VBox(30, title, tableWrapper);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        root.setStyle("-fx-background-color: #E4C590;");

        // Scene
        Scene scene = new Scene(root, 800, 500);
        stage.getIcons().add(new Image(UsersViewController.class.getResourceAsStream("/images/logo.jpg")));
        stage.setTitle("All Users");
        stage.setScene(scene);
        stage.show();
    }
}
