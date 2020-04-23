package com.itesm.hw1_a01633894;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public int vidas,
                rndm;

     Button btn1,
            btn2;
     EditText edText;
     TextView txt1,
            txt2;

    public void iniciar(){
        vidas=5;
        Random random=new Random();
        rndm=random.nextInt(101);
        edText.setText("");
        txt1.setText("Vidas: "+vidas);
        txt2.setText("Respuesta: ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn1=findViewById(R.id.boton1);
        btn2=findViewById(R.id.boton2);

        edText=findViewById(R.id.Number);

        txt1=findViewById(R.id.textView1);
        txt2=findViewById(R.id.textView2);

        iniciar();




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int number = Integer.parseInt(edText.getText().toString());
                    if (number < 0 || number > 100) {
                        Toast.makeText(getApplicationContext(), "Entre 0 y 100", Toast.LENGTH_LONG).show();
                    } else {
                        if (number == rndm) {
                            edText.setText("Correcto ¡Ganaste!");
                            txt2.setText("Respuesta: " + rndm);
                        } else {

                            vidas -= 1;

                            if (vidas <= 0) {
                                txt2.setText("Respuesta: " + rndm);
                                edText.setText("Perdiste");
                                txt1.setText("Vidas: 0");
                            } else {
                                txt1.setText("Vidas: " + vidas);
                                if (number > rndm) {
                                    Toast.makeText(getApplicationContext(), "Más pequeño", Toast.LENGTH_LONG).show();

                                }
                                if (number < rndm) {
                                    Toast.makeText(getApplicationContext(), "Más grande", Toast.LENGTH_LONG).show();
                                }
                            }

                        }
                    }
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Ingresa un entero", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciar();
            }
        });
    }

}
