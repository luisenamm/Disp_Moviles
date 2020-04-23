package com.itesm.appfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText name,
            job,
            email,
            password,
            dob,
            role;


    String sname,
            sjob,
            semail,
            spassword,
            sdob,
            srole;

    Button button;

    FirebaseAuth auth;
    DatabaseReference mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        name=(EditText)findViewById(R.id.input_name);
        job=(EditText)findViewById(R.id.input_job);
        email=(EditText)findViewById(R.id.input_email);
        password=(EditText)findViewById(R.id.input_password);
        dob=(EditText)findViewById(R.id.input_dob);
        role=(EditText)findViewById(R.id.input_role);



        button=(Button)findViewById(R.id.button_send);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sname=name.getText().toString();
                sjob=job.getText().toString();
                semail=email.getText().toString();
                spassword=password.getText().toString();
                sdob=dob.getText().toString();
                srole=role.getText().toString();

                if(!sname.isEmpty() && ! sjob.isEmpty()&& !spassword.isEmpty() && !sdob.isEmpty() && ! srole.isEmpty()){
                    register();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Complete los datos",)
                }
            }
        });

        public void register(){
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener
        }


        auth=FirebaseAuth.getInstance();
        mydatabase= FirebaseDatabase.getInstance().getReference();
    }
}
