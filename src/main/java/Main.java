import com.google.firebase.database.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    public static void main(String[] args) {
        InitializeFirebase.initializeDB();
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getResource("LoginForm.fxml");
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Parmbil Admin Applet");
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
        Map<String, Object> updates = new HashMap<>();
        updates.put("pw","joker2");
        farmerRef.child("koreantol").updateChildren(updates);
    }
}
