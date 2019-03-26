package com.example.tp01_carlosalberto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;

public class RedesSociais extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redes_sociais);

        if(AccessToken.getCurrentAccessToken() == null){
            Intent intent = new Intent(this, LoginFace.class);
            startActivity(intent);
        }
    }


}
