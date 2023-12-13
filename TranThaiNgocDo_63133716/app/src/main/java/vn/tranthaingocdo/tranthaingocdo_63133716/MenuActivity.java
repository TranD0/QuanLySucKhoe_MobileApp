package vn.tranthaingocdo.tranthaingocdo_63133716;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button btnLogout;
    TextView userName;
    FirebaseUser User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        auth = FirebaseAuth.getInstance();
        btnLogout = findViewById(R.id.logout);
        userName = findViewById(R.id.user_details);
        User =auth.getCurrentUser();
        if(User==null){
            Intent intent =new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }else{
            userName.setText(User.getEmail());
        }
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent =new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}