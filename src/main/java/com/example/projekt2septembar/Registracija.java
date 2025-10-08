package com.example.projekt2september;

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

import com.example.projekt2septembar.Create;
import com.example.projekt2septembar.MainActivity;
import com.example.projekt2septembar.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    EditText Username, Email, Password;
    TextView Register, ButtonLogin;

    String name, mail, password;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registracija);

        reference = FirebaseDatabase.getInstance().getReference("Users");
        Username = (EditText) findViewById(R.id.RegisterUsername);
        Email = (EditText) findViewById(R.id.RegisterEmail);
        Password = (EditText) findViewById(R.id.RegisterPassword);
        Register = (Button) findViewById(R.id.RegisterButton);
        ButtonLogin = (TextView) findViewById(R.id.LinkRegister);
        String text = "Log in";
        SpanString(text);
        BtnKlik();

    }

    void BtnClick(){

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = Username.getText().toString().trim();
                mail = Email.getText().toString().trim();
                password = Password.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    Username.setError("Please enter your name!");
                    Username.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(mail)){
                    Email.setError("Please enter your mail!");
                    Email.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Password.setError("Please enter your passworrd!");
                    Password.requestFocus();
                    return;
                }
                Signup();
            }
        });

    }

    private void Signup(){

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail.trim(), password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                UserProfileChangeRequest change = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.updateProfile(change);

                Create create = new Create(FirebaseAuth.getInstance().getUid(), name, mail, password);
                reference.child(FirebaseAuth.getInstance().getUid()).setValue(create);

                Toast.makeText(Registracija.this, "Registration has failed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Registration.this, "Registracija has failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void SpanString(String a){

        SpannableString string = new SpannableString(a);
        ClickableSpan click = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);
            }
        };

        string.setSpan(click, 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ButtonLogin.setText(string);
        ButtonLogin.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
