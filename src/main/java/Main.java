import com.google.firebase.database.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

private int chilren = 0;
    public static void main(String[] args) {
        InitializeFirebase.initializeDB();
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Parmbil Admin");

        StackPane layout = new StackPane();

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();

        setupGuy();
    }

    private void setupGuy() {
        System.out.println("1");
        DatabaseReference db;
        db = FirebaseDatabase.getInstance().getReference();
        //DatabaseReference farmerRef = db;
        DatabaseReference farmerRef = db.child("Farmers");

        farmerRef.child("justice").setValueAsync(new Farmer("Steek", "kiosk", "Justice De la Vida", "fhel", "justice"));

        System.out.println("2");

        farmerRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                Farmer farmer = snapshot.getValue(Farmer.class);
                System.out.println("Child");
                System.out.println(farmer.getName());
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
                System.out.println("Fail" + error.getCode());
            }
        });

    }
}
