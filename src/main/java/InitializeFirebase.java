import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.*;

public class InitializeFirebase {
    public static final String DatabaseURL = "https://parmbil-5557d.firebaseio.com";

    public static void initializeDB() {
        FileInputStream serviceAccount = null;
        FirebaseOptions options = null;
        try {

            serviceAccount = new FileInputStream("parmbil-5557d-firebase-adminsdk-42mad-248bbbfff8.json");
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(DatabaseURL)
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR: invalid service account credentials. See README.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException at InitializeFirebase");
        }
        System.out.println("Database initialized");
    }
}
