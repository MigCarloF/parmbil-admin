import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class FarmerEditController implements Initializable {

    Farmer farmerToEdit;

    DatabaseReference db;

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

        textUsername.setText(farmerToEdit.getUsername());
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
            Map<String, Object> updates = new HashMap<>();
            updates.put("pw",password);
            updates.put("name", name);
            updates.put("location", location);
            updates.put("favoriteCrop", favCrop);

            db.child(username).updateChildrenAsync(updates);

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
        farmerToEdit = SingletonEditFarmer.getInstance().getFarmer();
        SingletonEditFarmer.getInstance().setFarmer(null);

        textUsername.setText(farmerToEdit.getUsername());
        textPassword.setText(farmerToEdit.getPw());
        textLocation.setText(farmerToEdit.getLocation());
        textName.setText(farmerToEdit.getName());
        textFavCrop.setText(farmerToEdit.getFavoriteCrop());
    }



}
