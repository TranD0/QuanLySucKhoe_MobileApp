package vn.tranthaingocdo.tranthaingocdo_63133716;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Context context =Login.this;
    EditText emailNhap = (EditText) findViewById(R.id.Email);
    String email = emailNhap.getText().toString();
    EditText passwordNhap = (EditText) findViewById(R.id.Password);
    String password = passwordNhap.getText().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView Register = (TextView) findViewById(R.id.txtRegister);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //button chuyển đăng nhập
        Button login = (Button) findViewById(R.id.btn_Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              DangNhap();
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChuyenTrangLogin();
            }
        });
    }
public void DangNhap(){
    mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        ChuyenTrangMenu();
                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(context, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
}

    public void ChuyenTrangLogin(){
        Intent iManHinhKhac = new Intent(this, Register.class);
        startActivity(iManHinhKhac);
    }
    public void ChuyenTrangMenu(){
        Intent iManHinhKhac = new Intent(this, MenuActivity.class);
        startActivity(iManHinhKhac);
    }
}