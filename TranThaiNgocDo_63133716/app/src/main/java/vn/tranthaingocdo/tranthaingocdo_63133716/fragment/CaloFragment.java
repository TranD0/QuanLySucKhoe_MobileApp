package vn.tranthaingocdo.tranthaingocdo_63133716.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;

import vn.tranthaingocdo.tranthaingocdo_63133716.Daily;
import vn.tranthaingocdo.tranthaingocdo_63133716.ListFoodActivity;
import vn.tranthaingocdo.tranthaingocdo_63133716.R;

public class CaloFragment extends Fragment {
    private View rootView; FirebaseAuth auth;String email;    FirebaseUser User; private FirebaseDatabase db;private DatabaseReference ref;
    LinearLayout waterpg;
    TextView day,caloSum,count;
    Button button;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_calo, container, false);
        button=rootView.findViewById(R.id.buttonAdd);
        caloSum=rootView.findViewById(R.id.CaloSum1);
        count=rootView.findViewById(R.id.count1);
        auth = FirebaseAuth.getInstance();
        User = auth.getCurrentUser();
        email = User.getEmail();
        ReadData(email.replace(".", ","));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChuyenTrang(ListFoodActivity.class);
            }
        });
     return rootView;
    }
    private void ReadData(String email){
        DatabaseReference refa = FirebaseDatabase.getInstance().getReference("Daily").child(email.replace(".", ",")).child(LocalDate.now().toString());
        refa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String Ca = String.valueOf(dataSnapshot.child("calo").getValue());
                    String Co = String.valueOf(dataSnapshot.child("count").getValue());
                    count.setText(Co);
                    caloSum.setText(Ca);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi nếu có
            }
        });
    }
    public void ChuyenTrang(Class<?> cls) {
        Intent intent = new Intent(requireActivity(), cls);
        startActivity(intent);
    }

}
