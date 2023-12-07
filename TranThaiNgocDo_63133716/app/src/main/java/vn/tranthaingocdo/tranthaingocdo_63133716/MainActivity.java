package vn.tranthaingocdo.tranthaingocdo_63133716;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button chuyển đăng nhập
        Button login = (Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChuyenTrangLogin();
            }
        });
    }

    public void ChuyenTrangLogin(){
        Intent iManHinhKhac = new Intent(this, Login.class);
        startActivity(iManHinhKhac);
    }
}