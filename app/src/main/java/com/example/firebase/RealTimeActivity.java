package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.firebase.adapters.MensajeAdapter;
import com.example.firebase.models.Mensaje;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RealTimeActivity extends AppCompatActivity {
    private EditText mEditTextMensaje;
    private Button mBtnCrearDatos;

    private DatabaseReference mDatabase;

    private MensajeAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Mensaje> mMensajeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time);

        mEditTextMensaje = findViewById(R.id.etTitulo);
        mBtnCrearDatos = findViewById(R.id.btnCrearDatos);
        mRecyclerView = findViewById(R.id.recyclerViewMensajes);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mBtnCrearDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mensaje = mEditTextMensaje.getText().toString();
                mDatabase.child("Mensaje").push().child("titulo").setValue(mensaje);
                mDatabase.child("Mensaje").push().child("mensaje").setValue(mensaje);
            }
        });

        getMensajesFromFirebase();
    }

    private void getMensajesFromFirebase(){
        mDatabase.child("Mensaje").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    mMensajeList.clear();

                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        String titulo = ds.child("titulo").getValue().toString();
                        String mensaje = ds.child("mensaje").getValue().toString();
                        Mensaje objMensaje = new Mensaje(titulo, mensaje);
                        mMensajeList.add(objMensaje);
                    }

                    mAdapter = new MensajeAdapter(mMensajeList, R.layout.mensaje_view);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}