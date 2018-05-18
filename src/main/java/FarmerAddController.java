import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class FarmerAddController implements Initializable {

    private DatabaseReference db;

    @FXML
    private TextField textUsername;
    @FXML
    private TextField textPassword;
    @FXML
    private TextField textName;
    @FXML
    private TextField textLocation;
    @FXML
    private TextField textFavCrop;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnOk;
    @FXML
    private Label lblError;

    public void okPressed(ActionEvent event) {
        if(textUsername.getText() == null)
            textUsername.setText("");
        if(textPassword.getText() == null)
            textPassword.setText("");
        if(textName.getText() == null)
            textName.setText("");
        if(textLocation.getText() == null)
            textLocation.setText("");
        if(textFavCrop.getText() == null)
            textFavCrop.setText("");

        if (textUsername.getText().equals("") || textPassword.getText().equals("") || textName.getText().equals("") || textLocation.getText().equals("")|| textFavCrop.getText().equals("")){
            lblError.setText("Incomplete Data");
        } else {
            String username, password, name, location, favCrop;
            username = textUsername.getText();
            password = textPassword.getText();
            name = textName.getText();
            location = textLocation.getText();
            favCrop = textFavCrop.getText();

            Farmer farmer = new Farmer(favCrop, location, name, password, username);
            db.child(username).setValueAsync(farmer);

            Stage stage = (Stage) btnOk.getScene().getWindow();
            stage.close();

        }
    }
    public void cancelPressed(ActionEvent event){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = FirebaseDatabase.getInstance().getReference().child("Farmers");
        lblError.setText("");

    }
}
