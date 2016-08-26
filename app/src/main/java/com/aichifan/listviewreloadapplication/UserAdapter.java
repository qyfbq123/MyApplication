package com.aichifan.listviewreloadapplication;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yoda on 16/8/25.
 */
public class UserAdapter extends ArrayAdapter<User> {
    private Activity context;

    List<User> userList;
    public UserAdapter(Activity context, int resource, List<User> userList) {
        super(context, resource, userList);
        this.context = context;
        this.userList = userList;
    }

    public UserAdapter(Activity context, int resource, User[] userArr) {
        super(context, resource, userArr);
        this.context = context;
        this.userList = Arrays.asList(userArr);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.user_layout, parent, false);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView firstLine = (TextView) rowView.findViewById(R.id.firstLine);
        TextView secondLine = (TextView) rowView.findViewById(R.id.secondLine);


        User user = userList.get(position);
        if(!TextUtils.isEmpty(user.getPhoto())) {
            new GetImageTask(imageView).execute(user.getPhoto());
        }
        Log.v("user tel", user.getTel());
        firstLine.setText(user.getName());
        secondLine.setText(user.getTel());
        return rowView;
    }
}
