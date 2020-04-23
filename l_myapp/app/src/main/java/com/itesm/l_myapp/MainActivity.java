package com.itesm.l_myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    Button boton1,
            boton2;
    EditText edit1,
             edit2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton1=findViewById(R.id.boton1);
        boton2=findViewById(R.id.boton2);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle1=new Bundle();
                bundle1.putString("Name",String.valueOf(edit1.getText()));

                Intent myIntent =new Intent(getApplicationContext(),Main2Activity.class);
                myIntent.putExtras(bundle1);
                startActivity(myIntent);

            }
        });

        edit1=findViewById(R.id.editText);
        edit2=findViewById(R.id.editText2);


    }


}
