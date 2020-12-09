package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatosActivity extends AppCompatActivity implements ValueEventListener {
    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText metMensaje;
    TextView mtvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("mensaje");
        metMensaje = findViewById(R.id.etMensaje);
        mtvDatos = findViewById(R.id.tvDatos);
        myRef.addValueEventListener(this);
    }

    public void enviar(View view) {

        myRef.setValue(metMensaje.getText().toString());
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        String valor = snapshot.getValue().toString();
        mtvDatos.append(valor + "\n");
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}