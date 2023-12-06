package vn.tranthaingocdo.tranthaingocdo_63133716;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView Register = (TextView) findViewById(R.id.txtRegister);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChuyenTrangLogin();
            }
        });
    }
    public void ChuyenTrangLogin(){
        Intent iManHinhKhac = new Intent(this, Register.class);
        startActivity(iManHinhKhac);
    }
}