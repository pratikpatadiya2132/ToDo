package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewToDo extends AppCompatActivity {

    TextView header,add_title,add_desc,add_time;
    //EditText desctitle,desctodo,descdate;
    EditText title,desc,time;
    ImageView back;

    Button add;

    DatabaseReference reference;
    Integer number = new Random().nextInt();
    String  editsave = Integer.toString(number);

    private RelativeLayout rlayout;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_do);

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewToDo.this,MainActivity.class);
                startActivity(intent);
            }
        });

        header = findViewById(R.id.header);
        add_title = findViewById(R.id.add_title);
        title = findViewById(R.id.title);

        add_desc = findViewById(R.id.add_desc);
        desc = findViewById(R.id.desc);

        add_time = findViewById(R.id.add_title);
        time = findViewById(R.id.time);

        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this,R.anim.animation);
        rlayout.setAnimation(animation);


        add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inset data to database
                reference = FirebaseDatabase.getInstance().getReference().child("ToDo").child("New"+ number);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("desctitle").setValue(title.getText().toString());
                        snapshot.getRef().child("desctodo").setValue(desc.getText().toString());
                        snapshot.getRef().child("descdate").setValue(time.getText().toString());
                        snapshot.getRef().child("editsave").setValue(editsave);

                        Intent intent = new Intent(NewToDo.this,MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}