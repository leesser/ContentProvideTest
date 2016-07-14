package com.lss.contentprovidetest;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuai on 16-7-14.
 */
public class QueryUI extends AppCompatActivity {
    private ListView list_view;
    private List<Person> persons;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        String path_query="content://com.lss.readcontactmassage.PersonDBProvider/query";
        Uri uri_query = Uri.parse(path_query);
        ContentResolver contentResolver_query = getContentResolver();
        Cursor cursor = contentResolver_query.query(uri_query, null, null, null, null);
        persons=new ArrayList<Person>();
        if (cursor==null){
            return;
        }
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name1 = cursor.getString(cursor.getColumnIndex("name"));
            String number = cursor.getString(cursor.getColumnIndex("number"));
            Person person = new Person(id, name1, number);
            persons.add(person);
        }
        cursor.close();
        list_view= (ListView) findViewById(R.id.list_view);
        list_view.setAdapter(new MyAdapter());
    }
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return persons.size();
        }

        @Override
        public Object getItem(int position) {
            return persons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Person person = persons.get(position);
            View view = View.inflate(QueryUI.this, R.layout.list_item, null);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_name.setText("姓名:"+person.getName());
            TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
            tv_phone.setText("电话:"+person.getNumber());
            return view;
        }
    }
}
