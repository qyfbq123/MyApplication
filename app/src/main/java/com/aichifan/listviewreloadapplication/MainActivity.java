package com.aichifan.listviewreloadapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String HOST = "http://192.168.0.102:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doRefresh();
    }

    public void refresh(View view) {
        doRefresh();
    }

    public void doRefresh() {
        new AsyncTask<String, Void, User[]>(){
            @Override
            public User[] doInBackground(String... params) {
                Gson gson = new Gson();
                InputStream is = MyUtils.request(HOST + "/users", null, null);
                if(is != null) {
                    User[] userArr = gson.fromJson(new InputStreamReader(is), User[].class);
                    Log.v("user arr length", Integer.toString(userArr.length));
                    return userArr;
                }
                return null;
            }

            public void onPostExecute(User[] result) {
                List<String> names = new ArrayList<String>();
                for(User u : result) {
                    names.add(u.getName());
                }
                ListView lv = (ListView) findViewById(R.id.listView);
                if(lv.getAdapter() != null) {

                    Log.v("adapter", "refresh");
                    UserAdapter adapter = (UserAdapter)lv.getAdapter();

                }
                else {
                    Log.v("adapter", "new");
                    UserAdapter adapter = new UserAdapter(MainActivity.this, R.layout.user_layout, result);

                    lv.setAdapter(adapter);
                    lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Toast.makeText(MainActivity.this, "长按效果", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });
                }
            }
        }.execute();
    }
}
