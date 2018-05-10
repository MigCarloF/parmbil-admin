import com.google.firebase.database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        db = FirebaseDatabase.getInstance().getReference().child("Farmers");

        initTable();

    }

    public void initTable(){
        farmers = FXCollections.observableArrayList();
        colName.setCellValueFactory(new PropertyValueFactory<Farmer, String>("name"));
        colUsername.setCellValueFactory(new PropertyValueFactory<Farmer, String>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<Farmer, String>("pw"));
        colLocation.setCellValueFactory(new PropertyValueFactory<Farmer, String>("location"));
        colFavCrop.setCellValueFactory(new PropertyValueFactory<Farmer, String>("favoriteCrop"));

        System.out.println("ne");
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                System.out.println("hey");
                Farmer farmer = snapshot.getValue(Farmer.class);
                System.out.println("lol");
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

    public void adminPressed(ActionEvent event) throws IOException{
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

    private void loadWindow(ActionEvent event, String name) throws IOException{
        Parent adminFormParent = FXMLLoader.load(getClass().getResource(name));
        Scene adminFormScene= new Scene(adminFormParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(adminFormScene);
        window.show();
    }

}