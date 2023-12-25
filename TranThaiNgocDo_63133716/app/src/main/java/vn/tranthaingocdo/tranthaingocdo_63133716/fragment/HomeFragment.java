package vn.tranthaingocdo.tranthaingocdo_63133716.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.tranthaingocdo.tranthaingocdo_63133716.MenuActivity;
import vn.tranthaingocdo.tranthaingocdo_63133716.R;

public class HomeFragment extends Fragment {
    private View rootView;
    LinearLayout waterpg;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_home, container, false);

    }

}
