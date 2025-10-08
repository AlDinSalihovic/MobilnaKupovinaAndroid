package com.example.projekt2september;

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

public class Selling extends AppCompatActivity implements interface {
    ArrayList<Names> Sneakers = new ArrayList<Names>();

    int[] Pictures = {R.drawable.adidas, R.drawable.armour, R.drawable.ballance, R.drawable.fila,
    R.drawable.lacoste, R.drawable.nike, R.drawable.sketchers, R.drawable.polo};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_selling);

        basic();
        putting_info();
        back();
        }


    void basic(){

        RecyclerView recyclerView = findViewById(R.id.dataRecyclerView);
        AdapterClass adapterClass = new AdapterClass(this, Sneakers, this);
        recyclerView.setAdapter(adapterClass);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Space space = new Space(5);
        recyclerView.addItemDecoration(space);

    }

    void back(){

        TextView backToLogin = (TextView) findViewById(R.id.back);
        backToLogin.setPaintFlags(backToLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Selling.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private void putting_info(){

    String[] names = getResources().getStringArray(R.array.Names);
    String[] prices = getResources().getStringArray(R.array.Prices);

    for(int i = 0; i<names.length; i++){

    Sneakers.add(new Nazivi(names[i],prices[i], Pictures[i]));

    }
    }

    @Override
    public void clickOnItem(int position) {

     Intent intent = new Intent(Selling.this, Informations.class);
     intent.putExtra("Name", Sneakers.get(position).getName());
     intent.putExtra("Price", Sneakers.get(position).getPrice());
     intent.putExtra("Picture", Sneakers.get(position).getPicture());
     startActivity(intent);
     finish();

    }

}
