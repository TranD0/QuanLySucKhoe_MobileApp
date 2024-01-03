package vn.tranthaingocdo.tranthaingocdo_63133716.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;

import vn.tranthaingocdo.tranthaingocdo_63133716.ProficeActivity;
import vn.tranthaingocdo.tranthaingocdo_63133716.R;

public class ProficeFragment extends Fragment
{
    private View rootView; FirebaseAuth auth;   FirebaseUser User;private FirebaseDatabase db;private DatabaseReference ref;
    AutoCompleteTextView autoCompleteTextView;String email; int selectedNumber = -1;Float BMRSave;
    ArrayAdapter<String> adapteritem;  Button button; TextView Fname,Lname,Gender,Height,Weight,BMI,Year,BMR,Calo;
    String[] item = {"Gần như không làm gì, không đi đâu","Công việc văn phòng-học tập, không thể dục","Công việc văn phòng-học tập, thể dục nhẹ","Hoạt động thể chất vừa, thể dục nhẹ","Công việc nặng|tập thể thao nặng","Công việc siêu nặng"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profice, container, false);
    autoCompleteTextView = rootView.findViewById(R.id.autoCompleteTextView);
    button=  rootView.findViewById(R.id.button);
        adapteritem = new ArrayAdapter<String>(requireContext(), R.layout.dropdown_item, item);
    autoCompleteTextView.setAdapter(adapteritem);
    Fname = rootView.findViewById(R.id.UF);
    Lname = rootView.findViewById(R.id.UL);
    Gender = rootView.findViewById(R.id.UG);
    Height = rootView.findViewById(R.id.UH);
    Weight = rootView.findViewById(R.id.UW);
    Year = rootView.findViewById(R.id.UA);
        BMI= rootView.findViewById(R.id.BMI);
        BMR= rootView.findViewById(R.id.BMR);
        Calo= rootView.findViewById(R.id.Calo);

        //
        auth = FirebaseAuth.getInstance();
        User = auth.getCurrentUser();
        email = User.getEmail();
            ReadData(email.replace(".", ","));


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChuyenTrang(ProficeActivity.class);
            }
        });
    autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String item = parent.getItemAtPosition(position).toString();
            float selectedNumber = -1; // Gán giá trị mặc định

            // Sử dụng if-else
            if (item.equals("Gần như không làm gì, không đi đâu")) {
                selectedNumber = 1.2f;
            } else if (item.equals("Công việc văn phòng-học tập, không thể dục")) {
                selectedNumber = 1.29f;
            } else if (item.equals("Công việc văn phòng-học tập, thể dục nhẹ")) {
                selectedNumber = 1.375f;
            }
            else if (item.equals("Hoạt động thể chất vừa, thể dục nhẹ")) {
                selectedNumber = 1.63f;
            }

            else if (item.equals("Công việc nặng|tập thể thao nặng")) {
                selectedNumber = 1.86f;
            }
            else if (item.equals("Công việc siêu nặng")) {
                selectedNumber = 2.0f;
            }

            Calo.setText(String.valueOf(selectedNumber*BMRSave));
        }
    });
        return rootView;
    }
private void ReadData(String email){
    ref=FirebaseDatabase.getInstance().getReference("Users");
    ref.child(email).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DataSnapshot> task) {
            if(task.isSuccessful()){
                if(task.getResult().exists()){
                    DataSnapshot dataSnapshot = task.getResult();
                    String Fn = String.valueOf(dataSnapshot.child("fname").getValue());
                    String Ln = String.valueOf(dataSnapshot.child("lname").getValue());
                    String Hig = String.valueOf(dataSnapshot.child("hight").getValue());
                    String Wei = String.valueOf(dataSnapshot.child("weight").getValue());
                    String gen = String.valueOf(dataSnapshot.child("gender").getValue());
                    String Yea = String.valueOf(dataSnapshot.child("year").getValue());
                    int currentYear = LocalDate.now().getYear();
                    int Age =currentYear-Integer.parseInt(Yea);
                    Fname.setText(Fn);
                    Lname.setText(Ln);
                    Gender.setText(gen);
                    Height.setText(Hig);
                    Weight.setText(Wei);
                    Year.setText(String.valueOf(Age));
                    float KQBMR=0;
                    //66 + (13.7 x Cân nặng kg) + (5 x Chiều cao cm) – (6.8 x tuổi).
                    if(gen.equals("Male"))
                        KQBMR=(float)  66 + 13.7f * Float.parseFloat(Wei) + 5* Float.parseFloat(Hig)- 6.8f*(float)Age;
                    else
                        //655 + (9.6 x Cân nặng kg) + (1.8 x Chiều cao cm) – (4.7 x tuổi).
                        KQBMR=(float)  655 + 9.6f * Float.parseFloat(Wei) + 1.8f* Float.parseFloat(Hig)- 4.7f*(float)Age;
                    float KQBMI= Float.parseFloat(Wei)/(( Float.parseFloat(Hig)/100)*( Float.parseFloat(Hig)/100));
                    BMRSave=KQBMR;
                    BMI.setText( String.valueOf(KQBMI));
                    BMR.setText( String.valueOf(KQBMR));
                }
            }
        }
    });
}
    public void ChuyenTrang(Class<?> cls) {
        Intent intent = new Intent(requireActivity(), cls);
        startActivity(intent);
    }

}
