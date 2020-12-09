package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MensajeriaActivity extends AppCompatActivity {
    BroadcastReceiver broadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajeria);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(broadcastReceiver == null){
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Bundle bundle = intent.getExtras();
                    TextView mtvRemitente = findViewById(R.id.tvRemitente);
                    TextView mtvtitulo = findViewById(R.id.tvMenTitulo);
                    TextView mtvContenido = findViewById(R.id.tvContenido);
                    ImageView mivImagen = findViewById(R.id.ivImagen);

                    mtvRemitente.setText(bundle.getString("remitente"));
                    mtvtitulo.setText(bundle.getString("titulo"));
                    mtvContenido.setText(bundle.getString("contenido"));

                    Picasso.get().load(bundle.getString("imageurl")).into(mivImagen);

                }
            };
            registerReceiver(broadcastReceiver, new IntentFilter("escuchar_mensaje"));
        }
    }
}