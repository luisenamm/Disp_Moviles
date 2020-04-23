package com.itesm.hw2_a01633894;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intentssqlite.R;

public class MainActivity extends AppCompatActivity {


    DBHelper db;
    EditText name, lastName, dob, role, semester, nickname, password;
    Button registerButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        name = (EditText)findViewById(R.id.nameRField);
        lastName = (EditText)findViewById(R.id.lastNameRField);
        dob = (EditText)findViewById(R.id.dobRField);
        role = (EditText)findViewById(R.id.roleRField);
        semester = (EditText)findViewById(R.id.semesterRField);
        nickname = (EditText)findViewById(R.id.nicknameRField);
        password = (EditText)findViewById(R.id.passwordRField);

        registerButton = (Button) findViewById(R.id.registerButton);
        loginButton = (Button) findViewById(R.id.loginLButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entryName = name.getText().toString();
                String entryLastName = lastName.getText().toString();
                String entryDOB = dob.getText().toString();
                String entryRole = role.getText().toString();
                String entrySemester = semester.getText().toString();
                String entryNickname = nickname.getText().toString();
                String entryPassword = password.getText().toString();


                if(entryName.equals("") || entryLastName.equals("") || entryDOB.equals("") ||  entryRole.equals("")
                        || entrySemester.equals("") || entryNickname.equals("") || entryPassword.equals("")){
                    Toast.makeText(getApplicationContext(), "Llene todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    User user = new User(entryName, entryLastName, entryDOB, entryRole, Integer.parseInt(entrySemester), entryNickname,
                            entryPassword);
                    Boolean insert = db.insertUser(user);
                    if(insert == true) {
                        Toast.makeText(getApplicationContext(), "Se registró el usuario", Toast.LENGTH_SHORT).show();
                    }
                }




            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });


    }
}