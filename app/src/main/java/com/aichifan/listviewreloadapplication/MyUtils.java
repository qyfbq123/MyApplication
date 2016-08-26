package com.aichifan.listviewreloadapplication;

import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by yoda on 16/8/25.
 */
public class MyUtils {

    public static InputStream request(String urlStr, String method, String params) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(TextUtils.isEmpty(method) ? "GET" : method);

            if(!TextUtils.isEmpty(params)) {
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                OutputStreamWriter wr = new OutputStreamWriter(os);
                wr.write(params);
                wr.close();
                os.close();
            }
            conn.setDoInput(true);
            return conn.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
