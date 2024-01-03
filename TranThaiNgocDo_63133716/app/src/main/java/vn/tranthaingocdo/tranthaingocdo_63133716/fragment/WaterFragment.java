package vn.tranthaingocdo.tranthaingocdo_63133716.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;

import vn.tranthaingocdo.tranthaingocdo_63133716.MenuActivity;
import vn.tranthaingocdo.tranthaingocdo_63133716.R;

public class WaterFragment extends Fragment  {
    private ProgressBar progressBar;
    TextView txtProcess; // Khai báo biến View để lưu trữ giao diện của Fragment
    private View rootView;
    int PastWater=0;
    FirebaseAuth auth;String email;    FirebaseUser User; private FirebaseDatabase db;private DatabaseReference ref;

    private Button increase50Button, increase200Button, increase500Button, decreaseButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout cho Fragment và lưu trữ vào biến rootView
        rootView = inflater.inflate(R.layout.fragment_water, container, false);
        //check user
        auth = FirebaseAuth.getInstance();
        User = auth.getCurrentUser();
        email = User.getEmail();
        ReadData(email.replace(".", ","));
        //
        txtProcess = rootView.findViewById(R.id.TxtProcess);
        txtProcess.setText("0/2000ml");
        progressBar = rootView.findViewById(R.id.progressBar);
        increase50Button = rootView.findViewById(R.id.increase50Button);
        increase200Button = rootView.findViewById(R.id.increase200Button);
        increase500Button = rootView.findViewById(R.id.increase500Button);
        decreaseButton = rootView.findViewById(R.id.decreaseButton);
        // Sử dụng rootView để tìm các phần tử trong giao diện
        increase50Button.setOnClickListener(view -> updateProgress(50));
        increase200Button.setOnClickListener(view -> updateProgress(200));
        increase500Button.setOnClickListener(view -> updateProgress(500));
        decreaseButton.setOnClickListener(view -> updateProgress(PastWater));

        return rootView;
    }
    private void updateProgress(int increment) {
        // Lấy giá trị hiện tại của thanh tiến trình
        int currentProgress = progressBar.getProgress();
        if(PastWater!=increment)
            PastWater=-increment;
        // Tính giá trị mới, không vượt quá giới hạn 2000 hoặc dưới 0
        int newProgress = Math.max(0, Math.min(currentProgress + increment, 2000));

        // Cập nhật giá trị của thanh tiến trình
        progressBar.setProgress(newProgress);

        // Cập nhật giá trị của TextView
        txtProcess.setText(newProgress + "/2000ml");
        updateWater(newProgress);
    }
    private void ReadData(String email){
        DatabaseReference refa = FirebaseDatabase.getInstance().getReference("Daily").child(email.replace(".", ",")).child(LocalDate.now().toString());
        refa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    progressBar.setProgress(Integer.parseInt(String.valueOf(snapshot.child("water").getValue())));
                    txtProcess.setText(Integer.parseInt(String.valueOf(snapshot.child("water").getValue())) + "/2000ml");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void updateWater(int newProgress) {
        // Đường dẫn tới nút "water" trong Firebase

        DatabaseReference refa = FirebaseDatabase.getInstance().getReference("Daily").child(email.replace(".", ",")).child(LocalDate.now().toString()).child("water");

        // Cập nhật giá trị PastWater lên Firebase
        refa.setValue(newProgress);

    }
}
