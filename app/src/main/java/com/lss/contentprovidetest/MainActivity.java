package com.lss.contentprovidetest;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_name;
    private EditText et_phone;
    private Button btn_insert;
    private Button btn_update;
    private Button btn_delete;
    private Button btn_query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name= (EditText) findViewById(R.id.et_name);
        et_phone= (EditText) findViewById(R.id.et_phone);
        btn_insert= (Button) findViewById(R.id.btn_insert);
        btn_delete= (Button) findViewById(R.id.btn_delete);
        btn_query= (Button) findViewById(R.id.btn_query);
        btn_update= (Button) findViewById(R.id.btn_update);
        btn_insert.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_insert:
                String path_insert="content://com.lss.readcontactmassage.PersonDBProvider/insert";
                Uri uri_insert = Uri.parse(path_insert);
                ContentResolver contentResolver_insert = getContentResolver();
                String name = et_name.getText().toString();
                String phone = et_phone.getText().toString();
                if (name.isEmpty()||phone.isEmpty()){
                    Toast.makeText(this,"输入姓名或号码",Toast.LENGTH_SHORT).show();
                }else {
                    ContentValues values = new ContentValues();
                    values.put("name",name);
                    values.put("number",phone);
                    contentResolver_insert.insert(uri_insert,values);
                    Toast.makeText(this,"插入成功",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_update:
                String path_update="content://com.lss.readcontactmassage.PersonDBProvider/update";
                Uri uri_update = Uri.parse(path_update);
                ContentResolver contentResolver_update = getContentResolver();
                String name_update = et_name.getText().toString();
                String phone_update = et_phone.getText().toString();
                if (name_update.isEmpty()||phone_update.isEmpty()){
                    Toast.makeText(this,"输入姓名或号码",Toast.LENGTH_SHORT).show();
                }else {
                    ContentValues values = new ContentValues();
                    values.put("number",phone_update);
                    int update = contentResolver_update.update(uri_update, values, "name=?", new String[]{name_update});
                    if (update>0) {
                        Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "更新失败", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_query:
                Intent intent = new Intent(MainActivity.this, QueryUI.class);
                startActivity(intent);
                break;
            case R.id.btn_delete:
                String path_delete="content://com.lss.readcontactmassage.PersonDBProvider/delete";
                Uri uri_delete = Uri.parse(path_delete);
                ContentResolver contentResolver_delete = getContentResolver();
                String name_delete = et_name.getText().toString();
                if (name_delete.isEmpty()){
                    Toast.makeText(this,"输入姓名",Toast.LENGTH_SHORT).show();
                }else {
                    int delete = contentResolver_delete.delete(uri_delete,"name=?",new String[]{name_delete});
                    if (delete>0) {
                        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
