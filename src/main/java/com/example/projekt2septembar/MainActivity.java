package com.example.projekt2septembar;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    EditText Email, Password;
    TextView Login, Link;
    private FirebaseAuth autentifikacija;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        elementi();
        klikDugmeta();

    }

    void klikDugmeta(){

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if(TextUtils.isEmpty(mail)){

                    Email.setError("Morate unijeti svoj mail!");
                    Email.requestFocus();
                    return;

                }

                if(TextUtils.isEmpty(password)){

                    Password.setError("Morate unijeti password!");
                    Password.requestFocus();
                    return;

                }

               ulazKorisnika(mail,password);

            }
        });

    }

    void ulazKorisnika(String a, String b){

        autentifikacija.signInWithEmailAndPassword(a,b).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(MainActivity.this, "Dobro došli!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Prodaja.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Login nije uspio!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void elementi(){

        autentifikacija = FirebaseAuth.getInstance();
        Email = (EditText) findViewById(R.id.LoginEmail);
        Password = (EditText) findViewById(R.id.LoginPassword);
        Login = (Button) findViewById(R.id.LoginButton);
        Link = (TextView) findViewById(R.id.LoginLink);
        String text = "Registrirajte se";
        SpanString(text);

    }

    void SpanString(String a){

        SpannableString string = new SpannableString(a);
        ClickableSpan klik = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(MainActivity.this, Registracija.class);
                startActivity(intent);
            }
        };

        string.setSpan(klik, 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Link.setText(string);
        Link.setMovementMethod(LinkMovementMethod.getInstance());
    }

}