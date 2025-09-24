package com.example.projekt2septembar;

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

public class Informacije extends AppCompatActivity {

    Button dugme;

    EditText broj, adresa;

    TextView cena, naziv, link;

    ImageView slika;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_informacije);

        postaljanje();
        nazad();
        kupi();

    }

    void postaljanje(){

        dugme = (Button) findViewById(R.id.narudzba);
        broj = (EditText) findViewById(R.id.broj);
        adresa = (EditText) findViewById(R.id.adresa);
        cena = (TextView) findViewById(R.id.cijena);
        naziv = (TextView) findViewById(R.id.naziv);
        link = (TextView) findViewById(R.id.nazad);
        slika = (ImageView) findViewById(R.id.slika);

        String ime = getIntent().getStringExtra("Naziv");
        String cijena = getIntent().getStringExtra("Cijena");
        int Slika = getIntent().getIntExtra("Slika", 0);

        naziv.setText(ime);
        cena.setText(cijena);
        slika.setImageResource(Slika);


    }

    void nazad(){

        link.setPaintFlags(link.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Informacije.this,Prodaja.class);
                startActivity(intent);
                finish();

            }
        });

    }

    void kupi(){

        dugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String naziv_patike = naziv.getText().toString();
                String cijena_patike = cena.getText().toString();
                String broj_telefona = broj.getText().toString();
                String broj_adrese = adresa.getText().toString();

                if(broj_telefona.isEmpty()){

                    broj.setError("Nijedno polje ne smije biti prazno!");
                    return;

                }

                if(broj_adrese.isEmpty()){

                    adresa.setError("Nijedno polje ne smije biti prazno!");
                    return;

                }

                ubacivanje(naziv_patike, cijena_patike, broj_telefona, broj_adrese);

            }
        });

    }

    void ubacivanje(String a, String b, String c , String d){

        HashMap<String, Object> mapa = new HashMap<>();
        mapa.put("Naziv kartice", a);
        mapa.put("Cijena kartice", b);
        mapa.put("Broj telefona", c);
        mapa.put("Broj adrese", d);

        FirebaseDatabase data =FirebaseDatabase.getInstance();
        DatabaseReference referenca = data.getReference("Mušterije");

        referenca.child("Podatci").child(a).setValue(mapa).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(Informacije.this, "Hvala vam na kupovini!", Toast.LENGTH_SHORT).show();
                broj.getText().clear();
                adresa.getText().clear();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Informacije.this, "Kupovina je bila neuspiješna!", Toast.LENGTH_SHORT).show();

            }
        });

    }

}