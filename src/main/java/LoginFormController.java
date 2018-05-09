import com.google.firebase.database.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoginFormController implements Initializable {

    private DatabaseReference db;
    private ArrayList<Admin> adminList;
    private boolean userExists = false;
    private Admin currentAdmin;

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button login;
    @FXML
    private Label infoLabel;


    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    public void loginPressed(ActionEvent e) {
        if (username.getText() == null) {
            username.setText("");
        } else if (password.getText() == null) {
            password.setText("");
        }

        db.equalTo(username.getText()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                lock.lock();
                if (snapshot.exists()) {
                    userExists = true;
                    Admin admin = snapshot.getValue(Admin.class);
                    currentAdmin = admin;
                } else {
                    userExists = false;
                    System.out.println("NO :(");
                }
                cond.signal();
                lock.unlock();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        boolean loginSuccessful = false;
        if(currentAdmin != null) {
            if (currentAdmin.getUsername().equals(username.getText()) && currentAdmin.getPassword().equals(password.getText())) {
                loginSuccessful = true;
            }
        }

        if (loginSuccessful) {
            System.out.println("LOGIN!");
        } else {
            infoLabel.setText("Invalid credentials");
        }
        currentAdmin = null;
        username.setPromptText("Username");
        password.setPromptText("Password");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        infoLabel.setText("");
        db = FirebaseDatabase.getInstance().getReference().child("Admin");
        adminList = new ArrayList<>();
    }
}
