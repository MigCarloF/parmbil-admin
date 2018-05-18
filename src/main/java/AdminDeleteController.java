import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminDeleteController implements Initializable {

    private DatabaseReference db;
    private Admin adminToDelete;

    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;
    @FXML
    private Label lblSure;

    public void okPressed(ActionEvent event) {
        db.child(adminToDelete.getUsername()).setValueAsync(null);

        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }

    public void cancelPressed(ActionEvent event){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminToDelete = SingletonEditAdmin.getInstance().getAdmin();
        SingletonEditAdmin.getInstance().setAdmin(null);
        db = FirebaseDatabase.getInstance().getReference().child("Admin");
        lblSure.setText("Are you sure you want to delete " + adminToDelete.getUsername() + "?");
    }
}
