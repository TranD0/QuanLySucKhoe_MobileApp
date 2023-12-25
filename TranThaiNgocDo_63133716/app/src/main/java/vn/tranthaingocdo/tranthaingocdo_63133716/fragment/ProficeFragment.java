package vn.tranthaingocdo.tranthaingocdo_63133716.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.tranthaingocdo.tranthaingocdo_63133716.R;

public class ProficeFragment extends Fragment
{
    private View rootView;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapteritem;
    String[] item = {"Gần như không làm gì, không đi đâu","Công việc văn phòng-học tập, không thể dục","Công việc văn phòng-học tập, thể dục nhẹ","Hoạt động thể chất vừa, thể dục nặng","Công việc nặng|tập thể thao nặng","Công việc siêu nặng"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profice, container, false);
    autoCompleteTextView = rootView.findViewById(R.id.autoCompleteTextView);
        adapteritem = new ArrayAdapter<String>(requireContext(), R.layout.dropdown_item, item);
    autoCompleteTextView.setAdapter(adapteritem);
    autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String item = parent.getItemAtPosition(position).toString();
        }
    });
        return rootView;
    }
}
