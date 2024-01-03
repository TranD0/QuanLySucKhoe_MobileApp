package vn.tranthaingocdo.tranthaingocdo_63133716;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class ProficeActivity extends AppCompatActivity {
    TextInputEditText edtWeight,edtHeight,edtFname,edtLname,edtYear;
    Button button;  private FirebaseDatabase db;private DatabaseReference ref;
    RadioGroup radioGroup;   FirebaseAuth auth;String email;    FirebaseUser User;
    RadioButton radioButton;String selectedGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profice);
        edtLname = findViewById(R.id.RegLN);
        edtFname = findViewById(R.id.RegFN);
        edtWeight = findViewById(R.id.YWeight);
        edtHeight = findViewById(R.id.YHieght);
        edtYear = findViewById(R.id.YY);
         radioGroup = findViewById(R.id.radioGr);
         button =findViewById(R.id.buttonCof);
        auth = FirebaseAuth.getInstance();
        User = auth.getCurrentUser();
        email = User.getEmail();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Lấy RadioButton được chọn từ ID
                radioButton = findViewById(checkedId);
                if (radioButton != null && radioButton.isChecked()) {
                    selectedGender = radioButton.getText().toString();
                }
            }
        });
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 try{
                 Profice profice = new Profice(String.valueOf(edtFname.getText()),String.valueOf(edtLname.getText()),String.valueOf(email.replace(".", ",")),selectedGender,(float)Float.parseFloat( edtWeight.getText().toString()),(float) Float.parseFloat( edtHeight.getText().toString()),Integer.parseInt(edtYear.getText().toString()));
                 db= FirebaseDatabase.getInstance();
                 ref=db.getReference("Users");
                 ref.child(email.replace(".", ",")).setValue(profice).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         Toast.makeText(ProficeActivity.this, "success", Toast.LENGTH_SHORT).show();
                         ChuyenTrang(Login.class);
                     }
                 });}
                 catch (Exception e) {
                     e.printStackTrace();
                     Toast.makeText(ProficeActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                 }
             }
         });

    }
    public void ChuyenTrang(Class<?> cls){
        Intent intent = new Intent(getApplicationContext(), cls);
        startActivity(intent);
        finish();
    }
}