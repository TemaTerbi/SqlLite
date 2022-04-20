package com.example.sqlliteone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,phone,dateOfBth;
    Button insert, select, delete;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.txtName);
        phone = findViewById(R.id.txtNumber);
        dateOfBth = findViewById(R.id.txtDate);
        insert = findViewById(R.id.btnInsert);
        select = findViewById(R.id.btnSelect);
        delete = findViewById(R.id.btnDelete);
        databaseHelper = new DatabaseHelper(this);

        insert.setOnClickListener(view ->
                {
                   Boolean checkInsertData = databaseHelper.insert(name.getText().toString(),
                           phone.getText().toString(),dateOfBth.getText().toString());
                   if(checkInsertData)
                   {
                       Toast.makeText(getApplicationContext(),"Данные успешно добавлены",Toast.LENGTH_LONG).show();
                   }
                   else
                   {
                       Toast.makeText(getApplicationContext(),"Произошла ошибка",Toast.LENGTH_LONG).show();
                   }
                }
                );

        delete.setOnClickListener(view ->
                {
                    Boolean checkInsertData = databaseHelper.Delete(name.getText().toString());
                    if(checkInsertData)
                    {
                        Toast.makeText(getApplicationContext(),"Данные успешно удалены",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Произошла ошибка",Toast.LENGTH_LONG).show();
                    }
                }
        );

        select.setOnClickListener(view ->
        {

            Cursor res = databaseHelper.getdata();
            if(res.getCount() == 0)
            {
                Toast.makeText(getApplicationContext(),"Нет данных",Toast.LENGTH_LONG).show();
                return;
            }
            StringBuilder buffer = new StringBuilder();
            while (res.moveToNext()){
                buffer.append("Имя: ").append(res.getString(0)).append("\n");
                buffer.append("Телефон: ").append(res.getString(1)).append("\n");
                buffer.append("Дата рождения: ").append(res.getString(2)).append("\n");
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(true);
            builder.setMessage(buffer.toString());
            builder.show();
        });


    }
}