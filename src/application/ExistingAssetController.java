package application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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

}
