package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ExistingAssetsController {
    @FXML Button homeButton;
    @FXML Button searchButton;
    @FXML Label existingAssetsLabel;
    @FXML TableView<Asset> assetsTable;
    @FXML TableColumn<Asset, String> nameColumn;
    @FXML TableColumn<Asset, String> categoryColumn;
    @FXML TableColumn<Asset, String> locationColumn;
    @FXML TableColumn<Asset, String> viewItemColumn;
    @FXML Button viewItemButton;

    private List<String> assets = new ArrayList<>(); // Array that stores assets
    private ObservableList<Asset> assetObservableList = FXCollections.observableArrayList();

    public void initialize() {
        // Set initial text for category display label
        existingAssetsLabel.setText("Asset List");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        viewItemColumn.setCellValueFactory(new PropertyValueFactory<>("viewItem"));
        loadAssets(); // load entries from assets.csv into the table
    }

    public void loadAssets() {
        String filePath = "assets.csv";

        if (Files.exists(Paths.get(filePath))) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(filePath));

                // Clear existing assets List
                assets.clear();

                // add each asset to assets
                for (String line : lines) {
                    String[] parts = line.split(":");
                    assetObservableList.add(new Asset(parts[0], parts[1], parts[2]));
                }
            } catch (IOException e) {
                System.err.println("Error reading from CSV file: " + e.getMessage());
            }
        } else {
            System.out.println("File does not exist: " + filePath);
        }
    }

    /**

     Handles the action event when the "Back To Home" button is clicked.
     Loads the homepage FXML file and sets it as the scene for the stage.
     */
    public void goHome() {
        try {// Load the FXML file for the welcome page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Welcome.fxml"));
            Parent root = loader.load();

            // Get the stage from the current scene
            Stage stage = (Stage) existingAssetsLabel.getScene().getWindow();

            // Set the new scene for the stage
            Scene scene = new Scene(root);

            // Set the title of the stage
            stage.setTitle("Welcome to TrackWise");

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

}
