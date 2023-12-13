package vn.tranthaingocdo.tranthaingocdo_63133716;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
    TextInputEditText edtEmail,edtPassword;
    TextView txtRegister;
    Button btnLog;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            ChuyenTrang();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        edtEmail = findViewById(R.id.Email);
        edtPassword = findViewById(R.id.Password);
        btnLog = findViewById(R.id.btn_Login);
        progressBar = findViewById(R.id.progpressBar);
        txtRegister = findViewById(R.id.txtRegister);
        mAuth = FirebaseAuth.getInstance();
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
    });
    btnLog.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            progressBar.setVisibility(View.VISIBLE);
            String email,password;
            email = String.valueOf(edtEmail.getText());
            password = String.valueOf(edtPassword.getText());
            if(TextUtils.isEmpty(email)){
                Toast.makeText(Login.this, "Enter Email", Toast.LENGTH_SHORT).show();
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
            }
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                               ChuyenTrang();
                            } else {
                                Toast.makeText(Login.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    });
    }
    public void ChuyenTrang(){
        Intent intent =new Intent(getApplicationContext(),MenuActivity.class);
        startActivity(intent);
        finish();
    }

}