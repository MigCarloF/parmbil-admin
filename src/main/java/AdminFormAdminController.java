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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminFormAdminController implements Initializable {

    private DatabaseReference db;
    private ObservableList<Admin> admins;

    @FXML
    private TableView<Admin> tableAdmin;
    @FXML
    private TableColumn<Admin, String> colName;
    @FXML
    private TableColumn<Admin, String> colUsername;
    @FXML
    private TableColumn<Admin, String> colActive;
    @FXML
    private Label lblUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = FirebaseDatabase.getInstance().getReference().child("Admin");
        lblUser.setText("");
        if(SingletonLogin.getInstance().getCurrentLogin() != null){
            lblUser.setText(SingletonLogin.getInstance().getCurrentLogin().getName());
        }
        initTable();
    }

    public void initTable(){

        admins = FXCollections.observableArrayList();
        colName.setCellValueFactory(new PropertyValueFactory<Admin, String>("name"));
        colUsername.setCellValueFactory(new PropertyValueFactory<Admin, String>("username"));
        colActive.setCellValueFactory(new PropertyValueFactory<Admin, String>("active"));

        System.out.println("ne");
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                System.out.println("hey");
                Admin admin = snapshot.getValue(Admin.class);
                System.out.println("lol");
                admins.add(admin);
                tableAdmin.setItems(admins);
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

    public void addPressed(ActionEvent event) throws IOException {
        Parent adminFormParent = FXMLLoader.load(getClass().getResource("AdminAdd.fxml"));
        Scene adminFormScene= new Scene(adminFormParent);

        //This line gets the Stage information
        Stage window = new Stage();
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(adminFormScene);
        window.showAndWait();
    }
    public void deletePressed(ActionEvent event) throws  IOException {
        if (!SingletonLogin.getInstance().getCurrentLogin().getMaster()) {
            System.out.println("Not enough permissions!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Not enough permissions");
            alert.setContentText("Only master admin can delete admin");
            alert.showAndWait();
        } else if (tableAdmin.getSelectionModel().getSelectedCells().isEmpty()) {
            System.out.println("No admin selected!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No admin selected.");
            alert.setContentText("Please select an admin profile to delete.");
            alert.showAndWait();
        } else if (tableAdmin.getSelectionModel().getSelectedItem().getUsername().equals(SingletonLogin.getInstance().getCurrentLogin().getUsername())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Self deletion");
            alert.setContentText("Cannot delete your account");
            alert.showAndWait();
        } else {
            Stage window = new Stage();
            Admin editAdmin = tableAdmin.getSelectionModel().getSelectedItem();
            SingletonEditAdmin.getInstance().setAdmin(editAdmin);

            FXMLLoader anotherLoader = new FXMLLoader(getClass().getResource("AdminDelete.fxml"));
            Parent anotherRoot = anotherLoader.load();
            Scene anotherScene = new Scene(anotherRoot);
            window.setResizable(false);
            window.initModality(Modality.APPLICATION_MODAL);
            window.setScene(anotherScene);
            window.showAndWait();
        }
    }
    public void editPressed(ActionEvent event) throws IOException {
         if (!SingletonLogin.getInstance().getCurrentLogin().getMaster()) {
             System.out.println(SingletonLogin.getInstance().getCurrentLogin().getName());
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Not enough permissions");
             alert.setContentText("Only master admin can edit admin");
             alert.showAndWait();
         } else if (tableAdmin.getSelectionModel().getSelectedCells().isEmpty()) {
            System.out.println("No admin selected!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No admin selected.");
            alert.setContentText("Please select an admin profile to edit.");
            alert.showAndWait();
        } else {
            Stage window = new Stage();
            Admin editAdmin = tableAdmin.getSelectionModel().getSelectedItem();
            SingletonEditAdmin.getInstance().setAdmin(editAdmin);

            FXMLLoader anotherLoader = new FXMLLoader(getClass().getResource("AdminEdit.fxml"));
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

    public void farmerPressed(ActionEvent event) throws IOException{
        loadWindow(event, "AdminFormFarmer.fxml");
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
