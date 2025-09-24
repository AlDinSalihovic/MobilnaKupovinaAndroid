package com.example.projekt2septembar;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Prodaja extends AppCompatActivity implements interfejs {
    ArrayList<Nazivi> Patike = new ArrayList<Nazivi>();

    int[] Slike = {R.drawable.adidas, R.drawable.armour, R.drawable.ballance, R.drawable.fila,
    R.drawable.lacoste, R.drawable.nike, R.drawable.sketchers, R.drawable.polo};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prodaja);

        postava();
        postavka();
        nazad();
        }


    void postava(){

        RecyclerView recyclerView = findViewById(R.id.podatciRecyclerView);
        AdapterKlasa adapterKlasa = new AdapterKlasa(this, Patike, this);
        recyclerView.setAdapter(adapterKlasa);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Prostor prostor = new Prostor(5);
        recyclerView.addItemDecoration(prostor);

    }

    void nazad(){

        TextView nazadNaLogin = (TextView) findViewById(R.id.nazad);
        nazadNaLogin.setPaintFlags(nazadNaLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        nazadNaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Prodaja.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private void postavka(){

    String[] imena = getResources().getStringArray(R.array.Nazivi);
    String[] cijene = getResources().getStringArray(R.array.Cijene);

    for(int i = 0; i<imena.length; i++){

    Patike.add(new Nazivi(imena[i],cijene[i], Slike[i]));

    }
    }

    @Override
    public void klikNaItem(int pozicija) {

     Intent intent = new Intent(Prodaja.this, Informacije.class);
     intent.putExtra("Naziv", Patike.get(pozicija).getNaziv());
     intent.putExtra("Cijena", Patike.get(pozicija).getCijena());
     intent.putExtra("Slika", Patike.get(pozicija).getSlika());
     startActivity(intent);
     finish();

    }

}
