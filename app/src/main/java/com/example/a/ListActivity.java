package com.example.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {


    private DatabaseReference myRef;
    private FirebaseDatabase myDb;

    private ListView listItem;
    private List<DeliNote> list=new ArrayList<>();
    private ArrayAdapter<DeliNote> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listItem=(ListView)findViewById(R.id.listItem);
        myDb=FirebaseDatabase.getInstance();
        myRef=myDb.getReference("User");

        adapter=new ArrayAdapter<>(ListActivity.this,android.R.layout.simple_list_item_1,list);
        listItem.setAdapter(adapter);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                DeliNote deliNote=dataSnapshot.getValue(DeliNote.class);
                list.add(deliNote);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeliNote upNote=list.get(position);

                Bundle bundle=new Bundle();
                bundle.putSerializable("upNote",upNote);

                Intent intent=new Intent(ListActivity.this,UpdateActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        listItem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                DeliNote userNote=list.get(position);
                String key=userNote.getUserIdl();

                adapter.remove(list.get(position));
                myRef.child(key).removeValue();
                return true;
            }
        });
    }
}
