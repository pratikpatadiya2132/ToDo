package com.example.todo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView title,subtitle,endpage;
    RecyclerView ourtodo;
    ArrayList<todo> list;
    todoAdapter adapter;

    Button add;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        subtitle = findViewById(R.id.subtitle);
        endpage = findViewById(R.id.endpage);

        add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NewToDo.class);
                startActivity(intent);
            }
        });


        //wroking with data
        ourtodo = findViewById(R.id.ourtodo);
        ourtodo.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<todo>();

        //get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("ToDo");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot1: snapshot.getChildren() ) {
                    todo t = dataSnapshot1.getValue(todo.class);
                    list.add(t);
                }

                adapter = new todoAdapter(MainActivity.this, list);
                ourtodo.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Data Not Found",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        exitAlert();
    }

    private void exitAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit Alret!!");
        builder.setIcon(R.drawable.logo);
        builder.setMessage("Are You Sure Want To Exit ?");
        builder.setCancelable(false);

        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }

        });

        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });

        /*builder.setNeutralButton("Rate Us", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                new CommonMethod(RecyclerDemo.this, "Rate Us");
                dialogInterface.dismiss();
            }
        }); */

        builder.show();
    }
}