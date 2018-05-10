import com.google.firebase.database.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        InitializeFirebase.initializeDB();
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        File f = new File("src/main/java/hi.txt");
        f.getParentFile().mkdirs();
        f.createNewFile();
        URL url = getClass().getResource("LoginForm.fxml");
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Cebu South Bus Terminal");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
//        primaryStage.setTitle("Parmbil Admin");
//        StackPane layout = new StackPane();
//        Scene scene = new Scene(layout, 300, 250);
//        primaryStage.setScene(scene);
//        primaryStage.show();

        //setupGuy();
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
