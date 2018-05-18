import com.google.firebase.database.*;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminFormFarmerController implements Initializable {

    DatabaseReference db;
    ObservableList<Farmer> farmers;

    @FXML
    private TableView<Farmer> tableFarmer;
    @FXML
    private TableColumn<Farmer, String> colName;
    @FXML
    private TableColumn<Farmer, String> colUsername;
    @FXML
    private TableColumn<Farmer, String> colPassword;
    @FXML
    private TableColumn<Farmer, String> colLocation;
    @FXML
    private TableColumn<Farmer, String> colFavCrop;
    @FXML
    private Label lblUser;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        db = FirebaseDatabase.getInstance().getReference().child("Farmers");
        lblUser.setText("");
        if (SingletonLogin.getInstance().getCurrentLogin() != null) {
            lblUser.setText(SingletonLogin.getInstance().getCurrentLogin().getName());
        }
        initTable();

    }

    public void initTable() {
        farmers = FXCollections.observableArrayList();
        colName.setCellValueFactory(new PropertyValueFactory<Farmer, String>("name"));
        colUsername.setCellValueFactory(new PropertyValueFactory<Farmer, String>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<Farmer, String>("pw"));
        colLocation.setCellValueFactory(new PropertyValueFactory<Farmer, String>("location"));
        colFavCrop.setCellValueFactory(new PropertyValueFactory<Farmer, String>("favoriteCrop"));

        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                Farmer farmer = snapshot.getValue(Farmer.class);
                farmers.add(farmer);
                tableFarmer.setItems(farmers);
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public void editPressed(ActionEvent event) throws IOException {
        if (tableFarmer.getSelectionModel().getSelectedCells().isEmpty()) {
            System.out.println(SingletonLogin.getInstance().getCurrentLogin().getName());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No farmer selected.");
            alert.setContentText("Please select a farmer profile to edit.");
            alert.showAndWait();
        } else{
            Stage window = new Stage();
            Farmer editFarmer = tableFarmer.getSelectionModel().getSelectedItem();
            SingletonEditFarmer.getInstance().setFarmer(editFarmer);

            FXMLLoader anotherLoader = new FXMLLoader(getClass().getResource("FarmerEdit.fxml"));
            Parent anotherRoot = anotherLoader.load();
            Scene anotherScene = new Scene(anotherRoot);
            window.setResizable(false);
            window.initModality(Modality.APPLICATION_MODAL);
            window.setScene(anotherScene);
            window.showAndWait();
        }
    }


    public void addPressed(ActionEvent event) throws IOException {
        Parent adminFormParent = FXMLLoader.load(getClass().getResource("FarmerAdd.fxml"));
        Scene adminFormScene = new Scene(adminFormParent);
        //This line gets the Stage information
        Stage window = new Stage();
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(adminFormScene);
        window.showAndWait();
    }

    public void deletePressed(ActionEvent  event) throws  IOException {
        if (tableFarmer.getSelectionModel().getSelectedCells().isEmpty()) {
            System.out.println("No farmer selected!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No farmer selected.");
            alert.setContentText("Please select a farmer profile to delete.");
            alert.showAndWait();
        } else{
            Stage window = new Stage();
            Farmer editFarmer = tableFarmer.getSelectionModel().getSelectedItem();
            SingletonEditFarmer.getInstance().setFarmer(editFarmer);

            FXMLLoader anotherLoader = new FXMLLoader(getClass().getResource("FarmerDelete.fxml"));
            Parent anotherRoot = anotherLoader.load();
            Scene anotherScene = new Scene(anotherRoot);
            window.setResizable(false);
            window.initModality(Modality.APPLICATION_MODAL);
            window.setScene(anotherScene);
            window.showAndWait();
        }
    }
    public void logoutPressed(ActionEvent event) throws IOException {

        loadWindow(event, "LoginForm.fxml");
//        System.out.println("Login Successful");
//        Parent adminFormParent = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
//        Scene adminFormScene= new Scene(adminFormParent);
//
//        //This line gets the Stage information
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setResizable(false);
//        window.setScene(adminFormScene);
//        window.show();
    }


    public void adminPressed(ActionEvent event) throws IOException {
        loadWindow(event, "AdminFormAdmin.fxml");
//        Parent adminFormParent = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
//        Scene adminFormScene= new Scene(adminFormParent);
//
//        //This line gets the Stage information
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setResizable(false);
//        window.setScene(adminFormScene);
//        window.show();
    }

    private void loadWindow(ActionEvent event, String name) throws IOException {
        Parent adminFormParent = FXMLLoader.load(getClass().getResource(name));
        Scene adminFormScene = new Scene(adminFormParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(adminFormScene);
        window.show();
    }

}