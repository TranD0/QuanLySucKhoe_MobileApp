package vn.tranthaingocdo.tranthaingocdo_63133716.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import vn.tranthaingocdo.tranthaingocdo_63133716.Daily;
import vn.tranthaingocdo.tranthaingocdo_63133716.Login;
import vn.tranthaingocdo.tranthaingocdo_63133716.MenuActivity;
import vn.tranthaingocdo.tranthaingocdo_63133716.ProficeActivity;
import vn.tranthaingocdo.tranthaingocdo_63133716.R;

public class HomeFragment extends Fragment {
    private View rootView;  FirebaseAuth auth;String email;    FirebaseUser User; private FirebaseDatabase db;private DatabaseReference ref;
    private ProgressBar progressBar;
    TextView day,caloSum,count;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        day= rootView.findViewById(R.id.DateNow);
        caloSum = rootView.findViewById(R.id.CaloSum);
        count =rootView.findViewById(R.id.countF);
        progressBar = rootView.findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();
        User = auth.getCurrentUser();
        email = User.getEmail();
        ReadData(email.replace(".", ","));
// Định dạng ngày giờ

        day.setText(LocalDate.now().toString());
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
                    progressBar.setProgress(Integer.parseInt(String.valueOf(dataSnapshot.child("water").getValue())));
                    count.setText(Co);
                    caloSum.setText(Ca);

                }
                else{
                    Daily daily = new Daily(0,0,LocalDate.now().toString(),0);
                    db= FirebaseDatabase.getInstance();
                    ref=db.getReference("Daily").child(email.replace(".", ","));
                    ref.child(LocalDate.now().toString()).setValue(daily).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            ChuyenTrang(HomeFragment.class);
                        }
                    });
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
