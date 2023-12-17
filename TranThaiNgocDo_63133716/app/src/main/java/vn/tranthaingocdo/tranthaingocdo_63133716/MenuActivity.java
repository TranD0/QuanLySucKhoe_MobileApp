package vn.tranthaingocdo.tranthaingocdo_63133716;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import vn.tranthaingocdo.tranthaingocdo_63133716.fragment.HomeFragment;
import vn.tranthaingocdo.tranthaingocdo_63133716.fragment.ProficeFragment;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int FRAGMENT_HOME=0;
    private static final int FRAGMENT_PROFICE=1;
    private int currentFragment = FRAGMENT_HOME;
    FirebaseAuth auth;

    TextView userName;
    FirebaseUser User;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);  getSupportActionBar().setTitle("Home");
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
    drawerLayout.addDrawerListener(drawerToggle);
    drawerToggle.syncState();
   // xử lý khi login
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.home).setChecked(true);
        //kiểm tra tên user
    auth = FirebaseAuth.getInstance();
        View headerView = navigationView.getHeaderView(0);
        userName = headerView.findViewById(R.id.user_details);
        User = auth.getCurrentUser();
        if (User == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            String userEmail = User.getEmail();
            if (userEmail != null) {
                userName.setText(userEmail);
            } else {
                userName.setText("No email available");
            }}

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
            if (id == R.id.home) {
                if(currentFragment!=FRAGMENT_HOME){
                    replaceFragment(new HomeFragment());
                    currentFragment = FRAGMENT_HOME;
                    getSupportActionBar().setTitle("Home");
                }
            }
            else if(id == R.id.nav_profile){
                if(currentFragment!=FRAGMENT_PROFICE){
                    replaceFragment(new ProficeFragment());
                    currentFragment = FRAGMENT_PROFICE;
                    getSupportActionBar().setTitle("Profice");
                }
            }
            else if (id == R.id.LogOut) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        private void replaceFragment(Fragment fragment){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame,fragment);
            transaction.commit();
        }
    }
