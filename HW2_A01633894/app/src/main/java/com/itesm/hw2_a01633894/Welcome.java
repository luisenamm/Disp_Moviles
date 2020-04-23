package com.itesm.hw2_a01633894;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.intentssqlite.R;

import java.util.ArrayList;

public class Welcome extends AppCompatActivity {

    TextView welcome;
    Intent intent;
    RecyclerView recycler;
    DataAdapter adapter;
    ArrayList<String> userInfo;
    User user;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        intent = getIntent();
        welcome = (TextView)findViewById(R.id.welcomeTV);
        recycler = (RecyclerView)findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        db = new DBHelper(this);
        //GET USER
        user = db.getUserByNickname(intent.getStringExtra("message"));


        userInfo = new ArrayList<String>();


        welcome.setText("Bienvenido "+intent.getStringExtra("message"));

        userInfo.add("Nombre: "+user.name);
        userInfo.add("Apellido: "+user.last_name);
        userInfo.add("Cumpleaños: "+user.dob);
        userInfo.add("Rol: "+user.role);
        userInfo.add("Semestre: "+user.semester);
        userInfo.add("Usuario: "+user.nickname);

        adapter=new DataAdapter(userInfo);
        recycler.setAdapter(adapter);



    }
}