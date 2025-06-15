package com.example.miniprojet;


import java.sql.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;

public class SearchController {
    @FXML private TextField bloodSearchField, locationSearchField;
    @FXML private ListView<String> resultList;

    public void searchDonors() {
        ObservableList<String> results = FXCollections.observableArrayList();
        try (Connection conn = DBUtil.connect()) {
            String sql = "SELECT name, email, blood_type, location, last_donation FROM users WHERE blood_type=? AND location=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, bloodSearchField.getText());
            stmt.setString(2, locationSearchField.getText());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String entry = rs.getString("name") + " | " + rs.getString("blood_type") +
                        " | " + rs.getString("location") + " | " + rs.getString("email") +
                        " | Last donation: " + rs.getDate("last_donation");
                results.add(entry);
            }
            resultList.setItems(results);
        } catch (Exception e) {
            resultList.getItems().add("Error: " + e.getMessage());
        }
    }
}