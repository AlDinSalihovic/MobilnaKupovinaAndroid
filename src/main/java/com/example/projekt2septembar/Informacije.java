package com.example.projekt2september;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Informations extends AppCompatActivity {

    Button Button;

    EditText Number, Adress;

    TextView Price, Name, Link;

    ImageView Picture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_informations);

        puting_info();
        back();
        buying();

    }

    void puting_info(){

        Button = (Button) findViewById(R.id.order);
        Number = (EditText) findViewById(R.id.number);
        Adress = (EditText) findViewById(R.id.adress);
        Price = (TextView) findViewById(R.id.price);
        Name = (TextView) findViewById(R.id.name);
        Link = (TextView) findViewById(R.id.back);
        Picture = (ImageView) findViewById(R.id.pic);

        String name = getIntent().getStringExtra("Name");
        String price = getIntent().getStringExtra("Price");
        int picture = getIntent().getIntExtra("Picture", 0);

        name.setText(Name);
        price.setText(Price);
        picture.setImageResource(Picture);


    }

    void back(){

        link.setPaintFlags(link.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Informations.this,Selling.class);
                startActivity(intent);
                finish();

            }
        });

    }

    void buying(){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sneaker_name = Name.getText().toString();
                String sneaker_price = Price.getText().toString();
                String phone_number = Number.getText().toString();
                String adress_num = Adress.getText().toString();

                if(phone_number.isEmpty()){

                    Number.setError("No field can be empty!");
                    return;

                }

                if(adress_num.isEmpty()){

                    Adress.setError("No field can be empty!");
                    return;

                }

                puting_info1(sneaker_name, sneaker_price, phone_number, adress_num);

            }
        });

    }

    void puting_info1(String a, String b, String c , String d){

        HashMap<String, Object> map = new HashMap<>();
        map.put("Card name", a);
        map.put("Card price", b);
        map.put("Phone number", c);
        map.put("Adress number", d);

        FirebaseDatabase data =FirebaseDatabase.getInstance();
        DatabaseReference referenca = data.getReference("Customers");

        referenca.child("Data").child(a).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(Informations.this, "Thanks for shoping!", Toast.LENGTH_SHORT).show();
                Number.getText().clear();
                Adress.getText().clear();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Informations.this, "Shoping was unsuccsesfull!", Toast.LENGTH_SHORT).show();

            }
        });

    }

}