package vn.tranthaingocdo.tranthaingocdo_63133716.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.tranthaingocdo.tranthaingocdo_63133716.R;

public class WaterFragment extends Fragment  {
    private ProgressBar progressBar;
    TextView txtProcess; // Khai báo biến View để lưu trữ giao diện của Fragment
    private View rootView;

    private Button increase50Button, increase200Button, increase500Button, decrease50Button, decrease200Button, decrease500Button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout cho Fragment và lưu trữ vào biến rootView
        rootView = inflater.inflate(R.layout.fragment_water, container, false);

        txtProcess = rootView.findViewById(R.id.TxtProcess);
        txtProcess.setText("0/2000ml");
        progressBar = rootView.findViewById(R.id.progressBar);
        increase50Button = rootView.findViewById(R.id.increase50Button);
        increase200Button = rootView.findViewById(R.id.increase200Button);
        increase500Button = rootView.findViewById(R.id.increase500Button);
        decrease50Button = rootView.findViewById(R.id.decrease50Button);
        decrease200Button = rootView.findViewById(R.id.decrease200Button);
        decrease500Button = rootView.findViewById(R.id.decrease500Button);

        // Sử dụng rootView để tìm các phần tử trong giao diện

        increase50Button.setOnClickListener(view -> updateProgress(50));
        increase200Button.setOnClickListener(view -> updateProgress(200));
        increase500Button.setOnClickListener(view -> updateProgress(500));
        decrease50Button.setOnClickListener(view -> updateProgress(-50));
        decrease200Button.setOnClickListener(view -> updateProgress(-200));
        decrease500Button.setOnClickListener(view -> updateProgress(-500));

        return rootView;
    }
    private void updateProgress(int increment) {
        // Lấy giá trị hiện tại của thanh tiến trình
        int currentProgress = progressBar.getProgress();

        // Tính giá trị mới, không vượt quá giới hạn 2000 hoặc dưới 0
        int newProgress = Math.max(0, Math.min(currentProgress + increment, 2000));

        // Cập nhật giá trị của thanh tiến trình
        progressBar.setProgress(newProgress);

        // Cập nhật giá trị của TextView
        txtProcess.setText(newProgress + "/2000ml");
    }
}
