import com.google.firebase.database.*;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    private DatabaseReference db;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference farmerRef = db.child("Farmer");

        farmerRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Farmer farmer = snap.getValue(Farmer.class);
                    System.out.println(farmer.getName());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
}
