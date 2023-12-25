package vn.tranthaingocdo.tranthaingocdo_63133716;

import static vn.tranthaingocdo.tranthaingocdo_63133716.Register.profice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;

public class ProficeActivity extends AppCompatActivity {
    TextInputEditText edtWeight,edtHeight;
    Button button;
    RadioGroup radioGroup;
    RadioButton radioButton;String selectedGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profice);
        edtWeight = findViewById(R.id.YWeight);
        edtHeight = findViewById(R.id.YHieght);
         radioGroup = findViewById(R.id.radioGr);
         button =findViewById(R.id.buttonCof);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Lấy RadioButton được chọn từ ID
                radioButton = findViewById(checkedId);
                if (radioButton != null && radioButton.isChecked()) {
                     selectedGender = radioButton.getText().toString();
                }
            }
        });
        profice.setGender(selectedGender);
        profice.setHight( Float.parseFloat( edtHeight.getText().toString()) );
        profice.setWeight( Float.parseFloat( edtWeight.getText().toString()) );
    }
}