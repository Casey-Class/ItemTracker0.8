package application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExistingAssetController {

    @FXML
    private TableView<?> AssetTable;

    @FXML
    private TableColumn<?, ?> ViewItemCol;

    @FXML
    private TextField SearchField;

    @FXML
    private TableColumn<?, ?> CategoryCol;

    @FXML
    private ScrollPane ScrollPane;

    @FXML
    private Button SearchButton;

    @FXML
    private Label PageLabel;

    @FXML
    private Button BackButton;

    @FXML
    private AnchorPane ExistingAsset;

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private TableColumn<?, ?> AssetNameCol;

    @FXML
    private TableColumn<?, ?> LocationCol;

    @FXML
    void goHome(ActionEvent event) {

    }

    private List<String> assets = new ArrayList<>(); // Array that stores assets
    private ObservableList<Asset> assetObservableList = FXCollections.observableArrayList();

    public void initialize() {
        // Set initial text for category display label
        PageLabel.setText("Asset List");
        AssetNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        CategoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        LocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        ViewItemCol.setCellValueFactory(new PropertyValueFactory<>("viewItem"));
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

     Handles the action event when the "Back" button is clicked.
     Loads the homepage FXML file and sets it as the scene for the stage.
     */
    public void goHome() {
        try {// Load the FXML file for the welcome page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Welcome.fxml"));
            Parent root = loader.load();

            // Get the stage from the current scene
            Stage stage = (Stage) PageLabel.getScene().getWindow();

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
