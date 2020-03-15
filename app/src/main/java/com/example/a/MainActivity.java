package com.example.a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference myRef;
    private FirebaseDatabase myDb;

    private EditText edDrBile,edKiaraBile,edPatrolBile,editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb=FirebaseDatabase.getInstance();
        myRef=myDb.getReference("User");

        editName=(EditText)findViewById(R.id.editName);
        edDrBile=(EditText)findViewById(R.id.edDrBile);
        edKiaraBile=(EditText)findViewById(R.id.edKiaraBile);
        edPatrolBile=(EditText)findViewById(R.id.edPatrolBile);
    }

    public void saveF(View view) {

        String Key=myRef.push().getKey();

        String Name=editName.getText().toString();
        if (TextUtils.isEmpty(editName.getText().toString()))
        {
            editName.setError("Enter this fild");
            return;
        }

        String drBile=edDrBile.getText().toString();
        if (TextUtils.isEmpty(drBile))
        {
            edDrBile.setError("Enter this fild");
            return;
        }

        String kiBile=edKiaraBile.getText().toString();
        if (TextUtils.isEmpty(kiBile))
        {
            edKiaraBile.setError("Enter this fild");
            return;
        }

        String paBile=edPatrolBile.getText().toString();
        if (TextUtils.isEmpty(paBile))
        {
            edPatrolBile.setError("Enter this fild");
            return;
        }

        float db=Float.valueOf(drBile);
        float kb=Float.valueOf(kiBile);
        float pb=Float.valueOf(paBile);

        DeliNote deliNote=new DeliNote(Key,Name,db,kb,pb);

        myRef.child(Key).setValue(deliNote);


        Toast.makeText(MainActivity.this,"Data is insert",Toast.LENGTH_LONG).show();

        editName.setText("");
        edDrBile.setText("");
        edKiaraBile.setText("");
        edPatrolBile.setText("");

    }

    public void goToList(View view) {

        Intent intent=new Intent(MainActivity.this,ListActivity.class);

        startActivity(intent);
    }
}
