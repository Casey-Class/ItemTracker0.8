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

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewAssetController {
	@FXML TextField assetNameText;	// Text field for entering asset name
	@FXML TextField purchaseValue;	// Text field for entering purchase value
	@FXML TextArea descriptionBox;	// Text area for entering description
	@FXML DatePicker datePicker;	// Date picker for entering date purchased
	@FXML DatePicker warrantyDate;	// Date picker for entering warranty date
	@FXML ChoiceBox<String> locationPicker;	// Choice box for entering location
	@FXML ChoiceBox<String> categoryPicker;	// Choice box for entering category
	@FXML Button homeButton;	// Button for going home
	@FXML Button saveButton;	// Button for saving
	@FXML Label categoryDisplayLabel7;	// Label updating status of save
	
	private String valuePurchase;	// Purchase value instance variable
	private String description;		// Description instance variable
	private LocalDate date;	// Date instance variable
	private LocalDate warranty;	// Warranty instance variable
	
	private List<String> categoriesList = new ArrayList<>(); // Array that stores categories
	private List<String> locationList = new ArrayList<>();	// Array that stores location
	
	
	public void initialize() {
		// Set initial text for category display label
		categoryDisplayLabel7.setText("Define New Asset"); 
		loadCategories(); // Loads categories from categories.csv into the categories choice box
		loadLocations(); // Load Locations from locations.csv into the categories choice box
    }
	
	public void loadCategories() {
	    String filePath = "categories.csv";

	    // Check if the file exists
	    if (Files.exists(Paths.get(filePath))) {
	        try {
	            // Read all lines from the CSV file into a list
	            List<String> lines = Files.readAllLines(Paths.get(filePath));
	            
	            // Clear existing categoriesList
	            categoriesList.clear();

	            // Add each line (category) to the categoriesList
	            for (String line : lines) {
	                if (!line.trim().isEmpty()) {  // Check if the line is not empty or whitespace
	                    categoriesList.add(line);
	                }
	            }

	            // Clear and then add categories to the categoryPicker
	            categoryPicker.getItems().clear();
	            categoryPicker.getItems().addAll(categoriesList);
	            
	        } catch (IOException e) {
	            System.err.println("Error reading from CSV file: " + e.getMessage());
	        }
	    } else {
	        System.out.println("File does not exist: " + filePath);
	    }
	}
	
	public void loadLocations() {
	    String filePath = "location_categories.csv";

	    // Check if the file exists
	    if (Files.exists(Paths.get(filePath))) {
	        try {
	            // Read all lines from the CSV file into a list
	            List<String> lines = Files.readAllLines(Paths.get(filePath));
	            
	            // Clear existing locationList
	            locationList.clear();

	            // Add each line (location) to the locationList
	            for (String line : lines) {
	                if (!line.trim().isEmpty()) {  // Check if the line is not empty or whitespace
	                    locationList.add(Location.toLocation(line).getName());
	                }
	            }

	            // Clear and then add location to the locationPicker
	            locationPicker.getItems().clear();
	            locationPicker.getItems().addAll(locationList);
	            
	        } catch (IOException e) {
	            System.err.println("Error reading from CSV file: " + e.getMessage());
	        }
	    } else {
	        System.out.println("File does not exist: " + filePath);
	    }
	}
	
	/**
     * Handles the action event when the "Save" button is clicked.
     * Reads the information entered by the user
     * If assetName, category, or location is empty, displays an error message.
     */
	public void addInfo() {
		String assetName = assetNameText.getText(); 
		String category = categoryPicker.getValue();
		String location = locationPicker.getValue();
		
		if(!purchaseValue.getText().isEmpty()) {
			valuePurchase = purchaseValue.getText();
		}
		else
		{
			valuePurchase = null;
		}
		
		if(!descriptionBox.getText().isEmpty()) {
			description = descriptionBox.getText();
		}
		else
		{
			description = null;
		}
		
		if(datePicker.getValue() != null) {
			date = datePicker.getValue();
		}
		
		if(warrantyDate.getValue() != null) {
			warranty = warrantyDate.getValue();
		}
		
		if (assetName.isEmpty() || category == null || location == null) {
			categoryDisplayLabel7.setText("Error. Must Define all Required Info");
		}
		else {
			categoryDisplayLabel7.setText("New Location Defined: " + assetName);
			saveAssetToCSV(assetName, category, location, valuePurchase, description, date, warranty);
			categoryDisplayLabel7.setText("Asset Saved");
			descriptionBox.setText(""); // Clears the text field once the locationDescription is saved.
			assetNameText.setText(""); // Clears the text field once the assetName is saved.
			purchaseValue.setText(""); // Clears the text field once the purchase value is saved.
		}
		
	}
	
	/**
	 * Saves the entered asset to a CSV file.
	 * @param assetName The name of the asset to be saved.
	 * @param category The name of the category to be saved.
	 * @param location The name of the location to be saved.
	 * @param valuePurchase The value of purchase to be saved.
	 * @param description The description to be saved.
	 * @param date The date to be saved.
	 * @param warranty The warranty to be saved.
	 */
	public void saveAssetToCSV(String assetName, String category, String location, String valuePurchase, String description, LocalDate date, LocalDate warranty) {
	    Asset asset = new Asset(assetName, category, location);
	    asset.addDate(date);
	    asset.addDescription(description);
	    asset.addValue(valuePurchase);
	    asset.addWarranty(warranty);
	    
		String filePath = "assets.csv"; // The path to the CSV file.
	    
	    // Check if the file already exists, and if not, create it.
	    try {
	        if (!Files.exists(Paths.get(filePath))) {
	            Files.createFile(Paths.get(filePath));
	        }
	        
	        // Use FileWriter and BufferedWriter to write to the CSV file.
	        try (FileWriter fw = new FileWriter(filePath, true);
	             BufferedWriter bw = new BufferedWriter(fw);
	             PrintWriter out = new PrintWriter(bw)) {
	            out.println(asset.toString()); // Write the asset to the file.
	        } catch (IOException e) {
	            System.err.println("Error writing to CSV file: " + e.getMessage());
	            // Handle the exception appropriately, such as showing a dialog to the user.
	        }
	    } catch (IOException ex) {
	        System.err.println("An error occurred initializing the CSV file: " + ex.getMessage());
	        // Handle the exception appropriately.
	    }
	}
	
	/**
/**
    
	Handles the action event when the "Back To Home" button is clicked.
	Loads the homepage FXML file and sets it as the scene for the stage.
	*/
	public void goHome() {
	    try {// Load the FXML file for the welcome page
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Welcome.fxml"));
	        Parent root = loader.load();

	            // Get the stage from the current scene
	            Stage stage = (Stage) categoryDisplayLabel7.getScene().getWindow();

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
