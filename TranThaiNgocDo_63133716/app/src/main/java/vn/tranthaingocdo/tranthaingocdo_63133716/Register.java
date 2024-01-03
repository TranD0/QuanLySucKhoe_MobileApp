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

public class Register extends AppCompatActivity {
    TextInputEditText edtEmail,edtPassword;
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
                String email,password;

                email = String.valueOf(edtEmail.getText());
                password = String.valueOf(edtPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    Toast.makeText(Register.this, "Account created", Toast.LENGTH_SHORT).show();


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