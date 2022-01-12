package com.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class ResultFragment extends Fragment {

    private static final String TAG = "TAG_BMI";
    private String text = "您的BMI值: ";

    private Activity activity;
    private Button btCount, btClean, btAboutBMI, btAboutBMIWeb, btLogout, btQuit;
    private EditText edheight, edweight;
    private TextView tvresult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        count();
        clean();
        aboutBMI();
        aboutBMIWeb();
        logout();
        quit();
    }

    private void count() {
        btCount.setOnClickListener(view -> {
            try {

                String sHeight = edheight.getText().toString();
                String sWeight = edweight.getText().toString();

                float jvaHeight = Float.parseFloat(sHeight) / 100;
                float jvaWeight = Float.parseFloat(sWeight);//轉換字串的浮點數，把edweight設定進去

                double fBmi = jvaWeight / Math.pow(jvaHeight, 2);
                Log.d(TAG, "bmi:" + fBmi);

                //捨去小數點bmi
                DecimalFormat formatter = new DecimalFormat("#.#");
                fBmi = Double.parseDouble(formatter.format(fBmi));
                Log.d(TAG, "捨去小數點bmi:" + fBmi);

//        tvresult.setText(String.valueOf(Math.floor(fBmi*10) / 10.0));

                if (fBmi >= 0 && fBmi < 18.5) {
                    tvresult.setText(text + fBmi + "\n過輕");
                } else if (fBmi >= 18.5 && fBmi < 24) {
                    tvresult.setText(text + fBmi + "\n正常");
                } else if (fBmi >= 24 && fBmi < 27) {
                    tvresult.setText(text + fBmi + "\n過重");
                } else if (fBmi >= 27 && fBmi < 30) {
                    tvresult.setText(text + fBmi + "\n輕度肥胖");
                } else if (fBmi >= 30 && fBmi < 35) {
                    tvresult.setText(text + fBmi + "\n中度肥胖");
                } else if (fBmi >= 35) {
                    tvresult.setText(text + fBmi + "\n重度肥胖");
                } else {
                    tvresult.setText("錯誤!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(activity, "輸入有誤!", Toast.LENGTH_SHORT).show();
                tvresult.setText("錯誤!");
            }
        });
    }

    private void clean() {
        btClean.setOnClickListener(view -> {
            edheight.setText(null);
            edweight.setText(null);
            tvresult.setText(null);
            Toast.makeText(activity, "清除輸入值", Toast.LENGTH_SHORT).show();
        });
    }

    private void aboutBMI() {
        btAboutBMI.setOnClickListener(view -> {
            new AlertDialog.Builder(activity)
                    .setTitle("關於BMI")
                    .setMessage("BMI原來的設計是一個用於公眾健康研究的統計工具。" +
                            "當需要知道肥胖是否為某一疾病的致病原因時，" +
                            "可以把病人的身高及體重換算成BMI，" +
                            "再找出其數值及病發率是否有線性關連。" +
                            "由於BMI主要反應整體體重，" +
                            "無法區別體重中體脂肪組織與非脂肪組織（包括肌肉、器官），" +
                            "同樣身高體重的人可算出相同的BMI，但其實脂肪量不同[1]，" +
                            "因此其實BMI是整體營養狀態的指標。以往拿來做為肥胖的指標，" +
                            "是因發現BMI與體脂肪在統計上有高度相關；但在同樣BMI之下，" +
                            "仍會有體脂肪率的差異。")
                    .setPositiveButton("OK", null)
                    .show();
        });
    }

    private void aboutBMIWeb() {
        btAboutBMIWeb.setOnClickListener(view -> {
            Intent intent = new Intent(activity, activity_web.class);
            startActivity(intent);
        });
    }

    private void logout() {
        btLogout.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.popBackStack();
            Toast.makeText(activity, "登出", Toast.LENGTH_SHORT).show();
        });

    }

    private void quit() {
        btQuit.setOnClickListener(view -> {
            activity.finish();
        });
    }

    private void findViews(View view) {
        edheight = view.findViewById(R.id.edHeight);
        edweight = view.findViewById(R.id.edWeight);
        tvresult = view.findViewById(R.id.tvResult);
        btCount = view.findViewById(R.id.btCount);
        btClean = view.findViewById(R.id.btClean);
        btAboutBMI = view.findViewById(R.id.btAboutBMI);
        btAboutBMIWeb = view.findViewById(R.id.btAboutBMIWeb);
        btLogout = view.findViewById(R.id.beLogout);
        btQuit = view.findViewById(R.id.btQuit);
    }
}