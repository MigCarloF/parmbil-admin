import com.google.firebase.database.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args){
        InitializeFirebase.initializeDB();

        System.out.println("1");
        DatabaseReference db;
        db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference farmerRef = db;
        //DatabaseReference farmerRef = db.child("Farmers");

        Map<String, Farmers> farmers = new HashMap<>();
        farmerRef.child("grace69").setValueAsync(new Farmers("Aji No Moto", "China", "Grace Tan Wei Loon", "grace69lol", "grace69"));
        farmers.put("grace696", new Farmers("Aji No Moto", "China", "Grace Tan Wei Loon", "grace69lol", "grace696"));

        farmerRef.setValueAsync(farmers);

        System.out.println("2");

        farmerRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("3");
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Farmers farmer = snap.getValue(Farmers.class);
                    System.out.println(farmer.getName());
                }
                System.out.println("4");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("fail");
            }
        });
    }
}
