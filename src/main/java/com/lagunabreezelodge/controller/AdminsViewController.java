package com.lagunabreezelodge.controller;

import com.lagunabreezelodge.db.dao.AdminUserDAO;
import com.lagunabreezelodge.db.dao.impl.AdminUserDAOImpl;
import com.lagunabreezelodge.model.AdminUser;
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

public class AdminsViewController {

       public static void show(Stage stage) {
              // DAO + Fetch
              AdminUserDAO adminDAO = new AdminUserDAOImpl();
              List<AdminUser> adminList = adminDAO.findAll();
              ObservableList<AdminUser> admins = FXCollections.observableArrayList(adminList);

              // TableView
              TableView<AdminUser> tableView = new TableView<>(admins);
              tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
              tableView.setPrefWidth(900);
              tableView.setPrefHeight(600);

              TableColumn<AdminUser, Integer> idCol = new TableColumn<>("ID");
              idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
              idCol.setStyle("-fx-alignment: CENTER;");

              TableColumn<AdminUser, String> nameCol = new TableColumn<>("Name");
              nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
              nameCol.setStyle("-fx-alignment: CENTER;");

              TableColumn<AdminUser, String> emailCol = new TableColumn<>("Email");
              emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
              emailCol.setStyle("-fx-alignment: CENTER;");

              TableColumn<AdminUser, String> createdAtCol = new TableColumn<>("Created At");
              createdAtCol.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
              createdAtCol.setStyle("-fx-alignment: CENTER;");

              tableView.getColumns().addAll(idCol, nameCol, emailCol, createdAtCol);

              // Title
              Label title = new Label("✧˖°ADMIN USERS✧˖°");
              title.setFont(Font.font("Playfair Display", FontWeight.BOLD, 28));
              title.setTextFill(Color.web("#26524b"));

              // Card-style shadow box for table
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

              // Scene Setup
              Scene scene = new Scene(root, 900, 600);
              stage.getIcons().add(new Image(AdminsViewController.class.getResourceAsStream("/images/logo.jpg")));
              stage.setTitle("Admin Users - Laguna Breeze Lodge");
              stage.setScene(scene);
              stage.show();
       }
}
