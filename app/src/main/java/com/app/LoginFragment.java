package com.app;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.app.member.MemberBean;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.concurrent.FutureTask;

public class LoginFragment extends Fragment {
    private static final String TAG = "TAG_Login";
    private Activity activity;
    private Button button;
    private TextInputEditText edUsername, edPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        login();
    }

    private void findViews(View view) {
        edUsername = view.findViewById(R.id.edUsername);
        edPassword = view.findViewById(R.id.edPassword);
        button = view.findViewById(R.id.button);
    }

    private void login() {
        button.setOnClickListener((view) ->{
            Editable username = edUsername.getText();
            Editable password = edPassword.getText();
            boolean error = false;
            if (username == null || username.length() == 0){
                edUsername.setError("帳號錯誤");
                error = true;
            }
            if (password == null || password.length() == 0){
                edPassword.setError("密碼錯誤");
                error = true;
            }

            if (error){
                return;
            }

            MemberBean memberBean = new MemberBean();
            memberBean.setUsername(String.valueOf(username));
            memberBean.setPassword(String.valueOf(password));

            Gson gson = new Gson();
            String memverStr = gson.toJson(memberBean);
            JsonCallable callable = new JsonCallable("http://192.168.150.110:8080/server/MemberController", memverStr);

            FutureTask<String> task = new FutureTask<>(callable);
            new Thread(task).start();
            try {
                final String respStr = task.get();
                MemberBean respMemberBean = gson.fromJson(respStr, MemberBean.class);
                if ("帳號或密碼錯誤".equals(respMemberBean.getMessage())){
                    Toast.makeText(activity, respMemberBean.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_loginFragment_to_resultFragment);
                    Toast.makeText(activity, respMemberBean.getNickname(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, e.toString() );
            }

        });
    }
}