import com.google.firebase.database.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {


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

       // farmerRef.child("admin").setValue(new Admin("admin2", "admin", "Admin Man", true));

        System.out.println("2");

        farmerRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
//                System.out.println("11");
//                System.out.println(snapshot.exists());
//                Farmer farmer = snapshot.getValue(Farmer.class);
//                System.out.println("12");
//                //System.out.println(farmer);
//                System.out.println("13");
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
                System.out.println("Fail");
            }
        });
//
//        farmerRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                System.out.println("3");
//                for (DataSnapshot snap : snapshot.getChildren()) {
//                    Farmer farmer = snap.getValue(Farmer.class);
//                    System.out.println("Good!");
//                    System.out.println(farmer.getName());
//                }
//                System.out.println("4");
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//
//            }
//        });

    }
}
