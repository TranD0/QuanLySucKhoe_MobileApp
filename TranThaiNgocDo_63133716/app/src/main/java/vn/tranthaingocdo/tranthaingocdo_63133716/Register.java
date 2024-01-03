package vn.tranthaingocdo.tranthaingocdo_63133716;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;

public class Register extends AppCompatActivity {
    TextInputEditText edtEmail,edtPassword,edtCofPass;private FirebaseDatabase db;private DatabaseReference ref,ref1,ref2;
    TextView txtLogin;
    Button btnReg;
    FirebaseAuth mAuth;
    ImageView progressBar;
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            ChuyenTrang();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        edtCofPass = findViewById(R.id.CofPass);
        edtEmail = findViewById(R.id.RegEmail);
        edtPassword = findViewById(R.id.RegPass);
        btnReg =  findViewById(R.id.buttonReg);
        progressBar = findViewById(R.id.progpressBar);
        txtLogin=findViewById(R.id.txtLoginNow);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email,password,cofpassword;

                email = String.valueOf(edtEmail.getText());
                password = String.valueOf(edtPassword.getText());
                cofpassword = String.valueOf(edtCofPass.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(cofpassword)){
                    Toast.makeText(Register.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                }
                if(!password.equals(cofpassword)){ Toast.makeText(Register.this, "Wrong Password", Toast.LENGTH_SHORT).show();}
                else
                    mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Tạo database Daily
                                    Daily daily = new Daily(0, 0, LocalDate.now().toString(), 0);
                                    db = FirebaseDatabase.getInstance();
                                    ref = db.getReference("Daily").child(email.replace(".", ","));
                                    ref.child(LocalDate.now().toString()).setValue(daily).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> dailyTask) {
                                            if (dailyTask.isSuccessful()) {
                                                // Tạo database Users
                                                Profice profile = new Profice("FirstName", "LastName", email, "unknow", 50, 150, 1000);
                                                ref1 = db.getReference("Users");
                                                ref1.child(email.replace(".", ",")).setValue(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> profileTask) {
                                                        if (profileTask.isSuccessful()) {
                                                            // Tạo database ListFood
                                                            ref2 = db.getReference("ListFood").child(email.replace(".", ","));
                                                            ref2.child("1 chén cơm (100gr)").setValue(130).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> foodTask) {
                                                                    if (foodTask.isSuccessful()) {
                                                                        Toast.makeText(Register.this, "Account created", Toast.LENGTH_SHORT).show();
                                                                        ChuyenTrang(Login.class);
                                                                    } else {
                                                                        Toast.makeText(Register.this, "Failed to create ListFood", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }
                                                            });
                                                        } else {
                                                            Toast.makeText(Register.this, "Failed to create Users", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                            } else {
                                                Toast.makeText(Register.this, "Failed to create Daily", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(Register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
            }
        });
    }
    public void ChuyenTrang(Class<?> cls){
        Intent intent = new Intent(getApplicationContext(), cls);
        startActivity(intent);
        finish();
    }

}