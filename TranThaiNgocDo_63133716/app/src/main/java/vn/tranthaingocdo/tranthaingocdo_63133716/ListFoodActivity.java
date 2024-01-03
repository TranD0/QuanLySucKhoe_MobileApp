package vn.tranthaingocdo.tranthaingocdo_63133716;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;

import vn.tranthaingocdo.tranthaingocdo_63133716.fragment.CaloFragment;
import vn.tranthaingocdo.tranthaingocdo_63133716.fragment.HomeFragment;

public class ListFoodActivity extends AppCompatActivity {
    ArrayList<Food> foods; FoodAdater adater; TextInputEditText edtname,edtcalo;
    Button button;
    FirebaseAuth auth;String email;    FirebaseUser User; private FirebaseDatabase db;private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food);
        edtname=findViewById(R.id.FoodName);
        edtcalo=findViewById(R.id.FCalo);
        button =findViewById(R.id.buttonAddF);
        //lay email
        auth = FirebaseAuth.getInstance();
        User = auth.getCurrentUser();
        email = User.getEmail(); foods = new ArrayList<>();
        ReadData(email.replace(".", ","));
        //
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db= FirebaseDatabase.getInstance();
                ref=db.getReference("ListFood").child(email.replace(".", ","));
                ref.child(edtname.getText().toString()).setValue(Float.parseFloat(edtcalo.getText().toString())).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        ChuyenTrang(CaloFragment.class);
                    }
                });
            }
        });


        ListView lvPK = findViewById(R.id.lvPokemon);
         adater = new FoodAdater(foods,this);
        lvPK.setAdapter(adater);
        lvPK.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Float item=foods.get(position).getCalo();
                Toast.makeText(ListFoodActivity.this, item.toString(), Toast.LENGTH_SHORT).show();
                updateCalo(item);
            }
        });

    }
    private void ReadData(String email){
        DatabaseReference refa = FirebaseDatabase.getInstance().getReference("ListFood").child(email.replace(".", ","));
        refa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Lấy tên của child
                        String foodName = snapshot.getKey();

                        // Lấy giá trị của child và thêm vào danh sách foods
                        Long foodCaloriesLong = snapshot.getValue(Long.class);
                        if (foodCaloriesLong != null) {
                            float foodCalories = foodCaloriesLong.floatValue();

                            foods.add(new Food(foodName, foodCalories));
                        }


                    }
                    adater.notifyDataSetChanged();
                }
                else {

                    db= FirebaseDatabase.getInstance();
                    ref=db.getReference("ListFood").child(email.replace(".", ","));
                    ref.child("1 chén cơm (100gr)").setValue(130).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            ChuyenTrang(Login.class);
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListFoodActivity.this, String.valueOf(databaseError), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void ChuyenTrang(Class<?> cls){
        Intent intent = new Intent(getApplicationContext(), cls);
        startActivity(intent);
        finish();
    }
    private void updateCalo(float newProgress) {

        DatabaseReference refa = FirebaseDatabase.getInstance().getReference("Daily").child(email.replace(".", ",")).child(LocalDate.now().toString()).child("calo");
        refa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Long currentValue = dataSnapshot.getValue(Long.class);
                    if (currentValue != null) {
                        // Cộng thêm 1 vào giá trị hiện tại của count
                        float updatedValue = currentValue + newProgress;

                        // Đặt giá trị mới cho count
                        refa.setValue(updatedValue);
                    }
                } else {
                    // Nếu count chưa tồn tại, tạo mới count với giá trị 1
                    refa.setValue(newProgress);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference refcount = FirebaseDatabase.getInstance().getReference("Daily").child(email.replace(".", ",")).child(LocalDate.now().toString()).child("count");
        refcount.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Long currentValue = dataSnapshot.getValue(Long.class);
                    if (currentValue != null) {
                        // Cộng thêm 1 vào giá trị hiện tại của count
                        long updatedValue = currentValue + 1;

                        // Đặt giá trị mới cho count
                        refcount.setValue(updatedValue);
                    }
                } else {
                    // Nếu count chưa tồn tại, tạo mới count với giá trị 1
                    refcount.setValue(1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}