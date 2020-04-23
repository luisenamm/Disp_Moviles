package com.itesm.ex1_a01633894;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.intentssqlite.R;

public class MainActivity extends AppCompatActivity {

    DBHelper db;

    EditText question;
    Button a1,
            a2,
            a3,
            a4;
    Question
            q1=new Question(0,"0+1","1","0","-1","4","1"),
            q2=new Question(1,"2+2=","1","2","3","4","4"),
            q3=new Question(2,"21+22=","41","52","43","58","43"),
            q4=new Question(3,"25+35=","70","72","71","73","70"),
            q5=new Question(4,"100*2","220","102","200","202","200"),
            q6=new Question(5,"30+(40/2)","35","50","55","60","50"),
            q7=new Question(6,"(30+40)/2)","35","50","55","60","35"),
            q8=new Question(7,"400+1750","2150","215","2151","2051","2150"),
            q9=new Question(8,"400-150","200","250","300","350","250"),
            q10=new Question(9,"450*2","300","900","452","448","900");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new DBHelper(this);

        question=(EditText)findViewById(R.id.question);
        a1=(Button)findViewById(R.id.ans1);
        a2=(Button)findViewById(R.id.ans2);
        a3=(Button)findViewById(R.id.ans3);
        a4=(Button)findViewById(R.id.ans4);

        db.insertQuestion(q1);
        db.insertQuestion(q2);
        db.insertQuestion(q3);
        db.insertQuestion(q4);
        db.insertQuestion(q5);
        db.insertQuestion(q6);
        db.insertQuestion(q7);
        db.insertQuestion(q8);
        db.insertQuestion(q9);
        db.insertQuestion(q10);


        for (int i=0;i<10;i++) {
             db.getQuestionById(i);


        }

        question.setText(q1.getQuestion());
        a1.setText(q1.getAns_1());
        a2.setText(q1.getAns_2());
        a3.setText(q1.getAns_3());
        a4.setText(q1.getAns_4());

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Congratulations.class);
                startActivity(intent);
            }
        });

    }
}
