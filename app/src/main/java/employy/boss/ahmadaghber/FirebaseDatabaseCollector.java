package employy.boss.ahmadaghber;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.common.util.Strings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseCollector {
    private FirebaseDatabase mAuth;
    private DatabaseReference mReference;
    private ArrayList<Employee> employees = new ArrayList<>();

    public FirebaseDatabaseCollector(){
        mAuth = FirebaseDatabase.getInstance();
        mReference = mAuth.getReference("employee");
    }

    public interface DataStatus{
        void dataIsLoaded(ArrayList<Employee> employees , List<String>keys);
        void dataIsInserted();
        void dataIsUpdated();
        void dataIsDeleted();
    }
    int i=0;
    public void readEmployee(final DataStatus dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> keys = new ArrayList<>();
                i=keys.size();
                for ( DataSnapshot keyNode : dataSnapshot.getChildren() ){
                    keys.add(keyNode.getKey());
                    Employee employee = keyNode.getValue(Employee.class);
                    employees.add(employee);
                }
                dataStatus.dataIsLoaded(employees,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void add(Employee employee , final  DataStatus dataStatus){
        String key = mReference.push().getKey();

        mReference.child(key).setValue(employee).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.dataIsInserted();
            }
        });
    }


}
