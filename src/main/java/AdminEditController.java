import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminEditController implements Initializable {

    private DatabaseReference db;
    private Admin adminToEdit;
    @FXML
    private TextField textUsername;
    @FXML
    private TextField textPassword;
    @FXML
    private TextField textName;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnOk;
    @FXML
    private CheckBox chkboxActive;
    @FXML
    private Label lblError;

    public void okPressed(ActionEvent event) {
        if(textUsername.getText() == null)
            textUsername.setText("");
        if(textPassword.getText() == null)
            textPassword.setText("");
        if(textName.getText() == null)
            textName.setText("");

        if (textUsername.getText().equals("") || textPassword.getText().equals("") || textName.getText().equals("")){
            lblError.setText("Incomplete Data");
        } else if(adminToEdit.getMaster() && !chkboxActive.isSelected()){
            System.out.println(SingletonLogin.getInstance().getCurrentLogin().getName());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Master Deactivation");
            alert.setContentText("Cannot deactivate master account");
            alert.showAndWait();
        } else {
            String username, password, name;
            boolean active;

            username = textUsername.getText();
            password = textPassword.getText();
            name = textName.getText();

            if(chkboxActive.isSelected()){
                active = true;
            } else {
                active = false;
            }

            Admin admin = new Admin(true, false, name, password,username);
            db.child(username).setValueAsync(admin);

            Map<String, Object> updates = new HashMap<>();
            updates.put("password",password);
            updates.put("name", name);
            updates.put("active", active);

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
        db = FirebaseDatabase.getInstance().getReference().child("Admin");
        lblError.setText("");
        adminToEdit = SingletonEditAdmin.getInstance().getAdmin();
        SingletonEditAdmin.getInstance().setAdmin(null);

        textName.setText(adminToEdit.getName());
        textPassword.setText(adminToEdit.getPassword());
        textUsername.setText(adminToEdit.getUsername());

        if(adminToEdit.getActive()) {
            chkboxActive.setSelected(true);
        } else {
            chkboxActive.setSelected(false);
        }

    }
}
