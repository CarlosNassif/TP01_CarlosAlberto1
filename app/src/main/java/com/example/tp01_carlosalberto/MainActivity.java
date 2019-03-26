package com.example.tp01_carlosalberto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }

    public void AdicionarContato(View view){
        Intent intent = new Intent(this, AddContato.class);
        startActivity(intent);
    }

    public void LoginRedesSociais(View view) {
            Intent intent = new Intent(this, LoginFace.class);
            startActivity(intent);
    }

}
