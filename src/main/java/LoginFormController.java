import com.google.firebase.database.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Label infoLabel;


    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    public void loginPressed(ActionEvent e) throws IOException {


        if (username.getText() == null) {
            username.setText("");
        } else if (password.getText() == null) {
            password.setText("");
        }

        //db.orderByChild("username").equalTo(username.getText()).addListenerForSingleValueEvent(new ValueEventListener() {

        //gets username from textfield and gets the exact username to match
        //gets the matched username and moves matched Admin to currentAdmin to compare later on
        if (!NetChecker.netIsAvailable()) {
            infoLabel.setText("Check Internet Connection");
        } else {
            db.orderByChild("username").equalTo(username.getText()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    lock.lock();
                    if (snapshot.getValue() != null) {
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            userExists = true;
                            Admin admin = snap.getValue(Admin.class);
//                        System.out.println(admin.getUsername());
//                        System.out.println(admin.getPassword());
//                        System.out.println(admin.getName());
                            currentAdmin = admin;
                        }
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

            //syncs code with snapshot
            lock.lock();
            try {
                infoLabel.setText("Please wait...");
                cond.await();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            lock.unlock();

            boolean loginSuccessful = false;

            //compares currentAdmin from the listener a while ago
            if (currentAdmin != null) {
                if (currentAdmin.getUsername().equals(username.getText()) && currentAdmin.getPassword().equals(password.getText()) && currentAdmin.getActive()) {
                    loginSuccessful = true;
                }
            }

            //Login logic here
            if (loginSuccessful) {
                System.out.println("Login Successful");
                infoLabel.setText("");
                Parent adminFormParent = FXMLLoader.load(getClass().getResource("AdminFormFarmer.fxml"));
                Scene adminFormScene= new Scene(adminFormParent);

                //This line gets the Stage information
                Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
                window.setResizable(false);
                window.setScene(adminFormScene);
                window.show();

            } else {
                infoLabel.setText("Invalid credentials");
            }
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
