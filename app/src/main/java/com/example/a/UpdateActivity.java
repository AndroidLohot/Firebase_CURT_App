package com.example.a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateActivity extends AppCompatActivity {

    private DatabaseReference myRef;
    private FirebaseDatabase myDb;
    private String Name;

    private float drBile;
    private float kiBile;
    private float paBile;

    private EditText upName,upDrBile,upKirana,upPatrol;
    private DeliNote deliNote;

    private String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        myDb=FirebaseDatabase.getInstance();
        myRef=myDb.getReference("User");

        upDrBile=(EditText)findViewById(R.id.upDrBile);
        upName=(EditText)findViewById(R.id.upName);
        upKirana=(EditText)findViewById(R.id.upKiranabile);
        upPatrol=(EditText)findViewById(R.id.upPatrolBile);

        Bundle bundle=getIntent().getExtras();

        deliNote= (DeliNote) bundle.getSerializable("upNote");

        Name=deliNote.getName();
        drBile=deliNote.getDrBile();
        kiBile=deliNote.getKiBile();
        paBile=deliNote.getPaBile();

        upName.setText(Name);
        upPatrol.setText(String.valueOf(paBile));
        upKirana.setText(String.valueOf(kiBile));
        upDrBile.setText(String.valueOf(drBile));



    }

    public void updateF(View view) {

        key=deliNote.getUserIdl();

        String n=upName.getText().toString();
        float db=Float.valueOf(upDrBile.getText().toString());
        float kb=Float.valueOf(upKirana.getText().toString());
        float pb=Float.valueOf(upPatrol.getText().toString());


        myRef.child(key).child("name").setValue(n);
        myRef.child(key).child("drBile").setValue(db);
        myRef.child(key).child("paBile").setValue(pb);
        myRef.child(key).child("kiBile").setValue(kb);

        Intent intent=new Intent(UpdateActivity.this,ListActivity.class);
        startActivity(intent);
    }
}
