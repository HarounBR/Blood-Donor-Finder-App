package com.example.miniprojet;

import java.sql.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class AuthController {
    @FXML private TextField nameField, emailField, locationField, bloodField;
    @FXML private PasswordField passwordField;
    @FXML private DatePicker lastDonationPicker;
    @FXML private Label messageLabel;

    public void register() {
        try (Connection conn = DBUtil.connect()) {
            String sql = "INSERT INTO users(name, email, password, blood_type, location, last_donation) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nameField.getText());
            stmt.setString(2, emailField.getText());
            stmt.setString(3, passwordField.getText());
            stmt.setString(4, bloodField.getText());
            stmt.setString(5, locationField.getText());
            stmt.setDate(6, Date.valueOf(lastDonationPicker.getValue()));
            stmt.executeUpdate();
            messageLabel.setText("Registered successfully!");
        } catch (Exception e) {
            messageLabel.setText("Error: " + e.getMessage());
        }
    }

    public void login() {
        try (Connection conn = DBUtil.connect()) {
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, emailField.getText());
            stmt.setString(2, passwordField.getText());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                messageLabel.setText("Login successful!");
                Stage stage = (Stage) messageLabel.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/miniprojet/search.fxml"));
                stage.setScene(new Scene(root));
            } else {
                messageLabel.setText("Invalid credentials");
            }
        } catch (Exception e) {
            messageLabel.setText("Error: " + e.getMessage());
        }
    }
    public void goToRegister() {
        try {
            Stage stage = (Stage) emailField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/miniprojet/register.fxml"));
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToLogin() {
        try {
            Stage stage = (Stage) nameField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/miniprojet/login.fxml"));
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}