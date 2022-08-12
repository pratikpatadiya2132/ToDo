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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditToDo extends AppCompatActivity {

    EditText title,desc,time;
    Button update,delete;
    ImageView back;
    DatabaseReference reference;

    private RelativeLayout rlayout;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do);

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditToDo.this,MainActivity.class);
                startActivity(intent);
            }
        });

        title = findViewById(R.id.desctitle);
        desc = findViewById(R.id.desctodo);
        time = findViewById(R.id.descdate);

        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this,R.anim.animation);
        rlayout.setAnimation(animation);

        title.setText(getIntent().getStringExtra("desctitle"));
        desc.setText(getIntent().getStringExtra("desctodo"));
        time.setText(getIntent().getStringExtra("descdate"));

        final String esave = getIntent().getStringExtra("editsave");

        reference = FirebaseDatabase.getInstance().getReference().child("ToDo").child("New" + esave );

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(EditToDo.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Failure!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("desctitle").setValue(title.getText().toString());
                        snapshot.getRef().child("desctodo").setValue(desc.getText().toString());
                        snapshot.getRef().child("descdate").setValue(time.getText().toString());
                        snapshot.getRef().child("editsave").setValue(esave);
                        Intent intent = new Intent(EditToDo.this,MainActivity.class);
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
