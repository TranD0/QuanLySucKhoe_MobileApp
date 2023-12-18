package vn.tranthaingocdo.tranthaingocdo_63133716;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    ImageView splashImg,logo;
    TextView appName;
    LottieAnimationView lottieAnimationView;   FirebaseAuth mAuth;
//    @Override
//    public void onStart() {
//        super.onStart();
//      FirebaseUser currentUser = mAuth.getCurrentUser();
//     if(currentUser != null){
//    ChuyenTrang(MenuActivity.class);
//    }
//        }
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    Introduction();
    }
    public void Introduction(){
                logo=findViewById(R.id.logo);
        appName=findViewById(R.id.app_name);
        splashImg=findViewById(R.id.img);
        lottieAnimationView=findViewById(R.id.lottie);
        //
        splashImg.animate().translationY(-1900).setDuration(1000).setStartDelay(3000);
        logo.animate().translationY(2000).setDuration(1000).setStartDelay(2990);
        appName.animate().translationY(1400).setDuration(1000).setStartDelay(2990);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(3000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                ChuyenTrang(Login.class);
            }
        }, 3580); // Chuyển sau 5 giây (thời gian của các animation + thời gian sleep trước đó)
    }
    public void ChuyenTrang(Class<?> cls){
        Intent intent = new Intent(getApplicationContext(), cls);
        startActivity(intent);
        finish();
    }

}