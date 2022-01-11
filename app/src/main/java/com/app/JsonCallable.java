package com.app;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

public class JsonCallable implements Callable<String> {

    private static final String TAG = "TAG_JasonCallable";
    private String uelStr;
    private String outputStr;

    public JsonCallable(String uelStr, String outputStr) {
        this.uelStr = uelStr;
        this.outputStr = outputStr;
    }

    @Override
    public String call() throws Exception {
        URL url = new URL(uelStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        try (
                OutputStream outputStream = connection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                PrintWriter printWriter = new PrintWriter(outputStreamWriter);
        ){
            printWriter.append(outputStr);
        } catch (Exception e){
            Log.e(TAG, e.toString());
        }

        final int responseCode = connection.getResponseCode();
        if (responseCode != 200){
            return null;
        }

        try (
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ){
            String line;
            String sum = "";
            while ((line = bufferedReader.readLine()) != null){
                sum = sum + line;
            }
            return sum;

        } catch (Exception e){
            Log.e(TAG, e.toString());
        }

        return null;
    }
}
